package DuAnTotNghiep.service;

import java.util.List;

import DuAnTotNghiep.entity.Codesale;

public interface CodeService {

	Codesale create(Codesale codesale);

	List<Codesale> findAll();

	List<Codesale> findByUser(String user);

	Codesale update(Codesale codesale);

	void delete(Integer id);


}
