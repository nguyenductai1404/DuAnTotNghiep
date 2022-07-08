package DuAnTotNghiep.service;

import java.util.List;

import DuAnTotNghiep.dto.forgot;
import DuAnTotNghiep.entity.Account;

public interface AccountService {

	Account findById(String username);
	
	List<Account> findAll();

	Account update(Account account);

	void delete(String username);

	Account create(Account account);

	Account update(forgot forgot);

	List<Account> findByName(String name);
}
