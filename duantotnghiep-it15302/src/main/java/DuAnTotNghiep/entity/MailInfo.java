package DuAnTotNghiep.entity;

import java.io.File;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {

	String from = "Sang <taolasang2k1@gmail.com>";
	String to;
	List<String> cc;
	List<String> bcc;
	String subject;
	String body;
	List<File> files;
	
	public MailInfo(String to, String sbj, String body) {
		this.to = to;
		this.subject = sbj;
		this.body = body;
	}
}
