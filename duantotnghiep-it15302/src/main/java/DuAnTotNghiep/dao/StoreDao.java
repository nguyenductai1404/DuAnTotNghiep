package DuAnTotNghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Store;

public interface StoreDao extends JpaRepository<Store, Integer>{

	@Query("SELECT p FROM Store p WHERE p.account.username=?1")
	Store findByStore(String username);

	@Query("SELECT p FROM Store p WHERE p.tencuahang=?1")
	Store findByTen(String cuahang);

	@Query("SELECT p FROM Store p WHERE p.tencuahang LIKE ?1")
	List<Store> findByName(String name);


}
