package DuAnTotNghiep.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Saleuser;

public interface SaleuserDao extends JpaRepository<Saleuser, Integer>{

	@Query("SELECT p FROM Saleuser p WHERE p.account.username=?1 and p.codesale.code=?2")
	Saleuser findByUser(String user, String code);


}
