package br.com.pic.pay.transform.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.pic.pay.controller.user.request.UserRequestDTO;
import br.com.pic.pay.controller.user.response.UserResponseDTO;
import br.com.pic.pay.enums.user.UserType;
import br.com.pic.pay.model.user.UserDTO;
import br.com.pic.pay.repository.user.UserEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserTransform {
	
	private final ModelMapper modelMapper;
	
	public UserDTO from(UserRequestDTO user, UserType type) {
		UserDTO dtoResponse = modelMapper.map(user, UserDTO.class);
		dtoResponse.setType(type);
		return dtoResponse; 
	}

	public UserResponseDTO from(UserEntity userEntity) {
//		return modelMapper.map(userEntity, UserResponseDTO.class);
		return new UserResponseDTO(userEntity.getUuid(), userEntity.getFullName(), userEntity.getEmail(), userEntity.getDocument(), userEntity.getType());
				
	}

	public UserEntity from(UserDTO userDTO) {
		return modelMapper.map(userDTO, UserEntity.class);
	}

}
