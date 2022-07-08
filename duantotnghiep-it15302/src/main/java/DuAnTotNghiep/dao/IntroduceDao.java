package DuAnTotNghiep.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Introduce;

public interface IntroduceDao extends JpaRepository<Introduce, Integer>{

	@Query("SELECT p FROM Introduce p WHERE p.product.id=?1")
	List<Introduce> findByIdProduct(Integer id);

}
