package DuAnTotNghiep.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Cmtproduct;

public interface CmtproductDao extends JpaRepository<Cmtproduct, Integer>{

	@Query("SELECT p FROM Cmtproduct p WHERE p.product.id=?1")
	List<Cmtproduct> findByIdProduct(Integer id);

}
