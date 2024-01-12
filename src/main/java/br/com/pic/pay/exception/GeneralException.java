package br.com.pic.pay.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralException extends RuntimeException {

	private static final long serialVersionUID = 945582608338961420L;
	
	protected HttpStatus httpStatus;
    
    protected String title;
    
	public GeneralException() {
        super();
    }
    public GeneralException(String message, Throwable cause) {
        super(message, cause);
    }
    public GeneralException(String message) {
        super(message);
    }
    public GeneralException(Throwable cause) {
        super(cause);
    }

}
