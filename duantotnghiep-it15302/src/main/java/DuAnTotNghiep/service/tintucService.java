package DuAnTotNghiep.service;

import java.util.List;

import DuAnTotNghiep.entity.tintuc;

public interface tintucService {

	tintuc create(tintuc tintuc);

	List<tintuc> findAll();

	List<tintuc> findByUser(String user);

	tintuc update(tintuc tintuc);

	void delete(Integer id);

	List<tintuc> findByName(String name);


}
