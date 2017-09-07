package com.tel.pathfinder.services;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class which contains all the operations with route map
 * 
 * @author styles mangalasseri
 * 
 */
public interface RouteMapService {

	/**
	 * Method to read route map details
	 * 
	 * @return route map details read from map. Each entries from the route map are mapping in two dimension array in
	 *         the form of [row_value][column_value]
	 * @throws FileNotFoundException
	 *             if route map is not available
	 * @throws IOException
	 *             during IO Operation failures
	 * 
	 */
	char[][] readRouteMapDetails() throws FileNotFoundException, IOException;

	/**
	 * Method to write route map to file
	 * 
	 * @param mapDetails
	 *            current map details.Each entries from the route map are mapping in two dimension array in the form of
	 *            [row_value][column_value]
	 * @throws IOException
	 *             if IO operation is failed
	 */
	void writeRouteMap(final char[][] mapDetails) throws IOException;
}
