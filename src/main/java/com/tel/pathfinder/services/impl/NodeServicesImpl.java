package com.tel.pathfinder.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.tel.pathfinder.constants.CommonConstants;
import com.tel.pathfinder.enums.Movement;
import com.tel.pathfinder.enums.SymbolMapper;
import com.tel.pathfinder.exception.UnableToMoveException;
import com.tel.pathfinder.navigation.Node;
import com.tel.pathfinder.navigation.impl.AstarNode;
import com.tel.pathfinder.services.NodeServices;

/**
 * Class to handle all the operations with node details
 * 
 * @author styles mangalasseri
 * 
 */
public class NodeServicesImpl implements NodeServices {

	/** total nodes retrieved from the route map */
	private Node[][] nodeDetails = null;

	/** variable to store evaluating cells **/
	private PriorityQueue<Node> openNodeDetails = new PriorityQueue<Node>();

	/**
	 * Method to calculate total movement cost for the adjacent nodes
	 * 
	 * @param currentNode
	 *            current node
	 * @param rowValue
	 *            row value to retrieve node from array
	 * @param columnValue
	 *            column value to retrieve node from array
	 * @param destinationNode
	 *            destination node
	 * @param movement
	 *            instance of {@link Movement}
	 */
	private void calculateTotalMovementCostForAdjacentNode(final Node currentNode, final int rowValue,
			final int columnValue, final Node destinationNode, final Movement movement) {

		// node validation status
		boolean isInValid = false;
		// true if the node is already available in open list
		boolean isInOpenList = false;
		// current cost
		int currentCost = 0;
		// total cost
		int totalMovementCost = 0;
		// variable to hold the adjacent node
		Node adjacentNode = null;

		if (rowValue >= 0 && rowValue < AstarNode.getMaximumRowValue() && columnValue >= 0
				&& columnValue < AstarNode.getMaximumColumnValue()) {

			// retrieve adjacent node
			adjacentNode = nodeDetails[rowValue][columnValue];

			// validate whether the adjacent cells are valid or not
			isInValid = adjacentNode == null || (!adjacentNode.isValid()) || adjacentNode.isBlocked()
					|| adjacentNode.isEvaluated();

			if (!isInValid) {
				// calculate current movement cost
				currentCost = adjacentNode.calculateScoreOfTheMovement(currentNode.getTotalMovementScore(), movement);
				// calculate total movement cost upto adjacent node
				totalMovementCost = adjacentNode.calculateDistanceToDestination(destinationNode) + currentCost;
				// verify whether the node is in open list or not
				isInOpenList = openNodeDetails.contains(adjacentNode);
				// validate total cost of the movement
				if (!isInOpenList || totalMovementCost < adjacentNode.getTotalMovementScore()) {
					adjacentNode.setTotalMovementScore(totalMovementCost);
					adjacentNode.setParent(currentNode);
					if (!isInOpenList) {
						openNodeDetails.add(adjacentNode);
					}
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node[][] retrieveMapDetails(final char[][] mapDetails) {
		// setting maximum row value
		AstarNode.setMaximumRowValue(mapDetails.length);
		// setting maximum column value
		AstarNode.setMaximumColumnValue(mapDetails[0].length);
		// initialize array
		final AstarNode[][] nodeDetails = new AstarNode[mapDetails.length][mapDetails[0].length];

		// retrieve map details in the form of node
		for (int rowValue = 0; rowValue < nodeDetails.length; rowValue++) {
			for (int columnValue = 0; columnValue < mapDetails[0].length; columnValue++) {
				nodeDetails[rowValue][columnValue] = new AstarNode(mapDetails[rowValue][columnValue], rowValue,
						columnValue);
			}
		}

		return nodeDetails;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node getStartNode() {
		// start node
		Node startNode = null;
		// Temporary node
		Node currentNode = null;
		// iterate through the available nodes
		for (int rowValue = 0; rowValue < nodeDetails.length; rowValue++) {
			for (int columnValue = 0; columnValue < nodeDetails.length; columnValue++) {
				// retrieve current node
				currentNode = nodeDetails[rowValue][columnValue];
				// validating whether the given node is start node or not
				if (currentNode.compareMapSymbols(SymbolMapper.START)) {
					startNode = currentNode;
					break;
				}
			}
		}
		return startNode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node getDestinationNode() {
		// destination node
		Node destinationNode = null;
		// Temporary node
		Node currentNode = null;
		// iterate through the available nodes
		for (int rowValue = 0; rowValue < nodeDetails.length; rowValue++) {
			for (int columnValue = 0; columnValue < nodeDetails.length; columnValue++) {
				// retrieve current node
				currentNode = nodeDetails[rowValue][columnValue];
				// validating whether the given node is start node or not
				if (currentNode.compareMapSymbols(SymbolMapper.DESTINATION)) {
					destinationNode = currentNode;
					break;
				}
			}
		}
		return destinationNode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public char[][] updateNodeDetailsWithNavigationRoute(final char[][] mapDetails, final List<Node> navigationRoute) {
		for (Node node : navigationRoute) {
			mapDetails[node.getRowValue()][node.getColumnValue()] = CommonConstants.NAVIGATION_ROUTE;
		}
		return mapDetails;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public char[][] getNavigationRoute(final char[][] mapDetails) throws UnableToMoveException {

		// current node
		Node current = null;
		// navigation route
		final List<Node> navigationRoute = new ArrayList<Node>();
		// retrieve node details which is available in the map
		nodeDetails = retrieveMapDetails(mapDetails);
		// retrieve start node
		Node startNode = getStartNode();
		// retrieve destination node
		Node destinationNode = getDestinationNode();

		// closed = new boolean[AstarNode.getMaximumRowValue()][AstarNode.getMaximumColumnValue()];
		// add the start location to open list.
		openNodeDetails.add(nodeDetails[startNode.getRowValue()][startNode.getColumnValue()]);

		while (true) {

			// retrieve the cell to process
			current = openNodeDetails.poll();

			if (current == null) {
				// Exiting from loop if cell is non-walkable
				throw new UnableToMoveException("UNABLE TO WALK TO DESTINATION");
			}

			// marking cell as evaluated
			current.setIsEvaluated(true);

			// Destination reached
			if (current.isDestination()) {
				logger.info("REACHED DESTINATION");
				break;
			}

			// adjacent node details of [X,Y]

			// [ [(X-1),(Y-1)] , [(X-1), (Y)], [(X-1), (Y+1)] ]
			// [ [(X),(Y-1) ] , [ X , Y ] , (X), (Y+1)] ]
			// [ [(X+1),(Y-1)], [(X+1), (Y)], [(X+1), (Y+1)] ]

			// retrieve adjacent nodes and verify whether it is valid or not
			// get node element [(X-1), (Y-1)]
			calculateTotalMovementCostForAdjacentNode(current, current.getRowValue() - 1, current.getColumnValue() - 1,
					destinationNode, Movement.DIAGONAL_MOVEMENT);
			// get node element [(X-1), (Y)]
			calculateTotalMovementCostForAdjacentNode(current, current.getRowValue() - 1, current.getColumnValue(),
					destinationNode, Movement.BASIC_MOVEMENT);
			// get node element [(X-1), (Y+1)]
			calculateTotalMovementCostForAdjacentNode(current, current.getRowValue() - 1, current.getColumnValue() + 1,
					destinationNode, Movement.DIAGONAL_MOVEMENT);
			// get node element [(X), (Y-1)]
			calculateTotalMovementCostForAdjacentNode(current, current.getRowValue(), current.getColumnValue() - 1,
					destinationNode, Movement.BASIC_MOVEMENT);
			// get node element [(X), (Y+1)]
			calculateTotalMovementCostForAdjacentNode(current, current.getRowValue(), current.getColumnValue() + 1,
					destinationNode, Movement.BASIC_MOVEMENT);
			// get node element [(X+1), (Y-1)]
			calculateTotalMovementCostForAdjacentNode(current, current.getRowValue() + 1, current.getColumnValue() - 1,
					destinationNode, Movement.DIAGONAL_MOVEMENT);
			// get node element [(X+1), (Y)]
			calculateTotalMovementCostForAdjacentNode(current, current.getRowValue() + 1, current.getColumnValue(),
					destinationNode, Movement.BASIC_MOVEMENT);
			// get node element [(X+1), (Y+1)]
			calculateTotalMovementCostForAdjacentNode(current, current.getRowValue() + 1, current.getColumnValue() + 1,
					destinationNode, Movement.DIAGONAL_MOVEMENT);

		}

		// retrieve navigation route
		current = nodeDetails[destinationNode.getRowValue()][destinationNode.getColumnValue()];

		while (current.getParent() != null) {
			navigationRoute.add(current);
			current = current.getParent();
		}
		navigationRoute.add(current);

		return updateNodeDetailsWithNavigationRoute(mapDetails, navigationRoute);

	}
}
