package DuAnTotNghiep.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;

import DuAnTotNghiep.entity.Order;
import DuAnTotNghiep.service.OrderService;

@Controller
public class OrderController {

	@Autowired OrderService orderService;
	
	@RequestMapping("/order/checkout")
	public String checkout() {
		return "order/checkout";
	}
	@RequestMapping("/order/list")
	public String list(Model m, HttpServletRequest req) {
		String username = req.getRemoteUser();
		m.addAttribute("orders", orderService.findByUsername(username));
		return "order/chitietdonhang";
	}
	@RequestMapping("/order/detail/{id}")
	public String detail(Model m, @PathVariable("id") Long id) {
		m.addAttribute("order", orderService.findById(id));
		return "order/details";
	}
	@RequestMapping("/order/delete/{id}")
	public String delete(Model m, @PathVariable("id") Long id) {
		orderService.delete(id);
		return "redirect:/order/list";
	}
}
