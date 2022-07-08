package DuAnTotNghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.dao.CodesaleDao;
import DuAnTotNghiep.entity.Codesale;
import DuAnTotNghiep.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService{

	@Autowired CodesaleDao dao;

	@Override
	public Codesale create(Codesale codesale) {
		// TODO Auto-generated method stub
		return dao.save(codesale);
	}

	@Override
	public List<Codesale> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<Codesale> findByUser(String user) {
		// TODO Auto-generated method stub
		return dao.findByUser(user);
	}

	@Override
	public Codesale update(Codesale codesale) {
		// TODO Auto-generated method stub
		return dao.save(codesale);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}
	
	
}
