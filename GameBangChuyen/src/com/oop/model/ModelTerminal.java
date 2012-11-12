package com.oop.model;

import java.awt.Point;

/**
 * The Class ModelTerminal.
 */
public class ModelTerminal extends GraphNode implements ModelObject {

	private int type, boxCount;

	/** The position. */
	protected Point position;

	/**
	 * Instantiates a new model terminal.
	 */
	public ModelTerminal() {
		type = 1;
	}

	/**
	 * Instantiates a new model terminal.
	 * 
	 * @param terminal
	 *            the terminal
	 */
	public ModelTerminal(final ModelTerminal terminal) {
		this.position = terminal.getPosition();
		this.setType(terminal.getType());
		this.setBoxCount(terminal.getBoxCount());
	}

	/**
	 * Instantiates a new model terminal.
	 * 
	 * @param p
	 *            the p
	 */
	public ModelTerminal(Point p) {
		position = p;
		type = 1;
	}

	/**
	 * Gets the box count.
	 * 
	 * @return the box count
	 */
	public int getBoxCount() {
		return boxCount;
	}

	/**
	 * Gets the position.
	 * 
	 * @return the position
	 */
	@Override
	public Point getPosition() {
		return position;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets the box count.
	 * 
	 * @param boxCount
	 *            the new box count
	 */
	public void setBoxCount(int boxCount) {
		this.boxCount = boxCount;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Sets the position.
	 * 
	 * @param position
	 *            the new position
	 */
	protected void setPosition(Point position) {
		this.position = position;
	}
}
