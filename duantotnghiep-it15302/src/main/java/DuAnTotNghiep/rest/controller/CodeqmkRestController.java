package DuAnTotNghiep.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DuAnTotNghiep.entity.Codedmk;
import DuAnTotNghiep.service.CodeqmkService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/codeqmk")
public class CodeqmkRestController {

	@Autowired CodeqmkService codeqmkService;
	@Autowired HttpServletRequest request;
	
	@PostMapping
	public Codedmk create(@RequestBody Codedmk codedmk) {
		return codeqmkService.create(codedmk);
	}
}
