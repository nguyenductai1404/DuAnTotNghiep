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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DuAnTotNghiep.entity.Catesmall;
import DuAnTotNghiep.entity.Cmtproduct;
import DuAnTotNghiep.entity.Cmtstore;
import DuAnTotNghiep.entity.Image;
import DuAnTotNghiep.entity.Introduce;
import DuAnTotNghiep.entity.Likes;
import DuAnTotNghiep.entity.Product;
import DuAnTotNghiep.entity.Specification;
import DuAnTotNghiep.entity.Store;
import DuAnTotNghiep.service.CatesmallService;
import DuAnTotNghiep.service.CmtproductService;
import DuAnTotNghiep.service.CmtstoreService;
import DuAnTotNghiep.service.ImagesService;
import DuAnTotNghiep.service.IntroduceService;
import DuAnTotNghiep.service.LikeService;
import DuAnTotNghiep.service.ProductService;
import DuAnTotNghiep.service.SpecificationService;
import DuAnTotNghiep.service.StoreService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	CatesmallService catesmallService;
	@Autowired
	LikeService likeService;
	@Autowired
	HttpServletRequest req;
	@Autowired
	SpecificationService specificationService;
	@Autowired
	ImagesService imagesService;
	@Autowired
	CmtproductService cmtproductService;
	@Autowired
	IntroduceService introduceService;
	@Autowired
	StoreService storeservice;
	@Autowired
	CmtstoreService cmtstoreService;

	@RequestMapping("/product/list")
	public String list(Model m, @RequestParam("p") Optional<Integer> p, @RequestParam("cid") Optional<String> cid) {
		try {
			if (req.getRemoteUser() != null) {
				String username = req.getRemoteUser();
				List<Integer> like = likeService.findUsername(username);
				m.addAttribute("like", like);

				Pageable pa = PageRequest.of(p.orElse(0), 2);
				Page<Likes> list = likeService.findByUser(username, pa);
				int t = list.getTotalPages();
				if(list.getNumber() != 0) {
					if (list.getNumber() == t) {
						return "redirect:/product/list?p=0";
					}
				}
				m.addAttribute("items", list);
			}
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/product/list?p=0";
		}
		return "/product/like";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(Model m, @PathVariable("id") Integer id, @RequestParam("cid") Optional<String> cid) {
		if (req.getRemoteUser() != null) {
			String username = req.getRemoteUser();
			List<Integer> like = likeService.findUsername(username);
			m.addAttribute("like", like);
		}
		Product item = productService.findById(id);
		m.addAttribute("items", item);
		List<Specification> spe = specificationService.findByIdProduct(id);
		m.addAttribute("spe", spe);
		List<Product> list = productService.findByCategoryId(cid.get());
		m.addAttribute("image", list);
		List<Image> image = imagesService.findByIdProduct(id);
		m.addAttribute("images", image);
		List<Cmtproduct> cmtproduct = cmtproductService.findByIdProduct(id);
		m.addAttribute("cmtproduct", cmtproduct);
		List<Introduce> introduce = introduceService.findByIdProduct(id);
		m.addAttribute("introduce", introduce);
		return "/product/detail";
	}
	
	@RequestMapping("/product/store/{id}")
	public String store(Model m, @PathVariable("id") Integer id) {
		if (req.getRemoteUser() != null) {
			String username = req.getRemoteUser();
			List<Integer> like = likeService.findUsername(username);
			m.addAttribute("like", like);
		}
		List<Product> item = productService.findByStoreId(id);
		m.addAttribute("items", item);
		List<Cmtstore> cmtstore = cmtstoreService.findByIdStore(id);
		m.addAttribute("cmtstore", cmtstore);
		Store store = storeservice.findById(id);
		m.addAttribute("store", store);
		return "/product/store";
	}

	@RequestMapping("/products/cate/{name}")
	public String cates(Model m, @PathVariable("name") String name, @RequestParam("cateid") Optional<String> cateid) {
		if (req.getRemoteUser() != null) {
			String username = req.getRemoteUser();
			List<Integer> like = likeService.findUsername(username);
			m.addAttribute("like", like);
		}
		List<Catesmall> list1 = catesmallService.findByCate(cateid.get());
		m.addAttribute("catesmall", list1);
		List<Product> list = productService.findByCateNameAndCateId(name, cateid.get());
		m.addAttribute("items", list);
		return "/product/listsp";
	}

	@RequestMapping("/products/tkname")
	public String getName(Model m, @RequestParam("name") String name) {
		if (req.getRemoteUser() != null) {
			String username = req.getRemoteUser();
			List<Integer> like = likeService.findUsername(username);
			m.addAttribute("like", like);
		}
		if(name != "") {
			List<Product> list = productService.findByName("%" + name + "%");
			m.addAttribute("items", list);
		}
		return "/product/list";
	}

	@RequestMapping("/products/tkprice")
	public String search(Model m, @RequestParam("min") Optional<Double> min,
			@RequestParam("max") Optional<Double> max) {
		if (req.getRemoteUser() != null) {
			String username = req.getRemoteUser();
			List<Integer> like = likeService.findUsername(username);
			m.addAttribute("like", like);
		}
		double minPrice = min.orElse(Double.MIN_VALUE);
		double maxPrice = max.orElse(Double.MAX_VALUE);
		List<Product> list = productService.findByPriceBetween(minPrice, maxPrice);
		m.addAttribute("items", list);
		return "/product/list";
	}
}
