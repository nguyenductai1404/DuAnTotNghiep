package DuAnTotNghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.dao.CmtproductDao;
import DuAnTotNghiep.entity.Cmtproduct;
import DuAnTotNghiep.service.CmtproductService;

@Service
public class CmtproductServiceImpl implements CmtproductService{

	@Autowired CmtproductDao dao;
	
	@Override
	public List<Cmtproduct> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<Cmtproduct> findByIdProduct(Integer id) {
		// TODO Auto-generated method stub
		return dao.findByIdProduct(id);
	}

	@Override
	public Cmtproduct create(Cmtproduct cmtproduct) {
		// TODO Auto-generated method stub
		return dao.save(cmtproduct);
	}
	
}
