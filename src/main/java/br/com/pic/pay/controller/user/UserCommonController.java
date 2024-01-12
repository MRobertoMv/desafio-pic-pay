package br.com.pic.pay.controller.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pic.pay.controller.user.request.UserRequestDTO;
import br.com.pic.pay.controller.user.response.UserResponseDTO;
import br.com.pic.pay.enums.user.UserType;
import br.com.pic.pay.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/user/common")
@RequiredArgsConstructor
public class UserCommonController {
	
	private final UserService usrService;

	@Operation(summary = "Criar usuário comum")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso", 
	    content = { @Content(mediaType = "application/json") }),
	    @ApiResponse(
	            responseCode = "400", description = "Requisição inválida"
	            ,content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
	  })	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserResponseDTO>  save(@RequestBody @NotNull(message = "Requisição inválida") @Valid UserRequestDTO user) {
		return ResponseEntity.ok(usrService.save(user, UserType.COMMON));
	}
	
	@Operation(summary = "Obter usuário comum por CPF")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Usuário obtido com sucesso", 
	    content = { @Content(mediaType = "application/json") }),
	    @ApiResponse(
	            responseCode = "400", description = "Requisição inválida"
	            ,content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
	    @ApiResponse(
	            responseCode = "404", description = "Usuário não encontrado"
	            ,content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
	  })	
	@GetMapping(path = "/{cpf}")
	public ResponseEntity<UserResponseDTO>  getByDocument(@PathVariable String cpf) {
		return ResponseEntity.ok(usrService.getByDocument(cpf));
	}
}
