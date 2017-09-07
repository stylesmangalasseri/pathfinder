package com.tel.pathfinder.controller;

import com.tel.pathfinder.logger.Logger;
import com.tel.pathfinder.services.NodeServices;
import com.tel.pathfinder.services.RouteMapService;
import com.tel.pathfinder.services.impl.NodeServicesImpl;
import com.tel.pathfinder.services.impl.RouteMapServiceImpl;

/**
 * Controller class to handle A START algorithm to find shortest path from start to destination
 * 
 * @author styles mangalasseri
 * 
 */
public class AstarPathFinderExecutor {

	/**
	 * instance of {@link Logger} class to handle all the logging services
	 */
	private final static Logger LOGGER = Logger.getLogger();
	/**
	 * Instance of {@link RouteMapServiceImpl} class to handle all the route map services
	 */
	private final RouteMapService routeMapService = new RouteMapServiceImpl();
	/**
	 * instance of {@link NodeServices} to handle all the node related services
	 */
	private final NodeServices nodeService = new NodeServicesImpl();

	/**
	 * Method to find shortest route form route to destination using A START Algorithm
	 * 
	 * @param routeMapInputFilePath
	 *            input map file path
	 * @param routeMapOutpuFilePath
	 *            output map file path
	 */
	public void findShortedPath() {

		// map details
		char[][] mapDetails = null;

		try {
			// read all available map entries
			mapDetails = routeMapService.readRouteMapDetails();
			// search and find navigation route
			mapDetails = nodeService.getNavigationRoute(mapDetails);
			// update map with navigation route
			routeMapService.writeRouteMap(mapDetails);
		} catch (Exception exception) {
			LOGGER.error("ERROR: " + exception.getMessage());
		}
	}
}
