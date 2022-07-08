package DuAnTotNghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Account;

public interface AccountDao extends JpaRepository<Account, String>{

	@Query("SELECT p FROM Account p WHERE p.username LIKE ?1")
	List<Account> findByName(String name);
	
}
