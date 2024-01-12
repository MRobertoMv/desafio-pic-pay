package br.com.pic.pay.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends GeneralException {

	private static final long serialVersionUID = 3590081621173771760L;
	
	public NotFoundException() {
        super();
        super.httpStatus = this.httpStatus;
        super.title = this.title;
    }
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(Throwable cause) {
        super(cause);
    }
	
	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.CONFLICT;
	}
	
    @Override
    public String getTitle() {
    	return "Registro não encontrado.";
    }

}
