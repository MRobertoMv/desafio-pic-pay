package br.com.pic.pay.controller.user.response;

import br.com.pic.pay.enums.user.UserType;

public record UserResponseDTO(
		String uuid,
		String fullName,
		String email,
		String document,
		UserType type
		) {
}
