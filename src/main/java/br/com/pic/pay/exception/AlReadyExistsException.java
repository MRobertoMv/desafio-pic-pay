package br.com.pic.pay.exception;

import org.springframework.http.HttpStatus;

public class AlReadyExistsException extends GeneralException {

	private static final long serialVersionUID = 3590081621173771760L;
	
	public AlReadyExistsException() {
        super();
        super.httpStatus = this.httpStatus;
        super.title = this.title;
    }
    public AlReadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public AlReadyExistsException(String message) {
        super(message);
    }
    public AlReadyExistsException(Throwable cause) {
        super(cause);
    }
	
	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}
	
    @Override
    public String getTitle() {
    	return "Registro já existe.";
    }

}
