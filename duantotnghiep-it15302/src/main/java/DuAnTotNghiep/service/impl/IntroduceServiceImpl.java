package DuAnTotNghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.dao.IntroduceDao;
import DuAnTotNghiep.entity.Introduce;
import DuAnTotNghiep.service.IntroduceService;

@Service
public class IntroduceServiceImpl implements IntroduceService{

	@Autowired
	IntroduceDao idao;
	
	@Override
	public List<Introduce> findByIdProduct(Integer id) {
		// TODO Auto-generated method stub
		return idao.findByIdProduct(id);
	}


}
