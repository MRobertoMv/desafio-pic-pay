package br.com.pic.pay.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MSG_ERROR_FIELD_INVALID = "Requisição possui campo(s) inválido(s)";
	private static final String MSG_ERROR_HEADER_INVALID = "Requisição possui header(s) inválido(s)";
	private static final String TITLE_MSG_ERROR = "Não foi possível processar a solicitação";

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiErrorResponse> handleConstraintViolationException(HttpServletRequest request,
			ConstraintViolationException ex) {
		ApiErrorResponse errorResponse =getErrorResponse(ex);
		log.error("Error request: {}", errorResponse.toString());
		return ResponseEntity.badRequest().body(errorResponse);
	}
	
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.internalServerError().body(getErrorResponse(new SystemException(ex)));
	}
	
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return ResponseEntity.internalServerError().body(getErrorResponse(new SystemException(ex)));
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return ResponseEntity.badRequest().body(getErrorResponse(ex));
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<ApiErrorResponse> handleMissingRequestHeaderException(HttpServletRequest request,
			MissingRequestHeaderException ex) {
		ApiErrorResponse errorResponse =new ApiErrorResponse(HttpStatus.BAD_REQUEST,
				HttpStatus.BAD_REQUEST.value(), TITLE_MSG_ERROR,
				MSG_ERROR_HEADER_INVALID, 
				new StringBuilder().append("header: ").append(ex.getHeaderName()).append(" method: ").append(ex.getParameter()));
		
		log.error("Error request: {}", errorResponse.toString());
		return ResponseEntity.badRequest().body(errorResponse);
	}
	
	@ExceptionHandler(value = { SystemException.class })
	protected ResponseEntity<ApiErrorResponse> systemException(SystemException ex, WebRequest request) {
		return new ResponseEntity<>(getErrorResponse(ex), null, ex.httpStatus);
	}

	@ExceptionHandler(value = { BusinessException.class })
	protected ResponseEntity<ApiErrorResponse> businessException(BusinessException ex, WebRequest request) {
		return ResponseEntity.badRequest().body(getErrorResponse(ex));
	}

	@ExceptionHandler(value = { NotFoundException.class })
	protected ResponseEntity<ApiErrorResponse> notFoundException(NotFoundException ex, WebRequest request) {
		return new ResponseEntity<>(getErrorResponse(ex), null, ex.httpStatus);
	}

	@ExceptionHandler(value = { AlReadyExistsException.class })
	protected ResponseEntity<ApiErrorResponse> AlReadyExistsException(AlReadyExistsException ex, WebRequest request) {
		return new ResponseEntity<>(getErrorResponse(ex), null, ex.httpStatus);
	}
	
	private ApiErrorResponse getErrorResponse(GeneralException ex) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(ex.getHttpStatus(), ex.getHttpStatus().value(),
				ex.getTitle(), ex.getMessage(), Arrays.asList(ex.getLocalizedMessage()));

		log.error("Error request: {}", errorResponse.toString());

		return errorResponse;
	}

	private ApiErrorResponse getErrorResponse(ConstraintViolationException ex) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST,
				HttpStatus.BAD_REQUEST.value(), TITLE_MSG_ERROR,
				MSG_ERROR_FIELD_INVALID, 
				ex.getConstraintViolations().stream().map((c) -> c.getMessage())
				.collect(Collectors.toUnmodifiableList())
				);
		
		log.error("Error request: {}", errorResponse.toString());

		return errorResponse;
	}
	
	private List<String> getErrors(final MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.sorted((FieldError o1, FieldError o2) -> o1.getField().compareTo(o2.getField()))
				.map(error -> String.valueOf(error.getDefaultMessage())).collect(Collectors.toList());
	}
	
	private ApiErrorResponse getErrorResponse(final MethodArgumentNotValidException ex) {
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST,
				HttpStatus.BAD_REQUEST.value(), TITLE_MSG_ERROR,
				MSG_ERROR_FIELD_INVALID, 
				getErrors(ex));

		log.error("Error request: {}", apiErrorResponse.toString());

		return apiErrorResponse;
	}
}
