package DuAnTotNghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Specification;

public interface SpecificationDao extends JpaRepository<Specification, Integer>{

	@Query("SELECT p FROM Specification p WHERE p.product.id=?1")
	List<Specification> findByIdProduct(Integer id);

}
