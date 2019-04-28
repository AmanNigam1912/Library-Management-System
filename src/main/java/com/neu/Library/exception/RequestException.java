package com.neu.Library.exception;

public class RequestException extends Exception{
	public RequestException(String message)
	{
		super("BookException-"+message);
	}
	
	public RequestException(String message, Throwable cause)
	{
		super("BookException-"+message,cause);
	}
}
