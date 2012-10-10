package com.btl.Model;

import java.awt.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelTerminal.
 */
public class ModelTerminal {

	/** The position. */
	protected Point position;

	/**
	 * Instantiates a new model terminal.
	 * 
	 * @param p
	 *            the p
	 */
	public ModelTerminal(Point p) {
		position = p;
	}

	public ModelTerminal() {

	}

	public ModelTerminal(final ModelTerminal terminal) {
		this.position = terminal.getPosition();
	}

	protected void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * Gets the position.
	 * 
	 * @return the position
	 */
	public Point getPosition() {
		return position;
	}
}
