package DuAnTotNghiep.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.MailService;
import DuAnTotNghiep.SessionService;
import DuAnTotNghiep.dao.AccountDao;
import DuAnTotNghiep.dao.CodedmkDao;
import DuAnTotNghiep.entity.Account;
import DuAnTotNghiep.entity.Codedmk;
import DuAnTotNghiep.service.CodeqmkService;

@Service
public class CodeqmkServiceImpl implements CodeqmkService {

	@Autowired
	CodedmkDao cdao;
	@Autowired
	AccountDao adao;
	@Autowired
	MailService mail;
	@Autowired
	SessionService session;
	
	@Override
	public Codedmk create(Codedmk codedmk) {
		Account acc = adao.findById(codedmk.getAccount().getUsername()).get();
		session.set("sUser", acc.getUsername());
		try {
			mail.send(acc.getEmail(), "Mã code của bạn", codedmk.getCode());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cdao.save(codedmk);
	}

}
