package br.com.pic.pay.exception;

import java.time.Instant;
import java.util.Optional;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
	private String timestamp = Instant.now().toString();
	private HttpStatus httpStatus = null;
	private Integer errorCode = null;
	private String title = null;
	private String message = null;
	private Object details = null;

	public ApiErrorResponse(HttpStatus httpStatus, Integer errorCode, String title, String message, Object details) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.title = title;
		this.message = message;
		this.details = details;
	}

}
