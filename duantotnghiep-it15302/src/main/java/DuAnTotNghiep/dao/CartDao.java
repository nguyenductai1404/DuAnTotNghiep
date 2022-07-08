package DuAnTotNghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Cart;
import DuAnTotNghiep.entity.Total;

public interface CartDao extends JpaRepository<Cart, Integer>{

	@Query("SELECT new Total(d.cuahang, d.username, sum(d.price * d.qty), sum(d.qty)) FROM Cart d where d.username=?1 GROUP BY d.cuahang, d.username")
	List<Total> getTotalByUser(String user);

	@Query("SELECT p FROM Cart p WHERE p.username=?1 and p.cuahang=?2")
	List<Cart> getfindUserAndStore(String username, String cuahang);
}
