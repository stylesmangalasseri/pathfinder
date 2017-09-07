package com.tel.pathfinder.logger;

/**
 * Class to handle logging methods
 * 
 * @author styles mangalasseri
 * 
 */
public class Logger {

	// instance of logger
	private static Logger logger = null;

	/**
	 * Constructor
	 */
	private Logger() {
	}

	/**
	 * Method to retrieve logger instance
	 * 
	 * @return instance of {@link Logger}
	 */
	public static Logger getLogger() {
		if (logger == null) {
			logger = new Logger();
		}
		return logger;
	}

	/**
	 * log info messages
	 * 
	 * @param message
	 *            message to log
	 */
	public void info(String message) {
		System.out.println(message);
	}

	/**
	 * log error message
	 * 
	 * @param message
	 *            message to log
	 */
	public void error(String message) {
		System.err.println(message);
	}
}
