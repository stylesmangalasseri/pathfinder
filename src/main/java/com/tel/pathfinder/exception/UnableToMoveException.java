package com.tel.pathfinder.exception;

/**
 * Custom Exception to throw if the movement is blocked
 * 
 * @author styles mangalasseri
 * 
 */
public class UnableToMoveException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnableToMoveException(String message) {
		super(message);
	}
}
