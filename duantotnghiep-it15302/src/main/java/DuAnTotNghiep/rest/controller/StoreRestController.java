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

import DuAnTotNghiep.entity.Account;
import DuAnTotNghiep.entity.Product;
import DuAnTotNghiep.entity.Store;
import DuAnTotNghiep.service.StoreService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/store")
public class StoreRestController {

	@Autowired StoreService storeService;
	
	@GetMapping()
	public List<Store> getAll(){
		return storeService.findAll();
	}
	@GetMapping("{id}")
	public Store getOne(@PathVariable("id") Integer id) {
		return storeService.findById(id);
	}
	
	@GetMapping("/find/{username}")
	public Store getStore(@PathVariable("username") String username) {
		return storeService.findByStore(username);
	}
	
	@PostMapping
	public Store create(@RequestBody Store store) {
		return storeService.create(store);
	}

	@PutMapping("{id}")
	public Store update(@PathVariable("id") Integer id, @RequestBody Store store) {
		return storeService.update(store);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		storeService.delete(id);
	}
	
	@GetMapping("/timkiem/{name}")
	public List<Store> getName(@PathVariable("name") String name) {
		return storeService.findByName("%" + name + "%");
	}
}
