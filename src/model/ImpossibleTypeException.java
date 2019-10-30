package model;

import java.lang.Exception;

public class ImpossibleTypeException extends Exception{
	
	public ImpossibleTypeException(){
		super();
	}
	
	public ImpossibleTypeException(String message){
		super(message);
	}
	
}
