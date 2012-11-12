package com.oop.model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * The Class ModelSwitch.
 */
public class ModelSwitch extends GraphNode implements ModelObject {

	/** The current dir. */
	private int currentDir = -1; /* Khong co huong nao thi currentDir = -1 */

	/** The list direction. */
	private ArrayList<Direction> listDirection;

	/** The position. */
	protected Point position;

	/**
	 * Instantiates a new model switch.
	 */
	public ModelSwitch() {
		listDirection = new ArrayList<Direction>();
	}

	/**
	 * Instantiates a new model switch.
	 * 
	 * @param mSwitch
	 *            the m switch
	 */
	public ModelSwitch(final ModelSwitch mSwitch) {
		this();
		this.setPosition(mSwitch.getPosition());
		this.setCurrentDir(mSwitch.getCurrentDir());
		this.setListDirection(mSwitch.getListDirection());
	}
	/**
	 * Instantiates a new model switch.
	 * 
	 * @param p
	 *            the p
	 */
	public ModelSwitch(Point p) {
		this();
		this.position = p;

	}
	/**
	 * Adds the direction.
	 * 
	 * @param d
	 *            the d
	 */
	public void addDirection(Direction d) {
		listDirection.add(d);

		if (this.listDirection.size() == 1) {
			this.currentDir = 0;
		}
	}
	/**
	 * Change direction.
	 */
	public void changeDirection() {
		if (this.currentDir != -1) {
			this.currentDir = (this.currentDir + 1) % this.listDirection.size();
		}
	}

	/**
	 * Contains direction.
	 * 
	 * @param d
	 *            the d
	 * @return the boolean
	 */
	public Boolean containsDirection(Direction d) {
		return listDirection.contains(d);
	}

	/**
	 * Gets the direction.
	 * 
	 * @return the direction
	 */
	public Direction getDirection() {
		if (this.listDirection.size() > 0)
			return this.listDirection.get(this.currentDir);
		else
			return null;
	}

	/**
	 * Gets the list direction.
	 * 
	 * @return the list direction
	 */
	public ArrayList<Direction> getListDirection() {
		return listDirection;
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
	 * Removes the direction.
	 * 
	 * @param d
	 *            the d
	 */
	public void removeDirection(Direction d) {
		listDirection.remove(d);

		if (this.listDirection.isEmpty()) {
			this.currentDir = -1;
		}
	}

	/**
	 * Gets the current dir.
	 * 
	 * @return the current dir
	 */
	public int getCurrentDir() {
		return this.currentDir;
	}

	/**
	 * Sets the current dir.
	 * 
	 * @param i
	 *            the new current dir
	 */
	public void setCurrentDir(int i) {
		this.currentDir = i;
	}

	/**
	 * Sets the list direction.
	 * 
	 * @param listDirection
	 *            the new list direction
	 */
	protected void setListDirection(ArrayList<Direction> listDirection) {
		this.listDirection = listDirection;
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
