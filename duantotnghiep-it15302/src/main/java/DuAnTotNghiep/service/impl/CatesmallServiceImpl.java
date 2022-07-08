package DuAnTotNghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.dao.CateSmallDao;
import DuAnTotNghiep.entity.Catesmall;
import DuAnTotNghiep.service.CatesmallService;

@Service
public class CatesmallServiceImpl implements CatesmallService{

	@Autowired CateSmallDao cdao;
	
	@Override
	public List<Catesmall> findAll() {
		return cdao.findAll();
	}

	@Override
	public List<Catesmall> findByCate(String cid) {
		return cdao.findByCate(cid);
	}

}
