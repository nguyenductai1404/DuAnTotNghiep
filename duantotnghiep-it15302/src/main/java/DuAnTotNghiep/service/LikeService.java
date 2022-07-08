package DuAnTotNghiep.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import DuAnTotNghiep.entity.Likes;

public interface LikeService {

	List<Likes> findAll();

	void deleteById(Integer id);

	Likes create(Likes like);

	List<Integer> findUsername(String username);

	Page<Likes> findByUser(String username, Pageable pa);

}
