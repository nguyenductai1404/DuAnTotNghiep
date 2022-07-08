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

import DuAnTotNghiep.entity.Codesale;
import DuAnTotNghiep.service.CodeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/code")
public class CodeRestController {

	@Autowired
	CodeService codeservice;

	@GetMapping()
	public List<Codesale> getAll() {
		return codeservice.findAll();
	}
	
	@GetMapping("{user}")
	public List<Codesale> getByUser(@PathVariable("user") String user) {
		return codeservice.findByUser(user);
	}
	
	@PostMapping
	public Codesale create(@RequestBody Codesale codesale) {
		return codeservice.create(codesale);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		codeservice.delete(id);
	}
}
