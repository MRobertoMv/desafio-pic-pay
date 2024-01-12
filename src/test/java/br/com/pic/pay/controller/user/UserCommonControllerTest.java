package br.com.pic.pay.controller.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.pic.pay.controller.user.request.UserRequestDTO;
import br.com.pic.pay.controller.user.response.UserResponseDTO;
import br.com.pic.pay.enums.user.UserType;
import br.com.pic.pay.service.user.UserService;

@ExtendWith(SpringExtension.class)
public class UserCommonControllerTest {

	@InjectMocks
	private UserCommonController usrCommonController;
	
	@Mock
	private UserService usrService;
	
	private final UserType usrType = UserType.COMMON;
	
	@Test
	void saveOkTest() {
		
		UserRequestDTO user = new UserRequestDTO(
				"Usuario comum de teste", //fullName
				"497.324.040-00",  //document - cpf valid
				"teste@email.com", //email
				"pwd1!Testing" //pwd
				);
		
		UserResponseDTO usrRespMock = new UserResponseDTO(
				
				UUID.randomUUID().toString(), //uuid
				user.getFullName(), //fullName
				user.getEmail(), //email
				user.getDocument(), //document
				usrType //type
				);

		when(usrService.save(user, usrType)).thenReturn(usrRespMock);
		
		ResponseEntity<UserResponseDTO> usrResponseEntityOk = usrCommonController.save(user);
		
		assertNotNull(usrResponseEntityOk);
		
		assertNotNull(usrResponseEntityOk.getBody());
		
		assertEquals(HttpStatus.OK.value(), usrResponseEntityOk.getStatusCode().value());
		
		assertEquals(usrRespMock.uuid(), usrResponseEntityOk.getBody().uuid());
	}
}
