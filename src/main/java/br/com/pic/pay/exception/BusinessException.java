package br.com.pic.pay.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends GeneralException {

	private static final long serialVersionUID = 3590081621173771760L;
	
	public BusinessException() {
        super();
        super.httpStatus = this.httpStatus;
        super.title = this.title;
    }
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    public BusinessException(String message) {
        super(message);
    }
    public BusinessException(Throwable cause) {
        super(cause);
    }
	
	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
	
    @Override
    public String getTitle() {
    	return "Requisição inválida.";
    }

}
