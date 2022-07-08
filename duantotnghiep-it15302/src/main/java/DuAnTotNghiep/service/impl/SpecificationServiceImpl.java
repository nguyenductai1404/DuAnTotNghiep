package DuAnTotNghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.dao.SpecificationDao;
import DuAnTotNghiep.entity.Specification;
import DuAnTotNghiep.service.SpecificationService;

@Service
public class SpecificationServiceImpl implements SpecificationService{

	@Autowired SpecificationDao spedao;

	@Override
	public List<Specification> findByIdProduct(Integer id) {
		// TODO Auto-generated method stub
		return spedao.findByIdProduct(id);
	}

}
