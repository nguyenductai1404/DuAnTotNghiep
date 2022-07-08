package DuAnTotNghiep.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DuAnTotNghiep.entity.Category;
import DuAnTotNghiep.entity.Catesmall;
import DuAnTotNghiep.service.CategoryService;
import DuAnTotNghiep.service.CatesmallService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/catesmall")
public class CateSmallRestController {
	@Autowired CatesmallService catesmallService;
	
	@GetMapping()
	public List<Catesmall> getAll(){
		return catesmallService.findAll();
	}
}
