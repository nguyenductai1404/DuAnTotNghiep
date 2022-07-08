package DuAnTotNghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Order;
import DuAnTotNghiep.entity.Orderdetail;

public interface OrderDao extends JpaRepository<Order, Long> {

	@Query("SELECT p FROM Order p WHERE p.cuahang.account.username=?1")
	List<Order> findByDetails(String username);
	
	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	List<Order> findByUsername(String username);

}
