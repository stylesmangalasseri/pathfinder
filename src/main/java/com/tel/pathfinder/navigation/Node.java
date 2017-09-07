package com.tel.pathfinder.navigation;

import com.tel.pathfinder.enums.Movement;
import com.tel.pathfinder.enums.SymbolMapper;

/**
 * Node to represent map entries
 * 
 * @author styles mangalasseri
 * 
 */
public interface Node {

	/**
	 * Method to return row value
	 * 
	 * @return row value
	 */
	int getRowValue();

	/**
	 * Method to return column value
	 * 
	 * @return column value
	 */
	int getColumnValue();

	/**
	 * Retrieve total movement score
	 * 
	 * @return total movement score
	 */
	public int getTotalMovementScore();

	/**
	 * Set total movement score
	 * 
	 * @param totalMovementScore
	 *            movement score to set
	 */
	public void setTotalMovementScore(final int totalMovementScore);

	/**
	 * Method to get parent node from current node
	 * 
	 * @return parent node details
	 */
	public Node getParent();

	/**
	 * Method to set parent node details
	 * 
	 * @param node
	 *            details to set as parent for the current node
	 */
	public void setParent(final Node node);

	/**
	 * Method to get whether the node is evaluated or not
	 * 
	 * @return true if evaluated else false
	 */
	boolean isEvaluated();

	/**
	 * Method to set isEvaluated
	 * 
	 * @param isEvaluated
	 */
	void setIsEvaluated(final boolean isEvaluated);

	/**
	 * Method to check whether given node (row, col) is a valid or not.
	 * 
	 * @return true if row number and column number is in range
	 */
	boolean isValid();

	/**
	 * Method to check whether the given cell is blocked or not
	 * 
	 * @return true if the node is a blocker
	 */
	boolean isBlocked();

	/**
	 * Method to check whether destination cell has been reached or not
	 * 
	 * @return true if destination is reached
	 */
	boolean isDestination();

	/**
	 * Method to calculate the distance to the destination from the current node
	 * 
	 * @param destination
	 *            destination node
	 * @return the destination from the current node to the destination node
	 */
	int calculateDistanceToDestination(final Node destination);

	/**
	 * Method to calculate the total cost of the movement to the destination.
	 * 
	 * @param movement
	 *            instance of {@link Movement}
	 * @return total cost of the movement
	 */
	int calculateScoreOfTheMovement(final int movementScore, final Movement movement);

	/**
	 * Method to compare node based on the symbol available in the map
	 * 
	 * @param symbol
	 *            symbol
	 * @return true if the two symbols are equal else false
	 */
	boolean compareMapSymbols(final SymbolMapper symbolMapper);
}
