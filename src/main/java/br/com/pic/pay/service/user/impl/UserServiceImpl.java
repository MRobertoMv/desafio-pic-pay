package br.com.pic.pay.service.user.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.pic.pay.controller.user.request.UserRequestDTO;
import br.com.pic.pay.controller.user.response.UserResponseDTO;
import br.com.pic.pay.enums.user.UserType;
import br.com.pic.pay.exception.NotFoundException;
import br.com.pic.pay.model.user.UserDTO;
import br.com.pic.pay.repository.user.UserEntity;
import br.com.pic.pay.repository.user.UserRepository;
import br.com.pic.pay.service.user.UserService;
import br.com.pic.pay.transform.user.UserTransform;
import br.com.pic.pay.utils.PwdUtils;
import br.com.pic.pay.validator.user.UserValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final List<UserValidator> validators;
	private final UserTransform usrTransform;
	private final UserRepository usrRepository;
	private final PwdUtils pwdUtils;

	@Override
	@Transactional
	public UserResponseDTO save(@Valid UserRequestDTO user, UserType common) {
		
		UserDTO userDTO = usrTransform.from(user, common);
		for(UserValidator v : validators) {
			v.validate(userDTO);
		}
		
		UserEntity userEntity = usrTransform.from(userDTO);
		
		userEntity.setPwd(pwdUtils.encrypt(userDTO.getPwd()));
		
		usrRepository.save(userEntity);
		
		return usrTransform.from(userEntity);
		 
	}

	@Override
	public UserResponseDTO getByDocument(String cpf) {
		
		Optional<UserEntity> usrOpt = usrRepository.findByDocument(cpf);
		if(usrOpt.isEmpty()) {
			throw new NotFoundException(String.format("Não existe usuário com o cpf informado: %s", cpf));
		}
		return usrTransform.from(usrOpt.get());
	}

}
