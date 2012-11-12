package com.oop.model;

import java.awt.Point;

/**
 * The Class ModelItem.
 */
public class ModelItem implements ModelObject {
	/** The position. */
	private Point position;

	private int type, id;

	/**
	 * Instantiates a new model item.
	 */
	public ModelItem() {

	}

	/**
	 * Instantiates a new model item.
	 * 
	 * @param item
	 *            the item
	 */
	public ModelItem(ModelItem item) {
		this.setId(item.getId());
		this.setPosition(item.getPosition());
		this.setType(item.getType());
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.model.ModelObject#getPosition()
	 */
	@Override
	public Point getPosition() {
		return this.position;
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
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the position.
	 * 
	 * @param position
	 *            the new position
	 */
	public final void setPosition(Point position) {
		this.position = position;
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
}
