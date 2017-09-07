package com.tel.pathfinder.constants;

/**
 * Interface to handle all the commonly used constants
 * 
 * @author styles mangalasseri
 * 
 */
public interface CommonConstants {

	/**
	 * Symbol to mark navigation route
	 */
	char NAVIGATION_ROUTE = '#';
	/**
	 * system property name to retrieve travel map from user
	 */
	String PROPERTY_INPUT_FILE_NAME = "INPUT_FILE_NAME";
	/**
	 * system property name to write travel route map to user
	 */
	String PROPERTY_OUTPUT_FILE_NAME = "OUTPUT_FILE_NAME";

	/**
	 * Default value of the travel map
	 */
	String DEFAULT_INPUT_FILE_PATH = "map.txt";

	/**
	 * Default value of the travel map
	 */
	String DEFAULT_OUTPUT_FILE_PATH = "route_map.txt";

}
