package DuAnTotNghiep.rest.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import DuAnTotNghiep.Config;
import DuAnTotNghiep.dao.CartDao;
import DuAnTotNghiep.dao.CodesaleDao;
import DuAnTotNghiep.dao.OrderDao;
import DuAnTotNghiep.dao.OrderDetailDao;
import DuAnTotNghiep.dao.ProductDao;
import DuAnTotNghiep.dao.SaleuserDao;
import DuAnTotNghiep.dao.StoreDao;
import DuAnTotNghiep.dto.payment;
import DuAnTotNghiep.entity.Cart;
import DuAnTotNghiep.entity.Codesale;
import DuAnTotNghiep.entity.Order;
import DuAnTotNghiep.entity.Orderdetail;
import DuAnTotNghiep.entity.Product;
import DuAnTotNghiep.entity.Saleuser;
import DuAnTotNghiep.entity.Store;
import DuAnTotNghiep.entity.Total;
import DuAnTotNghiep.service.CartService;
import DuAnTotNghiep.service.LikeService;
import DuAnTotNghiep.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/pay")
public class PayRestController {

	@Autowired
	OrderDao odao;
	@Autowired
	LikeService likeService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailDao ddao;
	@Autowired
	ProductDao pdao;
	@Autowired
	StoreDao sdao;
	@Autowired
	CartDao cdao;
	@Autowired
	CodesaleDao codedao;
	@Autowired
	SaleuserDao saledao;
	@Autowired
	CartService cartservice;

	@PostMapping()
	public payment pay(HttpServletRequest req, HttpServletResponse resp, @RequestBody JsonNode orderData)
			throws ServletException, IOException {

		String user = req.getRemoteUser();

		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);

		String id = "";
		double tong = 0;
		int giam = 0;
		List<Total> total = cartservice.getTotalByUser(user);
		List<Codesale> kiemtra = codedao.findAll();

		Codesale code = null;

		String pattern = "ddMMyyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		for(Codesale c1 : kiemtra) {
			int now = Integer.parseInt(simpleDateFormat.format(order.getCreateDate()));
			int end = Integer.parseInt(simpleDateFormat.format(c1.getEndday()));
			int star = Integer.parseInt(simpleDateFormat.format(c1.getStarday()));
			if(star <= now && end >= now) {
				c1.setTrangthai(true);
				codedao.save(c1);
			}else {
				c1.setTrangthai(false);
				codedao.save(c1);
			}
		}

		for (Total t : total) {
			tong = t.getTong();
			Store store = sdao.findByTen(t.getCuahang());
			Order order1 = new Order();
			if (order.getCodesale() != null) {
				Saleuser saleuser = saledao.findByUser(t.getUsername(), order.getCodesale().getCode());
				if (saleuser == null) {
					code = codedao.findByCode(order.getCodesale().getCode());
					if (code != null) {
						if (code.isTrangthai() && store.getId() == code.getCuahang().getId()) {
							Saleuser sale = new Saleuser();
							sale.setAccount(order.getAccount());
							sale.setCodesale(code);
							sale.setDate(order.getCreateDate());
							saledao.save(sale);
							giam = (int) t.getTong() * code.getPercents() / 100;
							tong = t.getTong() - giam;
							order1.setCodesale(code);
						}
					}
				}
			}
			order1.setAccount(order.getAccount());
			order1.setCreateDate(new Date());
			order1.setAddress(order.getAddress());
			order1.setNguoinhan(order.getNguoinhan());
			order1.setSdt(order.getSdt());
			order1.setDiachinn(order.getDiachinn());
			order1.setTrangthai(order.getTrangthai());
			order1.setTongtien(tong + "");
			order1.setCuahang(store);
			order1.setHoanthanh(true);
			Order or = odao.save(order1);
			id += or.getId() + "-";
			List<Cart> cart = cartservice.getfindUserAndStore(t.getUsername(), t.getCuahang());
			for (Cart c : cart) {
				Product pro = pdao.getById(c.getProductid());
				Orderdetail detail = new Orderdetail();
				detail.setOrder(or);
				detail.setProduct(pro);
				detail.setPrice(c.getPrice());
				detail.setQuantity(c.getQty());
				ddao.save(detail);
				cdao.deleteById(c.getId());
			}
		}

		String vnp_TxnRef = id;

		String vnp_Version = "2.1.0";
		String vnp_Command = "pay";
		String vnp_OrderInfo = "Thanh Toán";
		String orderType = "Giảm giá: " + giam + "VND";
		int amount = (Integer.parseInt(order.getTongtien()) - giam) * 100;
		String vnp_IpAddr = Config.getIpAddress(req);
		String vnp_TmnCode = Config.vnp_TmnCode;

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef.toString());
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");
		String bank_code = "NCB";
		if (bank_code != null && !bank_code.isEmpty()) {
			vnp_Params.put("vnp_BankCode", bank_code);
		}
		vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
		vnp_Params.put("vnp_OrderType", orderType);

		String locate = req.getParameter("language");
		if (locate != null && !locate.isEmpty()) {
			vnp_Params.put("vnp_Locale", locate);
		} else {
			vnp_Params.put("vnp_Locale", "vn");
		}
		vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

		Date dt = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(dt);
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		Calendar cldvnp_ExpireDate = Calendar.getInstance();
		cldvnp_ExpireDate.add(Calendar.SECOND, 30);
		Date vnp_ExpireDateD = cldvnp_ExpireDate.getTime();
		String vnp_ExpireDate = formatter.format(vnp_ExpireDateD);

		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				// hashData.append(fieldValue); //sử dụng và 2.0.0 và 2.0.1 checksum sha256
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString())); // sử dụng v2.1.0
																										// check sum
																										// sha512
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

		payment payment = new payment();
		payment.setCode("00");
		payment.setMessage("success");
		payment.setUrl(paymentUrl);

		return payment;
	}
}
