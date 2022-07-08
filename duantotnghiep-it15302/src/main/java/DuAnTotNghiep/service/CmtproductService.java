package DuAnTotNghiep.service;

import java.util.List;

import DuAnTotNghiep.entity.Cmtproduct;
import DuAnTotNghiep.entity.Product;

public interface CmtproductService {

	List<Cmtproduct> findAll();

	List<Cmtproduct> findByIdProduct(Integer id);

	Cmtproduct create(Cmtproduct cmtproduct);

//	Product findById(Integer id);
//
//	List<Product> findByCategoryId(String cid);
//
//	Product create(Product product);
//
//	Product update(Product product);

}
