package DuAnTotNghiep.service;

import java.util.List;

import DuAnTotNghiep.entity.Cmtstore;

public interface CmtstoreService {

	List<Cmtstore> findAll();

	List<Cmtstore> findByIdStore(Integer id);

	Cmtstore create(Cmtstore cmtstore);


}
