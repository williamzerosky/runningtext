package com.william.runingtext.exception;

public class TextNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public TextNotFoundException(String msg) {
		super(msg);
	}
	
	public TextNotFoundException(String msg,Throwable t) {
		super(msg,t);
	}
}
