package com.tel.pathfinder.main;

import com.tel.pathfinder.controller.AstarPathFinderExecutor;

/**
 * Starter class
 * 
 * @author styles mangalasseri
 * 
 */
public class Starter {

	public static void main(String[] args) {

		// instance of controller
		AstarPathFinderExecutor controller = new AstarPathFinderExecutor();
		// find shortest path
		controller.findShortedPath();

	}
}
