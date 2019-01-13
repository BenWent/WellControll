package cn.cdtu.exceptions;

public class LoginException extends Exception{
	private static final long serialVersionUID = 0x5a928d2448e8baeL;
	
	public LoginException(String message){
		super(message);
	}
}
