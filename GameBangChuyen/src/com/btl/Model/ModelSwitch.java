package com.btl.Model;

import java.awt.Point;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelSwitch.
 */
public class ModelSwitch {

	/** The list direction. */
	protected ArrayList<Direction> listDirection;

	/** The position. */
	protected Point position;

	/** The current dir. */
	protected int currentDir = -1; /* Khong co huong nao thi currentDir = -1 */

	public void setCurrentDir(int i) {
		this.currentDir = i;
	}
	/**
	 * Instantiates a new model switch.
	 * 
	 * @param p
	 *            the p
	 */
	public ModelSwitch(Point p) {
		this.position = p;

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
}
