package DuAnTotNghiep.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import DuAnTotNghiep.entity.Order;
import DuAnTotNghiep.entity.Orderdetail;
import DuAnTotNghiep.entity.Reportdetail;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(Long id);

	List<Order> findByUsername(String username);

	List<Order> findByDetails(String username);

	List<Reportdetail> thongKeDoanhThu(boolean trangthai, String username);

	Order create(Order order);

	void delete(Long id);

	List<Reportdetail> thongKeDoanhThuThang(Integer thang, String username);

	List<Reportdetail> thongKeDoanhThuNam(Integer nam, String username);

	List<Reportdetail> TKDoanhThu(Integer thang, Integer nam, String username);


}
