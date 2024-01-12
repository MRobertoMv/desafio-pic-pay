package br.com.pic.pay.service.user;

import org.springframework.stereotype.Service;

import br.com.pic.pay.controller.user.request.UserRequestDTO;
import br.com.pic.pay.controller.user.response.UserResponseDTO;
import br.com.pic.pay.enums.user.UserType;
import jakarta.validation.Valid;

@Service
public interface UserService {
	UserResponseDTO save(@Valid UserRequestDTO user, UserType common);

	UserResponseDTO getByDocument(String cpf);
}
