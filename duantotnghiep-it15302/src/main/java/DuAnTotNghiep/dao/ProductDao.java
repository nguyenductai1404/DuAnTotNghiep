package DuAnTotNghiep.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.available=true and p.soluong>0")
	Page<Product> findAvailable(Pageable pa);
	
	@Query("SELECT p FROM Product p WHERE p.catesmall.category.id=?1 and p.available=true and p.soluong>0")
	List<Product> findByCategoryId(String cid);

	@Query("SELECT p FROM Product p WHERE p.catesmall.name=?1 and p.available=true and p.soluong>0")
	List<Product> findByCateName(String name);

	@Query("SELECT p FROM Product p WHERE p.catesmall.name=?1 and p.catesmall.category.id=?2 and p.available=true and p.soluong>0")
	List<Product> findByCateNameAndCateId(String name, String cateid);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE ?1 and p.available=true and p.soluong>0")
	List<Product> findByName(String name);
	
	List<Product> findByPriceBetween(double minPrice, double maxPrice);
	

	@Query("SELECT p FROM Product p WHERE p.cuahang.account.username=?1 and p.available=true and p.soluong>0")
	List<Product> findByStore(String store);

	@Query("SELECT p FROM Product p WHERE p.cuahang.id=?1 and p.available=true and p.soluong>0")
	List<Product> findByStoreId(Integer id);
	
}
