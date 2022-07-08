package DuAnTotNghiep.service;

import java.util.List;

import DuAnTotNghiep.entity.Specification;

public interface SpecificationService {

	List<Specification> findByIdProduct(Integer id);


}
