package br.com.pic.pay.controller.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * public record UserRequestDTO(
 * 
 * @NotNull(message = "Nome completo não informado")
 * 
 * @NotBlank(message = "Nome completo inválido")
 * 
 * @Size(min = 5, max = 255, message =
 * "Nome completo, tamanho inválido, entre 5 e 255 caracteres") String fullName,
 * 
 * @NotNull(message = "Documento não informado")
 * 
 * @NotBlank(message = "Documento inválido")
 * 
 * @Size(min = 11, max = 14, message =
 * "Documento, tamanho inválido, entre 11 (CPf) e 14 (CNPJ) caracteres") String
 * document,
 * 
 * @NotNull(message = "Email não informado")
 * 
 * @Email(message = "Email inválido") String email,
 * 
 * @NotNull(message = "Senha não informada")
 * 
 * @NotBlank(message = "Senha inválida")
 * 
 * @Size(min = 8, max = 30, message =
 * "Senha, tamanho inválido, entre 8 e 30 caracteres") String pwd ) {
 * 
 * }
 * 
 * Resolved [org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public org.springframework.http.ResponseEntity<br.com.pic.pay.controller.user.response.UserResponseDTO> br.com.pic.pay.controller.user.UserCommonController.save(br.com.pic.pay.controller.user.request.UserRequestDTO): [Field error in object 'userRequestDTO' on field 'email': rejected value [string]; codes [Email.userRequestDTO.email,Email.email,Email.java.lang.String,Email]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [userRequestDTO.email,email]; arguments []; default message [email],[Ljakarta.validation.constraints.Pattern$Flag;@6ff35712,.*]; default message [Email inválido]]
 */

@Builder
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

	@NotNull(message = "Nome completo não informado")
	@NotBlank(message = "Nome completo inválido")
	@Size(min = 5, max = 255, message = "Nome completo, tamanho inválido, entre 5 e 255 caracteres")
	private String fullName;

	@NotNull(message = "Documento não informado")
	@NotBlank(message = "Documento inválido")
	@Size(min = 11, max = 14, message = "Documento, tamanho inválido, entre 11 (CPf) e 14 (CNPJ) caracteres")
	private String document;

	@NotNull(message = "Email não informado")
	@Email(message = "Email inválido")
	private String email;

	@NotNull(message = "Senha não informada")
	@NotBlank(message = "Senha inválida")
	@Size(min = 8, max = 30, message = "Senha, tamanho inválido, entre 8 e 30 caracteres")
	private String pwd;

}
