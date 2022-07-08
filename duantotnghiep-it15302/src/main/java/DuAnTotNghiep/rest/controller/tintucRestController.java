package DuAnTotNghiep.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DuAnTotNghiep.entity.Cmtproduct;
import DuAnTotNghiep.entity.Product;
import DuAnTotNghiep.entity.tintuc;
import DuAnTotNghiep.service.tintucService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/post")
public class tintucRestController {

	@Autowired
	tintucService tintucService;

	@GetMapping()
	public List<tintuc> getAll() {
		return tintucService.findAll();
	}
	
	@GetMapping("{user}")
	public List<tintuc> getByUser(@PathVariable("user") String user) {
		return tintucService.findByUser(user);
	}
	
	@PostMapping
	public tintuc create(@RequestBody tintuc tintuc) {
		return tintucService.create(tintuc);
	}
	
	@PutMapping("{id}")
	public tintuc update(@PathVariable("id") Integer id, @RequestBody tintuc tintuc) {
		return tintucService.update(tintuc);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		tintucService.delete(id);
	}
	@GetMapping("/timkiem/{name}")
	public List<tintuc> getName(@PathVariable("name") String name) {
		return tintucService.findByName("%" + name + "%");
	}
}
