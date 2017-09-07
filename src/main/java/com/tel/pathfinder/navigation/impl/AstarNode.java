package com.tel.pathfinder.navigation.impl;

import com.tel.pathfinder.enums.Movement;
import com.tel.pathfinder.enums.SymbolMapper;
import com.tel.pathfinder.navigation.Node;

/**
 * Class to handle each node of the map
 * 
 * @author styles mangalasseri
 * 
 */
public class AstarNode implements Node, Comparable<Node> {

	/** To trace out the shortest path **/
	private Node parent;

	/** total movement score = distance to destination + cost of the movement **/
	private int totalMovementScore = 0;

	/** maximum row value */
	private static int ROW_MAX;

	/** maximum column value */
	private static int COLUMN_MAX;

	/** Variable to verify whether the node is evaluated or not */
	private boolean isEvaluated;

	/** row value */
	private int row;

	/** column value */
	private int column;

	/** map symbol details */
	private SymbolMapper mapSymbol;

	/**
	 * Constructor
	 * 
	 * @param label
	 *            label from travel map
	 * @param row
	 *            row value
	 * @param column
	 *            column value
	 * @param row_max
	 *            maximum row value
	 * @param column_max
	 *            maximum column value
	 */
	public AstarNode(final char label, final int row, final int column) {
		this.mapSymbol = SymbolMapper.getSymbolMapper(String.valueOf(label));
		this.row = row;
		this.column = column;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowValue() {
		return row;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnValue() {
		return column;
	}

	/**
	 * Method to set maximum row value
	 * 
	 * @param row_max
	 *            maximum row value
	 */
	public static void setMaximumRowValue(final int row_max) {
		ROW_MAX = row_max;
	}

	/**
	 * Method to get Maximum row value
	 * 
	 * @return maximum row value
	 */
	public static int getMaximumRowValue() {
		return ROW_MAX;
	}

	/**
	 * Method to set maximum column value
	 * 
	 * @param column_max
	 *            maximum column value
	 */
	public static void setMaximumColumnValue(final int column_max) {
		COLUMN_MAX = column_max;
	}

	/**
	 * Method to get Maximum column value
	 * 
	 * @return maximum column value
	 */
	public static int getMaximumColumnValue() {
		return COLUMN_MAX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTotalMovementScore() {
		return totalMovementScore;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTotalMovementScore(final int totalMovementScore) {
		this.totalMovementScore = totalMovementScore;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node getParent() {
		return parent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setParent(final Node node) {
		parent = node;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEvaluated() {
		return isEvaluated;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIsEvaluated(final boolean isEvaluated) {
		this.isEvaluated = isEvaluated;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid() {
		return (row >= 0) && (row < ROW_MAX) && (column >= 0) && (column < COLUMN_MAX);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isBlocked() {
		return mapSymbol == SymbolMapper.WATER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDestination() {
		return mapSymbol == SymbolMapper.DESTINATION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int calculateDistanceToDestination(final Node destination) {

		AstarNode currentNode = (AstarNode) destination;
		// calculate the distance from the tile to the goal using Manhattan
		// distance formula |x1 - x2| + |y1 - y2|
		return Math.abs(row - currentNode.row) + Math.abs(column - currentNode.column);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int calculateScoreOfTheMovement(final int movementScore, final Movement movement) {
		return movementScore + movement.getMovementCost() + mapSymbol.getCost();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean compareMapSymbols(final SymbolMapper symbolMapper) {
		return mapSymbol == symbolMapper;
	}

	@Override
	public String toString() {
		return mapSymbol + "(" + row + "," + column + ")";
	}

	@Override
	public boolean equals(final Object obj) {
		// validation status
		boolean status = false;

		if (obj instanceof AstarNode) {
			final AstarNode currentNode = (AstarNode) obj;
			// compare two nodes based the label row and column values
			status = compareMapSymbols(currentNode.mapSymbol) && row == currentNode.row && column == currentNode.column;
		}
		return status;
	}

	@Override
	public int compareTo(final Node node) {
		int value = 0;
		if (this.getTotalMovementScore() == node.getTotalMovementScore()) {
			value = 0;
		} else {
			value = this.getTotalMovementScore() > node.getTotalMovementScore() ? 1 : -1;
		}
		return value;
	}

}
