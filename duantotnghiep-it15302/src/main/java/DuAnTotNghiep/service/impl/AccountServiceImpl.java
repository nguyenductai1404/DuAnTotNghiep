package DuAnTotNghiep.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DuAnTotNghiep.MailService;
import DuAnTotNghiep.SessionService;
import DuAnTotNghiep.dao.AccountDao;
import DuAnTotNghiep.dao.CodedmkDao;
import DuAnTotNghiep.dto.forgot;
import DuAnTotNghiep.entity.Account;
import DuAnTotNghiep.entity.Codedmk;
import DuAnTotNghiep.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao adao;
	@Autowired
	CodedmkDao cdao;
	@Autowired
	MailService mail;
	@Autowired
	SessionService session;

	@Override
	public Account findById(String username) {
		return adao.findById(username).get();
	}

	@Override
	public List<Account> findAll() {
		return adao.findAll();
	}

	@Override
	public Account create(Account account) {
		return adao.save(account);
	}

	@Override
	public Account update(Account account) {
		return adao.save(account);
	}

	@Override
	public void delete(String username) {
		adao.deleteById(username);
	}

	@Override
	public Account update(forgot forgot) {
		// TODO Auto-generated method stub
		Account acc = adao.findById(session.get("sUser")).get();
		List<Codedmk> dmk = cdao.findUsername(acc.getUsername());
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		String password = sb.toString();
		
		Date now = new Date();

		for(int i = 0; i < dmk.size(); i++) {
			System.err.println(dmk.get(i).getDate().getTime());
			if(dmk.get(i).getDate().getTime()+900000 > now.getTime()) {
				if(dmk.get(i).getCode().equals(forgot.getCodeqmk()) && dmk.get(i).isTrangthai()) {
					acc.setPassword(password);
					try {
						mail.send(acc.getEmail(), "Mật khẩu mới của bạn", password);
					} catch (Exception e) {
						// TODO: handle exception
					}
					cdao.deleteById(dmk.get(i).getId());
				}else {
					try {
						mail.send(acc.getEmail(), "Mật khẩu mới của bạn", "Code đã hết hạn sử dụng. Mời bạn thử lại !!!");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}else {
				dmk.get(i).setTrangthai(false);
				cdao.saveAll(dmk);
			}
		}
		
		return adao.save(acc);
	}

	@Override
	public List<Account> findByName(String name) {
		// TODO Auto-generated method stub
		return adao.findByName(name);
	}

}
