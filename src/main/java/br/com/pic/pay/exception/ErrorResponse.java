package br.com.pic.pay.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	
	private  String timestamp = Instant.now().toString();
	private  HttpStatus httpStatus = null;
	private  Integer errorCode = null;
	private  String title = null;
	private  String message = null;
	private  Object details = null;

	public ErrorResponse(HttpStatus httpStatus, Integer errorCode, String title, String message, Object details) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.title = title;
		this.message = message;
		this.details = details;
	}
}
