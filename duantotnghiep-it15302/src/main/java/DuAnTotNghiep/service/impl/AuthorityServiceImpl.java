package DuAnTotNghiep.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.dao.AccountDao;
import DuAnTotNghiep.dao.AuthorityDao;
import DuAnTotNghiep.entity.Authority;
import DuAnTotNghiep.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{

	
	@Autowired AuthorityDao audao;
	@Autowired AccountDao adao;
	
	
	@Override
	public Authority create(Authority authority) {
		return audao.save(authority);
	}
}
