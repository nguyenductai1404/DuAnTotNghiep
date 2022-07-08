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
import DuAnTotNghiep.entity.Cmtstore;
import DuAnTotNghiep.service.CmtstoreService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/cmtstore")
public class CmtstoreRestController {

	@Autowired
	CmtstoreService cmtstoreservice;

	@GetMapping()
	public List<Cmtstore> getAll() {
		return cmtstoreservice.findAll();
	}
	
	@GetMapping("{id}")
	public List<Cmtstore> getById(@PathVariable("id") Integer id) {
		return cmtstoreservice.findByIdStore(id);
	}

	@PostMapping
	public Cmtstore create(@RequestBody Cmtstore cmtstore) {
		return cmtstoreservice.create(cmtstore);
	}
}
