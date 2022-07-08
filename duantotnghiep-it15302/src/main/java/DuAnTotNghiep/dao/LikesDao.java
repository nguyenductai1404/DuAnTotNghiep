package DuAnTotNghiep.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DuAnTotNghiep.entity.Likes;

public interface LikesDao extends JpaRepository<Likes, Integer>{

	@Query("SELECT p.product.id FROM Likes p WHERE p.account.username=?1")
	List<Integer> findUsername(String username);

	@Query("SELECT p FROM Likes p WHERE p.account.username=?1")
	Page<Likes> findByUser(String username, Pageable pa);

}
