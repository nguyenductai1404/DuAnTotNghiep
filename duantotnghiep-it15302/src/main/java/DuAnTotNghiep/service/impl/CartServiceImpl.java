package DuAnTotNghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.dao.CartDao;
import DuAnTotNghiep.entity.Cart;
import DuAnTotNghiep.entity.Total;
import DuAnTotNghiep.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired CartDao cdao;

	@Override
	public Cart create(Cart cart) {
		// TODO Auto-generated method stub
		return cdao.save(cart);
	}

	@Override
	public List<Total> getTotalByUser(String user) {
		// TODO Auto-generated method stub
		return cdao.getTotalByUser(user);
	}

	@Override
	public List<Cart> getfindUserAndStore(String username, String cuahang) {
		// TODO Auto-generated method stub
		return cdao.getfindUserAndStore(username, cuahang);
	}

	
}
