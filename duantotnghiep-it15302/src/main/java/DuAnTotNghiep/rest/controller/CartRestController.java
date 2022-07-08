package DuAnTotNghiep.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import DuAnTotNghiep.entity.Cart;
import DuAnTotNghiep.service.CartService;

@CrossOrigin("*")
@RestController
public class CartRestController {

	@Autowired
	CartService cartservice;
	
	@PostMapping("/rest/cart")
	public Cart save(@RequestBody Cart cart) {
		return cartservice.create(cart);
	}
}
