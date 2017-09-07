package com.tel.pathfinder.enums;

/**
 * Enum value to hold the movement type
 * 
 * @author styles mangalasseri
 * 
 */
public enum Movement {

	/** movement to sideways from one square to another. */
	BASIC_MOVEMENT(10),
	/** movement to diagonally from one square to another. */
	DIAGONAL_MOVEMENT(14);

	/** Movement cost */
	private int movementCost;

	private Movement(int movementCost) {
		this.movementCost = movementCost;
	}

	/**
	 * Method to get movement cost
	 * 
	 * @return movement cost
	 */
	public int getMovementCost() {
		return movementCost;
	}

}
