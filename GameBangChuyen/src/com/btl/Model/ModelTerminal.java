package com.btl.Model;

import java.awt.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelTerminal.
 */
public class ModelTerminal extends GraphNode implements ModelObject {

	private int type, boxCount;

	/** The position. */
	protected Point position;

	public ModelTerminal() {

	}

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	protected void setPosition(Point position) {
		this.position = position;
	}

	public int getBoxCount() {
		return boxCount;
	}

	public void setBoxCount(int boxCount) {
		this.boxCount = boxCount;
	}
}
