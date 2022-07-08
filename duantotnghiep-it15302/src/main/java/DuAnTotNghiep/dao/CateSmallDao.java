package DuAnTotNghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Catesmall;
import DuAnTotNghiep.entity.Product;

public interface CateSmallDao extends JpaRepository<Catesmall, String>{

	@Query("SELECT p FROM Catesmall p WHERE p.category.id=?1")
	List<Catesmall> findByCate(String cid);

}
