package DuAnTotNghiep.service;

import java.util.List;

import DuAnTotNghiep.entity.Image;

public interface ImagesService {

	Image findById(Integer id);
	
	List<Image> findByIdProduct(Integer id);

}
