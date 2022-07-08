package DuAnTotNghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.dao.CategoryDao;
import DuAnTotNghiep.entity.Category;
import DuAnTotNghiep.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired CategoryDao cdao;
	
	@Override
	public List<Category> findAll() {
		return cdao.findAll();
	}

}
