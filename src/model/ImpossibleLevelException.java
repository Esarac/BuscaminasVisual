package model;

import java.lang.Exception;

public class ImpossibleLevelException extends Exception{
	
	public ImpossibleLevelException(){
		super();
	}
	
	public ImpossibleLevelException(String message){
		super(message);
	}
	
}
