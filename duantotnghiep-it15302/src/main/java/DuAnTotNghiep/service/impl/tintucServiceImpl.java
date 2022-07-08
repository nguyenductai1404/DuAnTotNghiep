package DuAnTotNghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.dao.tintucDao;
import DuAnTotNghiep.entity.tintuc;
import DuAnTotNghiep.service.tintucService;

@Service
public class tintucServiceImpl implements tintucService{

	@Autowired tintucDao dao;

	@Override
	public tintuc create(tintuc tintuc) {
		// TODO Auto-generated method stub
		return dao.save(tintuc);
	}

	@Override
	public List<tintuc> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<tintuc> findByUser(String user) {
		// TODO Auto-generated method stub
		return dao.findByUser(user);
	}

	@Override
	public tintuc update(tintuc tintuc) {
		// TODO Auto-generated method stub
		return dao.save(tintuc);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public List<tintuc> findByName(String name) {
		// TODO Auto-generated method stub
		return dao.findByName(name);
	}
	
	
}
