package com.btl.Model;

import java.awt.Point;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Model objects.
 */
public class ModelFactory {
	/** The position. */
	private Point position;

	/** The direction. */
	private Direction direction;

	/**
	 * Instantiates a new model factory.
	 * 
	 * @param p
	 *            the p
	 */
	public ModelFactory(final Point p) {
		setPosition(p);
	}

	public ModelFactory(final Point p, Direction d) {
		setPosition(p);
		setDirection(d);
	}

	/**
	 * Gets the position.
	 * 
	 * @return the position
	 */
	public final Point getPosition() {
		return position;
	}

	/**
	 * Gets the direction.
	 * 
	 * @return the direction
	 */
	public final Direction getDirection() {
		return direction;
	}

	/**
	 * Sets the direction.
	 * 
	 * @param d
	 *            the new direction
	 */
	public final void setDirection(final Direction d) {
		direction = d;
	}

	/**
	 * Sets the position.
	 * 
	 * @param position
	 *            the new position
	 */
	protected final void setPosition(final Point position) {
		this.position = position;
	}
}
