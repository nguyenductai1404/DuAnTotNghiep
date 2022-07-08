package DuAnTotNghiep.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DuAnTotNghiep.MailService;
import DuAnTotNghiep.entity.Catesmall;
import DuAnTotNghiep.entity.Product;
import DuAnTotNghiep.entity.tintuc;
import DuAnTotNghiep.service.CatesmallService;
import DuAnTotNghiep.service.CmtproductService;
import DuAnTotNghiep.service.ImagesService;
import DuAnTotNghiep.service.LikeService;
import DuAnTotNghiep.service.ProductService;
import DuAnTotNghiep.service.SpecificationService;
import DuAnTotNghiep.service.tintucService;

@Controller
public class HomeController {

	@Autowired
	HttpServletRequest request;
	@Autowired
	MailService mail;
	@Autowired
	ProductService productService;
	@Autowired
	CatesmallService catesmallService;
	@Autowired
	LikeService likeService;
	@Autowired
	SpecificationService specificationService;
	@Autowired
	ImagesService imagesService;
	@Autowired
	CmtproductService cmtproductService;
	@Autowired
	tintucService tintucService;

	@RequestMapping("/security/dangky")
	public String dangky() {
		return "layout/_registration";
	}

	@RequestMapping("/home/index")
	public String home(Model m, @RequestParam("p") Optional<Integer> p, @RequestParam("cid") Optional<String> cid) {
		try {
			if (request.getRemoteUser() != null) {
				String username = request.getRemoteUser();
				List<Integer> like = likeService.findUsername(username);
				m.addAttribute("like", like);
			}
			if (cid.isPresent()) {
				List<Product> list = productService.findByCategoryId(cid.get());
				m.addAttribute("items", list);
				List<Catesmall> list1 = catesmallService.findByCate(cid.get());
				m.addAttribute("catesmall", list1);
				return "/product/listsp";
			} else {
				Pageable pa = PageRequest.of(p.orElse(0), 8);
				Page<Product> list = productService.findAvailable(pa);
				int t = list.getTotalPages();
				if (list.getNumber() == t) {
					return "redirect:/home/index?p=0";
				}
				m.addAttribute("items", list);
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/home/index?p=0";
		}
		return "layout/_sanpham";
	}

	@RequestMapping("/home/contact")
	public String contact() {
		return "layout/contact";
	}

	@RequestMapping("/home/hotro")
	public String hotro() {
		return "layout/hotro";
	}
	
	@RequestMapping("/home/huongdan")
	public String huongdan() {
		return "layout/huongdan";
	}

	@RequestMapping("/account/edit")
	public String edit() {
		return "layout/capnhattk";
	}

	@RequestMapping("/home/gioithieu")
	public String gioithieu() {
		return "layout/gioithieu";
	}

	@RequestMapping("/account/quenmk")
	public String quemk() {
		return "layout/quenmk";
	}

	@RequestMapping("/account/doimk")
	public String doimk() {
		return "layout/doimk";
	}

	@RequestMapping("/order/lsmua")
	public String lsmua() {
		return "order/lsmuahang";
	}

	@RequestMapping({ "/admin", "/admin/home/index" })
	public String admin(Model m) {
		String name = request.getRemoteUser();
		m.addAttribute("name", name);
		return "redirect:/admin/indexAdmin.html";
	}

	@RequestMapping("/store/home/index")
	public String store(Model m) {
		return "redirect:/store/indexStore.html";
	}

	@RequestMapping("/account/individual")
	public String individual() {
		return "/layout/individual";
	}

	@RequestMapping("/codeqmk")
	public String codeqmk() {
		return "/layout/code";
	}
	
	@RequestMapping("/home/tintuc")
	public String tintuc(Model m) {
		List<tintuc> item = tintucService.findAll();
		m.addAttribute("items", item);
		return "layout/show_post";
	}
	
	@RequestMapping("/tintuc/post")
	public String post() {
		return "layout/post_bai";
	}
}
