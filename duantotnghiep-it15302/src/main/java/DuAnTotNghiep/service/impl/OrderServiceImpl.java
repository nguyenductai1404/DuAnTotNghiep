package DuAnTotNghiep.service.impl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import DuAnTotNghiep.MailService;
import DuAnTotNghiep.dao.AccountDao;
import DuAnTotNghiep.dao.CartDao;
import DuAnTotNghiep.dao.CodesaleDao;
import DuAnTotNghiep.dao.OrderDao;
import DuAnTotNghiep.dao.OrderDetailDao;
import DuAnTotNghiep.dao.ProductDao;
import DuAnTotNghiep.dao.SaleuserDao;
import DuAnTotNghiep.dao.StoreDao;
import DuAnTotNghiep.entity.Account;
import DuAnTotNghiep.entity.Cart;
import DuAnTotNghiep.entity.Codesale;
import DuAnTotNghiep.entity.Order;
import DuAnTotNghiep.entity.Orderdetail;
import DuAnTotNghiep.entity.Product;
import DuAnTotNghiep.entity.Reportdetail;
import DuAnTotNghiep.entity.Saleuser;
import DuAnTotNghiep.entity.Store;
import DuAnTotNghiep.entity.Total;
import DuAnTotNghiep.service.CartService;
import DuAnTotNghiep.service.LikeService;
import DuAnTotNghiep.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao odao;
	@Autowired
	OrderDetailDao ddao;
	@Autowired
	MailService mail;
	@Autowired
	AccountDao adao;
	@Autowired
	LikeService likeService;
	@Autowired
	OrderService orderService;
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
	@Autowired
	HttpServletRequest req;

	@Override
	public Order create(JsonNode orderData) {
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

		for (Codesale c1 : kiemtra) {
			int now = Integer.parseInt(simpleDateFormat.format(order.getCreateDate()));
			int end = Integer.parseInt(simpleDateFormat.format(c1.getEndday()));
			int star = Integer.parseInt(simpleDateFormat.format(c1.getStarday()));
			if (star <= now && end >= now) {
				c1.setTrangthai(true);
				codedao.save(c1);
			} else {
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

				String detailsid = detail.getProduct().getId().toString();
				Product pro1 = pdao.findById(Integer.parseInt(detailsid)).get();
				pro1.setSoluong(pro1.getSoluong() - detail.getQuantity());
				pdao.save(pro1);

			}

			List<String> stringList = Pattern.compile("-").splitAsStream(id).collect(Collectors.toList());
			stringList.forEach(s -> {
				NumberFormat currentLocale = NumberFormat.getInstance();
				String tongtien = currentLocale.format(Double.parseDouble(or.getTongtien()));
				try {
					mail.send(or.getAccount().getEmail(), "Tổng tiền bạn thanh toán", tongtien);
				} catch (Exception e) {
					// TODO: handle exception
				}
			});
		}

		return order;
	}

	@Override
	public Order findById(Long id) {
		return odao.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		// TODO Auto-generated method stub
		return odao.findByUsername(username);
	}

	@Override
	public List<Order> findByDetails(String username) {
		// TODO Auto-generated method stub
		return odao.findByDetails(username);
	}

	@Override
	public List<Reportdetail> thongKeDoanhThu(boolean trangthai, String username) {
		return ddao.thongKeDoanhThu(trangthai, username);
	}

	@Override
	public Order create(Order order) {
		// TODO Auto-generated method stub
		return odao.save(order);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Order order = odao.findById(id).get();
		order.setTrangthai("Đơn hàng đã hủy");
		order.setHoanthanh(false);
		odao.save(order);
	}

	@Override
	public List<Reportdetail> thongKeDoanhThuThang(Integer thang, String username) {
		// TODO Auto-generated method stub
		System.err.println(thang);
		return ddao.thongKeDoanhThuThang(thang, username);
	}

	@Override
	public List<Reportdetail> thongKeDoanhThuNam(Integer nam, String username) {
		// TODO Auto-generated method stub
		return ddao.thongKeDoanhThuNam(nam, username);
	}

	@Override
	public List<Reportdetail> TKDoanhThu(Integer thang, Integer nam, String username) {
		// TODO Auto-generated method stub
		return ddao.TKDoanhThu(thang, nam, username);
	}

}
