package it.sincrono.controllers.beans.a;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.StreamingHttpOutputMessage.Body;

public class Esito implements Body{
	
	protected String message;
	
	public Esito() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Esito(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void writeTo(OutputStream outputStream) throws IOException {
		
	}
	
	
	

}
