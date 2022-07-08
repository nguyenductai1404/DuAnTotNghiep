package DuAnTotNghiep.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DuAnTotNghiep.dao.AccountDao;
import DuAnTotNghiep.dao.AuthorityDao;
import DuAnTotNghiep.dao.RoleDao;
import DuAnTotNghiep.entity.Authority;
import DuAnTotNghiep.service.AuthorityService;

@CrossOrigin("*")
@RestController
public class AuthorityRestController {

	@Autowired AuthorityDao authrityDao;
	@Autowired RoleDao roleDao;
	@Autowired AccountDao accountDao;
	@Autowired HttpServletRequest request;
	@Autowired AuthorityService authorityService;
	
	@RequestMapping("/rest/authorities")
	public Map<String, Object> getAuthorities(){
		Map<String, Object> data = new HashMap<>();
		data.put("authorities", authrityDao.findAll());
		data.put("roles", roleDao.findAll());
		data.put("accounts", accountDao.findAll());
		data.put("user", request.getRemoteUser());
		return data;
	}
	@DeleteMapping("/rest/authorities/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authrityDao.deleteById(id);
	}
	@PostMapping("/rest/authority")
	public Authority save(@RequestBody Authority authority) {
		return authorityService.create(authority);
	}
}
