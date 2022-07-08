package DuAnTotNghiep.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DuAnTotNghiep.entity.Account;
import DuAnTotNghiep.entity.Image;
import DuAnTotNghiep.service.ImagesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/detals")
public class ImagesRestController {

	@Autowired
	ImagesService imagesService;
	
	@GetMapping("{id}")
	public Image getOne(@PathVariable("id") Integer id) {
		return imagesService.findById(id);
	}
}
