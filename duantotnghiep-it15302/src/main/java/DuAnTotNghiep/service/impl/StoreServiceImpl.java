package DuAnTotNghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.dao.StoreDao;
import DuAnTotNghiep.entity.Store;
import DuAnTotNghiep.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired StoreDao sdao;
	
	@Override
	public List<Store> findAll() {
		return sdao.findAll();
	}

	@Override
	public Store findById(Integer id) {
		// TODO Auto-generated method stub
		return sdao.findById(id).get();
	}

	@Override
	public Store create(Store store) {
		// TODO Auto-generated method stub
		return sdao.save(store);
	}

	@Override
	public Store update(Store store) {
		// TODO Auto-generated method stub
		return sdao.save(store);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		sdao.deleteById(id);
	}

	@Override
	public Store findByStore(String username) {
		// TODO Auto-generated method stub
		return sdao.findByStore(username);
	}

	@Override
	public List<Store> findByName(String name) {
		// TODO Auto-generated method stub
		return sdao.findByName(name);
	}

}
