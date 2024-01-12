package br.com.pic.pay.validator.user;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.pic.pay.exception.AlReadyExistsException;
import br.com.pic.pay.model.user.UserDTO;
import br.com.pic.pay.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(3)
public class UniqueEmailUserValidator implements UserValidator {
	
	private final UserRepository usrRepository;

	@Override
	public boolean validate(UserDTO user) {
		if(usrRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new AlReadyExistsException("Já existe usuário com esse email");
		}
		return true;
	}

}
