package DuAnTotNghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Image;

public interface ImageDao extends JpaRepository<Image, Integer>{

	@Query("SELECT p FROM Image p WHERE p.product.id=?1")
	List<Image> findByIdProduct(Integer id);
}
