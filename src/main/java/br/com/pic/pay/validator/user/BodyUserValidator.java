package br.com.pic.pay.validator.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.pic.pay.model.user.UserDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(1)
public class BodyUserValidator implements UserValidator {
	
	@Override
	public boolean validate(UserDTO user) {
		return user != null &&
				StringUtils.isNotBlank(user.getFullName()) &&
				StringUtils.isNotBlank(user.getDocument()) &&
				StringUtils.isNotBlank(user.getEmail()) &&
				StringUtils.isNotBlank(user.getPwd()) &&
				user.getType() != null;
				
	}

}
