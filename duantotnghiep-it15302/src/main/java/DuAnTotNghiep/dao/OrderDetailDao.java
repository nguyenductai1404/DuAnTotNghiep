package DuAnTotNghiep.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Orderdetail;
import DuAnTotNghiep.entity.Reportdetail;

public interface OrderDetailDao extends JpaRepository<Orderdetail, Long>{

	@Query("SELECT new Reportdetail(d.order.account.username, d.order.createDate, sum(d.price * d.quantity), sum(d.quantity)) FROM Orderdetail d where d.order.hoanthanh=?1 and d.order.cuahang.account.username=?2 GROUP BY d.order.account.username, d.order.createDate")
	List<Reportdetail> thongKeDoanhThu(boolean trangthai, String username);

	@Query("SELECT new Reportdetail(d.order.account.username, d.order.createDate, sum(d.price * d.quantity), sum(d.quantity)) FROM Orderdetail d where MONTH(d.order.createDate)=?1 and d.order.hoanthanh=true and d.order.cuahang.account.username=?2 GROUP BY d.order.account.username, d.order.createDate")
	List<Reportdetail> thongKeDoanhThuThang(Integer thang, String username);

	@Query("SELECT new Reportdetail(d.order.account.username, d.order.createDate, sum(d.price * d.quantity), sum(d.quantity)) FROM Orderdetail d where YEAR(d.order.createDate)=?1 and d.order.hoanthanh=true and d.order.cuahang.account.username=?2 GROUP BY d.order.account.username, d.order.createDate")
	List<Reportdetail> thongKeDoanhThuNam(Integer nam, String username);

	@Query("SELECT new Reportdetail(d.order.account.username, d.order.createDate, sum(d.price * d.quantity), sum(d.quantity)) FROM Orderdetail d where MONTH(d.order.createDate)=?1 and YEAR(d.order.createDate)=?2 and d.order.hoanthanh=true and d.order.cuahang.account.username=?3 GROUP BY d.order.account.username, d.order.createDate")
	List<Reportdetail> TKDoanhThu(Integer thang, Integer nam, String username);

}
