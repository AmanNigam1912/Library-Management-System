package com.neu.Library.exception;

public class LibraryUserException extends Exception {
	
	public LibraryUserException (String message)
	{
		super("LibraryUserException-"+message);
	}
	
	public LibraryUserException (String message, Throwable cause)
	{
		super("LibraryUserException-"+message,cause);
	}
	
}
