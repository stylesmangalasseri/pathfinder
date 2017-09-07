package com.tel.pathfinder.services;

import java.util.List;

import com.tel.pathfinder.exception.UnableToMoveException;
import com.tel.pathfinder.logger.Logger;
import com.tel.pathfinder.navigation.Node;

/**
 * Interface for the node services
 * 
 * @author styles mangalasseri
 * 
 */
public interface NodeServices {

	/**
	 * logger
	 */
	Logger logger = Logger.getLogger();

	/**
	 * Method to retrieve route map from text file
	 * 
	 * @param mapDetails
	 *            map details
	 * @return route map in (row,column) format
	 */
	Node[][] retrieveMapDetails(final char[][] mapDetails);

	/**
	 * Method to retrieve start node from the map
	 * 
	 * @return start node details
	 */
	Node getStartNode();

	/**
	 * Method to retrieve destination node from the map
	 * 
	 * @return destination node details
	 */
	Node getDestinationNode();

	/**
	 * Method to update map details as navigation route
	 * 
	 * @param mapDetails
	 *            available map details
	 * @param navigationRoute
	 *            navigation node details
	 * @return map having navigation route
	 */
	char[][] updateNodeDetailsWithNavigationRoute(final char[][] mapDetails, final List<Node> navigationRoute);

	/**
	 * Method to retrieve navigation route from given map details
	 * 
	 * @param mapDetails
	 *            map details, each entries from the route map are mapping in two dimension array in the form of
	 *            [row_value][column_value]
	 * @return map details wich having the navigation route
	 */
	char[][] getNavigationRoute(final char[][] mapDetails) throws UnableToMoveException;
}
