package DuAnTotNghiep.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Codedmk;

public interface CodedmkDao extends JpaRepository<Codedmk, Integer>{

	@Query("SELECT p FROM Codedmk p WHERE p.account.username=?1")
	List<Codedmk> findUsername(String username);

	

}
