package DuAnTotNghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.dao.CmtstoreDao;
import DuAnTotNghiep.entity.Cmtstore;
import DuAnTotNghiep.service.CmtstoreService;

@Service
public class CmtstoreServiceImpl implements CmtstoreService{

	@Autowired CmtstoreDao dao;
	
	@Override
	public List<Cmtstore> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<Cmtstore> findByIdStore(Integer id) {
		// TODO Auto-generated method stub
		return dao.findByIdStore(id);
	}

	@Override
	public Cmtstore create(Cmtstore cmtstore) {
		// TODO Auto-generated method stub
		return dao.save(cmtstore);
	}
	
}
