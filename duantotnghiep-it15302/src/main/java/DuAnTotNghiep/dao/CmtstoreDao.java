package DuAnTotNghiep.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Cmtstore;

public interface CmtstoreDao extends JpaRepository<Cmtstore, Integer>{

	@Query("SELECT p FROM Cmtstore p WHERE p.cuahang.id=?1")
	List<Cmtstore> findByIdStore(Integer id);

}
