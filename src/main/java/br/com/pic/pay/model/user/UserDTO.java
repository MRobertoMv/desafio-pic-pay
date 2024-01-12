package br.com.pic.pay.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.pic.pay.enums.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String fullName;
	private String document;
	private String email;
	private String pwd;
	private UserType type;
	
	private Double balance;

}
