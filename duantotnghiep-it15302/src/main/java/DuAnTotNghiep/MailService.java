package DuAnTotNghiep;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.entity.MailInfo;


@Service
public class MailService {
	@Autowired
	JavaMailSender sender;
	@Autowired
	ServletContext app;
	
	List<MimeMessage> hangDoi = new ArrayList<>();
	
	public void send(MailInfo mail) throws MessagingException {
		MimeMessage m = sender.createMimeMessage();
		MimeMessageHelper hepler = new MimeMessageHelper(m, true, "utf-8");
		
		hepler.setFrom(mail.getFrom());
		hepler.setTo(mail.getTo());
		hepler.setSubject(mail.getSubject());
		hepler.setText(mail.getBody(), true);
		
		hangDoi.add(m);
	}
	
	public void send(String to, String sbj, String body) throws MessagingException {
		MailInfo m = new MailInfo(to, sbj, body);
		this.send(m);
	}
	
	@Scheduled(fixedDelay = 10000)
	public void run() {
		int s = 0;
		int f = 0;
		if(!hangDoi.isEmpty()) {
			MimeMessage m = hangDoi.remove(0);
			try {
				sender.send(m);
				s++;
			} catch (Exception e) {
				e.printStackTrace();
				f++;
			}
		}
		System.out.println("Thành công "+ s +", Thất bại: "+ f);
	}
}
