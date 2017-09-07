package com.tel.pathfinder.enums;

/**
 * Enum to indicate different map symbols which is available in the map
 * 
 * @author styles mangalasseri
 * 
 */
public enum SymbolMapper {

	/** flat land terrain */
	FLATLANDS(".", 1),

	/** forest */
	FOREST("*", 2),

	/** mountain */
	MOUNTAIN("^", 3),

	/** water */
	WATER("~", 4),

	/** start position */
	START("@", 1),

	/** destination position */
	DESTINATION("X", 1);

	/**
	 * label marked in map
	 */
	private String label;
	/**
	 * Movement cost
	 */
	private int cost;

	private SymbolMapper(String label, int cost) {
		this.label = label;
		this.cost = cost;
	}

	/**
	 * Method to retrieve map symbol
	 * 
	 * @param label
	 *            label which is available in map
	 * @return instance of {@link SymbolMapper}
	 */
	public static SymbolMapper getSymbolMapper(String label) {
		// terrain
		SymbolMapper symbolMapper = null;
		// iterating through the Map symbols to find the required symbols enum
		// constant
		for (SymbolMapper symbol : SymbolMapper.values()) {
			if (symbol.getLabel().equals(label)) {
				symbolMapper = symbol;
				break;
			}
		}
		return symbolMapper;
	}

	/**
	 * Method to return label of the map symbol
	 * 
	 * @return label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Method to return cost of the movement through the terrain
	 * 
	 * @return cost
	 */
	public int getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return label;
	}
}
