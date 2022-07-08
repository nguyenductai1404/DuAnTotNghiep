package DuAnTotNghiep.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DuAnTotNghiep.entity.Cmtproduct;
import DuAnTotNghiep.service.CmtproductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/cmtproduct")
public class CmtproductRestController {

	@Autowired
	CmtproductService cmtproductService;

	@GetMapping()
	public List<Cmtproduct> getAll() {
		return cmtproductService.findAll();
	}
	
	@GetMapping("{id}")
	public List<Cmtproduct> getById(@PathVariable("id") Integer id) {
		return cmtproductService.findByIdProduct(id);
	}

	@PostMapping
	public Cmtproduct create(@RequestBody Cmtproduct cmtproduct) {
		return cmtproductService.create(cmtproduct);
	}
}
