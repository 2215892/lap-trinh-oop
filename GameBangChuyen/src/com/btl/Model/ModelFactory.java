package com.btl.Model;

import java.awt.Point;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Model objects.
 */
public class ModelFactory extends GraphNode implements ModelObject {
	/** The direction. */
	private Direction direction;

	/** The position. */
	protected Point position;

	/**
	 * Instantiates a new model factory.
	 */
	public ModelFactory() {

	}

	/**
	 * Instantiates a new model factory.
	 * 
	 * @param factory
	 *            the factory
	 */
	public ModelFactory(final ModelFactory factory) {

		this.setPosition(factory.getPosition());
		this.setDirection(factory.getDirection());
	}

	/**
	 * Instantiates a new model factory.
	 * 
	 * @param p
	 *            the p
	 */
	public ModelFactory(final Point p) {
		setPosition(p);
	}

	/**
	 * Instantiates a new model factory.
	 * 
	 * @param p
	 *            the p
	 * @param d
	 *            the d
	 */
	public ModelFactory(final Point p, final Direction d) {
		setPosition(p);
		setDirection(d);
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
	 * Gets the position.
	 * 
	 * @return the position
	 */
	@Override
	public final Point getPosition() {
		return position;
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
	public final void setPosition(final Point position) {
		this.position = position;
	}
}
