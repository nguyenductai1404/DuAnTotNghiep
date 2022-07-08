package DuAnTotNghiep.service;

import java.util.List;

import DuAnTotNghiep.entity.Catesmall;

public interface CatesmallService {

	List<Catesmall> findAll();

	List<Catesmall> findByCate(String cid);

}
