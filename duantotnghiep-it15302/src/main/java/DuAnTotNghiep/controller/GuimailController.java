package DuAnTotNghiep.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import DuAnTotNghiep.MailService;

@Controller
public class GuimailController {

	@Autowired
	HttpServletRequest req;
	@Autowired
	MailService mail;
	
	@RequestMapping("/hotro/guimail")
	public String hotro() {
		String ho = req.getParameter("ho");
		String ten = req.getParameter("ten");
		String email = "taolasang2k1@gmail.com";
		String sdt = req.getParameter("sdt");
		String tinnhan = req.getParameter("tinnhan");
		
		try {
			mail.send(email,"Mail Hỗ Trợ","Tên: "+ ho + " "+ ten + "<br>" + "Số điện thoại: " + sdt + "<br>" + "Tin nhắn: "+ tinnhan);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/home/hotro";
	}
	@RequestMapping("/lienhe/guimail")
	public String lienhe() {
		String ten = req.getParameter("ten");
		String email = "taolasang2k1@gmail.com";
		String tinnhan = req.getParameter("tinnhan");
		
		try {
			mail.send(email,"Mail Liên Hệ","Tên: "+ ten + "<br>" + "Tin nhắn: "+ tinnhan);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/home/contact";
	}
}
