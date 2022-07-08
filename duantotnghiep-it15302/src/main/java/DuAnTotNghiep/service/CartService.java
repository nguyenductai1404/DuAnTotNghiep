package DuAnTotNghiep.service;

import java.util.List;

import DuAnTotNghiep.entity.Cart;
import DuAnTotNghiep.entity.Total;

public interface CartService {

	Cart create(Cart cart);

	List<Total> getTotalByUser(String user);

	List<Cart> getfindUserAndStore(String username, String cuahang);

}
