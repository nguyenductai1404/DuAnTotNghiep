package DuAnTotNghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.tintuc;

public interface tintucDao extends JpaRepository<tintuc, Integer>{

	@Query("SELECT p FROM tintuc p WHERE p.account.username=?1")
	List<tintuc> findByUser(String user);

	@Query("SELECT p FROM tintuc p WHERE p.noidung LIKE ?1")
	List<tintuc> findByName(String name);

	
}
