package DuAnTotNghiep.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DuAnTotNghiep.dto.forgot;
import DuAnTotNghiep.entity.Account;
import DuAnTotNghiep.entity.Codedmk;
import DuAnTotNghiep.entity.Product;
import DuAnTotNghiep.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {

	@Autowired AccountService accountService;
	@Autowired HttpServletRequest request;
	
	@GetMapping("/load")
	public List<Account> getAll(){
		return accountService.findAll();
	}
	@GetMapping("/user")
	public Map<String, Object> getAuthorities(){
		Map<String, Object> data = new HashMap<>();
		data.put("user", request.getRemoteUser());
		return data;
	}
	@PostMapping
	public Account create(@RequestBody Account account) {
		return accountService.create(account);
	}
	@PutMapping("{username}")
	public Account update(@PathVariable("username") String username, @RequestBody Account account) {
		return accountService.update(account);
	}
	@PutMapping("/forgot")
	public Account update1(@RequestBody forgot forgot) {
		return accountService.update(forgot);
	}
	@DeleteMapping("{username}")
	public void delete(@PathVariable("username") String username) {
		accountService.delete(username);
	}
	@GetMapping("{id}")
	public Account getOne(@PathVariable("id") String id) {
		return accountService.findById(id);
	}
	
	@GetMapping("/timkiem/{name}")
	public List<Account> getName(@PathVariable("name") String name) {
		return accountService.findByName("%" + name + "%");
	}
}
