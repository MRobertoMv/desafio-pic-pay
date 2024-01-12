package br.com.pic.pay.exception;

import org.springframework.http.HttpStatus;

public class SystemException extends GeneralException {

	private static final long serialVersionUID = 3590081621173771760L;
	
	public SystemException() {
        super();
        super.httpStatus = this.httpStatus;
        super.title = this.title;
    }
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
    public SystemException(String message) {
        super(message);
    }
    public SystemException(Throwable cause) {
        super(cause);
    }
	
	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
    @Override
    public String getTitle() {
    	return "Sistema indisponível.";
    }

}
