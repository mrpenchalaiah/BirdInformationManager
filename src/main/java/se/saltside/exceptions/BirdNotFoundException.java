package se.saltside.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BirdNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public BirdNotFoundException(String message){
		super(message);
	}

}
