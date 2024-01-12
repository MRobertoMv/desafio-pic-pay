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
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/user/shop-keeper")
@RequiredArgsConstructor
public class UserShopKeeperController {

	private final UserService usrService;

	@Operation(summary = "Criar usuário lojista")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário criado com sucesso", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserResponseDTO> save(
			@RequestBody @NotNull(message = "Requisição inválida") UserRequestDTO user) {
		return ResponseEntity.ok(usrService.save(user, UserType.SHOP_KEEPER));
	}

	@Operation(summary = "Obter usuário lojista por CPF")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário lojista obtido com sucesso", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
	@GetMapping(path = "/{cnpj}")
	public ResponseEntity<UserResponseDTO> getByDocument(@PathVariable String cnpj) {
		return ResponseEntity.ok(usrService.getByDocument(cnpj));
	}
}
