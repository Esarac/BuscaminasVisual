package model;

import java.lang.Exception;

public class ImpossibleCoordinateException extends Exception{

	public ImpossibleCoordinateException(){
		super();
	}
	
	public ImpossibleCoordinateException(String message){
		super(message);
	}
	
}
