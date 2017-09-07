package com.tel.pathfinder.exception;

import java.io.FileNotFoundException;

/**
 * Exception to throw if map file details not found
 * 
 * @author styles mangalasseri
 * 
 */
public class MapDetailsNotFoundException extends FileNotFoundException {

	private static final long serialVersionUID = 1L;

	public MapDetailsNotFoundException(String message) {
		super(message);
	}
}
