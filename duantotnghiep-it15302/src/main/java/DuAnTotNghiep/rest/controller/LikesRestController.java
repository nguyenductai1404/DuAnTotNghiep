package DuAnTotNghiep.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DuAnTotNghiep.entity.Likes;
import DuAnTotNghiep.service.LikeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/likes")
public class LikesRestController {

	@Autowired
	LikeService likeservice;
	
	@GetMapping()
	public List<Likes> getAll() {
		return likeservice.findAll();
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		likeservice.deleteById(id);
	}
	@PostMapping
	public Likes create(@RequestBody Likes like) {
		return likeservice.create(like);
	}
}
