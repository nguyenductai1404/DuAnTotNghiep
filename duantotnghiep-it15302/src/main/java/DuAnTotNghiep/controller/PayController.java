package DuAnTotNghiep.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import DuAnTotNghiep.Config;
import DuAnTotNghiep.MailService;
import DuAnTotNghiep.dao.OrderDao;
import DuAnTotNghiep.dao.OrderDetailDao;
import DuAnTotNghiep.dao.ProductDao;
import DuAnTotNghiep.entity.Order;
import DuAnTotNghiep.entity.Orderdetail;
import DuAnTotNghiep.entity.Product;
import DuAnTotNghiep.service.OrderService;

@Controller
@RequestMapping("/pay")
public class PayController {

	@Autowired
	OrderDao odao;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDetailDao ddao;
	@Autowired
	HttpServletRequest req;
	@Autowired
	MailService mail;
	@Autowired
	ProductDao pdao;

	@RequestMapping("/ipn")
	public String ipn(Model model) throws ServletException, IOException {

		Map fields = new HashMap();
		for (Enumeration params = req.getParameterNames(); params.hasMoreElements();) {
			// String fieldName = (String) params.nextElement();
			// String fieldValue = request.getParameter(fieldName);
			String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
			String fieldValue = URLEncoder.encode(req.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				fields.put(fieldName, fieldValue);
			}
		}
		String id = req.getParameter("vnp_TxnRef");

		String vnp_SecureHash = req.getParameter("vnp_SecureHash");
		if (fields.containsKey("vnp_SecureHashType")) {
			fields.remove("vnp_SecureHashType");
		}
		if (fields.containsKey("vnp_SecureHash")) {
			fields.remove("vnp_SecureHash");
		}
		String signValue = Config.hashAllFields(fields);
		if (signValue.equals(vnp_SecureHash)) {
			// Kiem tra chu ky OK
			/*
			 * Kiem tra trang thai don hang trong DB: checkOrderStatus, - Neu trang thai don
			 * hang OK, tien hanh cap nhat vao DB, tra lai cho VNPAY RspCode=00 - Neu trang
			 * thai don hang (da cap nhat roi) => khong cap nhat vao DB, tra lai cho VNPAY
			 * RspCode=02
			 */
			boolean checkOrderId = true; // vnp_TxnRef đơn hàng có tồn tại trong database merchant
			boolean checkAmount = true; // vnp_Amount is valid (so sánh số tiền VNPAY request và sô tiền của giao dịch
										// trong database merchant)
			boolean checkOrderStatus = true; // PaymnentStatus = 0 (pending)
			if (checkOrderId) {
				if (checkAmount) {
					if (checkOrderStatus) {
						if ("00".equals(req.getParameter("vnp_ResponseCode"))) {
							List<String> stringList = Pattern.compile("-").splitAsStream(id)
									.collect(Collectors.toList());
							stringList.forEach(s -> {
								Order order = odao.findById(Long.parseLong(s)).get();
								order.setTrangthai("Đã đặt hàng");
								odao.save(order);
								NumberFormat currentLocale = NumberFormat.getInstance();
								String tongtien = currentLocale.format(Double.parseDouble(order.getTongtien()));
								try {
									mail.send(order.getAccount().getEmail(), "Tổng tiền bạn thanh toán", tongtien);
								} catch (Exception e) {
									// TODO: handle exception
								}
								List<Orderdetail> details = order.getOrderDetails();
								for (int i = 0; i < details.size(); i++) {
									String detailsid = details.get(i).getProduct().getId().toString();
									Product pro = pdao.findById(Integer.parseInt(detailsid)).get();
									pro.setSoluong(pro.getSoluong() - details.get(i).getQuantity());
									pdao.save(pro);
								}
							});
							return "redirect:/order/list";
						} else {
							// Xu ly thanh toan khong thanh cong
							// out.print("GD Khong thanh cong");
							List<String> stringList = Pattern.compile("-").splitAsStream(id)
									.collect(Collectors.toList());
							stringList.forEach(s -> {
								Order order = odao.findById(Long.parseLong(s)).get();
								order.setTrangthai("Thanh toán thất bại");
								order.setHoanthanh(false);
								odao.save(order);
							});
							model.addAttribute("pay", "Giao dịch thất bại mời bạn thử lại - Mã đơn hàng: " + id);
							return "/order/checkout";
						}
//						out.print("{\"RspCode\":\"00\",\"Message\":\"Confirm Success\"}");
					} else {
						// Don hang nay da duoc cap nhat roi, Merchant khong cap nhat nua (Duplicate
						// callback)
//						out.print("{\"RspCode\":\"02\",\"Message\":\"Order already confirmed\"}");
					}
				} else {
//					out.print("{\"RspCode\":\"04\",\"Message\":\"Invalid Amount\"}");
				}
			} else {
//				out.print("{\"RspCode\":\"01\",\"Message\":\"Order not Found\"}");
			}

		} else {
//			out.print("{\"RspCode\":\"97\",\"Message\":\"Invalid Checksum\"}");
		}
		List<String> stringList = Pattern.compile("-").splitAsStream(id).collect(Collectors.toList());
		stringList.forEach(s -> {
			Order order = odao.findById(Long.parseLong(s)).get();
			order.setTrangthai("Thanh toán thất bại");
			order.setHoanthanh(false);
			odao.save(order);
		});
		model.addAttribute("pay", "Giao dịch thất bại mời bạn thử lại - Mã đơn hàng: " + id);
		return "/order/checkout";
	}
}
