package com.btl.Model;

import java.awt.Point;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelSwitch.
 */
public class ModelSwitch {

	/** The list direction. */
	private ArrayList<Direction> listDirection;

	/** The position. */
	private Point position;

	/** The current dir. */
	private int currentDir = -1; /* Khong co huong nao thi currentDir = -1 */

	public void setCurrentDir(int i) {
		this.currentDir = i;
	}
	public ArrayList<Direction> getListDirection() {
		return listDirection;
	}
	protected void setListDirection(ArrayList<Direction> listDirection) {
		this.listDirection = listDirection;
	}
	protected void setPosition(Point position) {
		this.position = position;
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

	public ModelSwitch() {
		listDirection = new ArrayList<Direction>();
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
	 * Change direction.
	 */
	public void changeDirection() {
		if (this.currentDir != -1) {
			this.currentDir = (this.currentDir + 1) / this.listDirection.size();
		}
	}

	public int getCurrentDir() {
		return this.currentDir;
	}

	public Direction getDirection() {
		if (this.listDirection.size() > 0)
			return this.listDirection.get(this.currentDir);
		else
			return null;
	}

	public Point getPosition() {
		return this.position;
	}
}
