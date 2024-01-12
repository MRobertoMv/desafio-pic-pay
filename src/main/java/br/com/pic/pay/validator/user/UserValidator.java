package br.com.pic.pay.validator.user;

import br.com.pic.pay.model.user.UserDTO;

public interface UserValidator {

	boolean validate(UserDTO user);
}
