package br.com.pic.pay.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PwdUtils {
	
	public String encrypt(String pwd) {
		return BCrypt.hashpw(pwd, BCrypt.gensalt());
	}
	
	public boolean checkPwd(String pwd, String encriptedPwd) {
		return BCrypt.checkpw(pwd, encriptedPwd);
	}

}
