package com.tel.pathfinder.services.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.tel.pathfinder.constants.CommonConstants;
import com.tel.pathfinder.exception.MapDetailsNotFoundException;
import com.tel.pathfinder.services.RouteMapService;

/**
 * Class which contains all the operations with route map file
 * 
 * @author styles mangalasseri
 * 
 */
public class RouteMapServiceImpl implements RouteMapService {

	/**
	 * Method to return input map file
	 * 
	 * @return input map file path
	 * @throws MapDetailsNotFoundException
	 */
	private String getRouteMapInputFile() throws MapDetailsNotFoundException {

		// read input map file path
		String inputFile = System.getProperty(CommonConstants.PROPERTY_INPUT_FILE_NAME);

		if (inputFile == null || inputFile.isEmpty()) {
			throw new MapDetailsNotFoundException("Please provide input map file path as VM Arguement. USAGE: -D"
					+ CommonConstants.PROPERTY_INPUT_FILE_NAME + "=<input_map_file_path>");
		}
		return inputFile;
	}

	/**
	 * Method to return output map file
	 * 
	 * @return output map file path
	 * @throws MapDetailsNotFoundException
	 */
	private String getRouteMapOutputFile() throws MapDetailsNotFoundException {

		// read output map file path
		String outputFile = System.getProperty(CommonConstants.PROPERTY_OUTPUT_FILE_NAME);

		if (outputFile == null || outputFile.isEmpty()) {
			throw new MapDetailsNotFoundException("Please provide output map file path as VM Arguement. USAGE: -D"
					+ CommonConstants.PROPERTY_OUTPUT_FILE_NAME + "=<output_map_file_path>");
		}

		return outputFile;
	}

	/**
	 * Method to retrieve input map file details
	 * 
	 * @param routeMapFilePath
	 *            input file
	 * @return input file reader
	 * @throws FileNotFoundException
	 *             if input file is not found
	 */
	private FileReader getRouteFileMapReader(final String routeMapFilePath) throws FileNotFoundException {
		return new FileReader(routeMapFilePath);
	}

	/**
	 * Method to initialize the array by finding total number of rows and columns for from route map
	 * 
	 * @param routeMapFilePath
	 *            input file
	 * @return nodes
	 * @throws FileNotFoundException
	 *             if route map is not available
	 * @throws IOException
	 *             during IO operation failures
	 */
	private char[][] initMapData(final String routeMapFilePath) throws FileNotFoundException, IOException {
		// total number of rows
		int rowCount = 0;
		// total number of columns
		int columnCount = 0;
		// current line
		String currentLine = null;
		// store all the node details which is available in the map
		char[][] nodeDetails = null;
		// instance of file reader
		FileReader fileReader = null;
		// instance of BufferedReader
		BufferedReader bufferedReader = null;

		try {
			try {
				// retrieve the input route map file
				fileReader = getRouteFileMapReader(routeMapFilePath);
				// instance of BufferedReader
				bufferedReader = new BufferedReader(fileReader);
				// read file contents line by line to
				while ((currentLine = bufferedReader.readLine()) != null) {
					// remove unwanted spaces
					currentLine = currentLine.trim();
					if (rowCount == 0) {
						columnCount = currentLine.length();
					}
					rowCount++;
				}
			} finally {
				// close the file reader
				if (fileReader != null) {
					fileReader.close();
				}
				// close the buffered reader
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
		} catch (FileNotFoundException fileNotFoundException) {
			throw fileNotFoundException;
		} catch (IOException ioException) {
			throw ioException;
		}

		// initializing the node details arrays with total row count and column
		// count
		nodeDetails = new char[rowCount][columnCount];

		return nodeDetails;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public char[][] readRouteMapDetails() throws FileNotFoundException, IOException {

		// row value of the character in map
		int rowValue = 0;
		// column value of the character in map
		int columnValue = 0;
		// retrieved node details
		char[][] mapDetails = null;
		// current line
		String currentLine = null;
		// instance of file reader used to read the file contents
		FileReader fileReader = null;
		// instance of buffered reader
		BufferedReader bufferedReader = null;
		// route map file
		final String routeMapFilePath = getRouteMapInputFile();
		// verify whether output file path is set or not
		getRouteMapOutputFile();

		try {
			try {
				// initialize node details by identifying total number of rows
				// and columns
				mapDetails = initMapData(routeMapFilePath);
				// retrieve file reader instance of the given route map
				fileReader = getRouteFileMapReader(routeMapFilePath);
				// instance of BufferedReader
				bufferedReader = new BufferedReader(fileReader);
				// read route map details line by line
				while ((currentLine = bufferedReader.readLine()) != null) {
					// remove unwanted spaces
					currentLine = currentLine.trim();
					for (columnValue = 0; columnValue < currentLine.length(); columnValue++) {
						// instantiating each node
						mapDetails[rowValue][columnValue] = currentLine.charAt(columnValue);
					}
					rowValue++;
				}
			} finally {
				// close the file reader
				if (fileReader != null) {
					fileReader.close();
				}
				// close the buffered reader
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
		} catch (FileNotFoundException fileNotFoundException) {
			throw fileNotFoundException;
		} catch (IOException ioException) {
			throw ioException;
		}

		return mapDetails;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeRouteMap(final char[][] mapDetails) throws IOException {
		// output file
		File file = null;
		// write instance
		FileWriter fileWriter = null;
		// writer instance
		BufferedWriter bufferedWriter = null;

		// output route file
		final String outputFilePath = getRouteMapOutputFile();

		try {
			try {
				// create file is not exists
				file = new File(outputFilePath);

				// if output file is already present delete and create new
				if (file.exists()) {
					file.delete();
				}

				// create directory if not exists
				file.getParentFile().mkdir();
				// create new file
				file.createNewFile();

				// file writer instance
				fileWriter = new FileWriter(file);

				// buffered writer
				bufferedWriter = new BufferedWriter(fileWriter);

				// retrieve the line separator
				String lineSeparator = System.getProperty("line.separator");

				for (int rowValue = 0; rowValue < mapDetails.length; rowValue++) {
					for (int columnValue = 0; columnValue < mapDetails[0].length; columnValue++) {
						bufferedWriter.write(mapDetails[rowValue][columnValue]);
						if (columnValue == (mapDetails[0].length - 1)) {
							bufferedWriter.write(lineSeparator);
							bufferedWriter.flush();
						}
					}
				}
			} finally {
				// close the file writer
				if (fileWriter != null) {
					fileWriter.close();
				}
				// close the buffered writer
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
			}
		} catch (IOException ioException) {
			throw ioException;
		}
	}
}
