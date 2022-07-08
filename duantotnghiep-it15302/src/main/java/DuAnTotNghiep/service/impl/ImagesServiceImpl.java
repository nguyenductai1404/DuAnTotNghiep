package DuAnTotNghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.dao.ImageDao;
import DuAnTotNghiep.entity.Image;
import DuAnTotNghiep.service.ImagesService;

@Service
public class ImagesServiceImpl implements ImagesService{

	@Autowired ImageDao dao;

	@Override
	public Image findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public List<Image> findByIdProduct(Integer id) {
		// TODO Auto-generated method stub
		return dao.findByIdProduct(id);
	}

}
