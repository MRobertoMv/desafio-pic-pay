package br.com.pic.pay.validator.user;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.pic.pay.exception.AlReadyExistsException;
import br.com.pic.pay.model.user.UserDTO;
import br.com.pic.pay.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(2)
public class UniqueDocumentUserValidator implements UserValidator {
	
	private final UserRepository usrRepository;

	@Override
	public boolean validate(UserDTO user) {
		if(usrRepository.findByDocument(user.getDocument()).isPresent()) {
			throw new AlReadyExistsException("Já existe usuário com esse documento");
		}
		return true;
	}

}
