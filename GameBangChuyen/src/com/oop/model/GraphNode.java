package com.oop.model;

import java.awt.Point;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class GraphNode.
 */
public abstract class GraphNode implements ModelObject {
	private ArrayList<ModelObject> listNeighbor = new ArrayList<ModelObject>();
	
	/** The flag. */
	protected int flag = 0;

	/**
	 * Adds the neighbor.
	 * 
	 * @param modelObj
	 *            the model obj
	 */
	public void addNeighbor(ModelObject modelObj) {
		this.listNeighbor.add(modelObj);
	}

	/**
	 * Gets the flag.
	 * 
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * Gets the list neighbor.
	 * 
	 * @return the list neighbor
	 */
	public ArrayList<ModelObject> getListNeighbor() {
		return this.listNeighbor;
	}

	/**
	 * Gets the neighbor.
	 * 
	 * @param d
	 *            the d
	 * @return the neighbor
	 */
	public ModelObject getNeighbor(final Direction d) {

		Point temp;
		switch (d) {
			case UP :
				for (ModelObject i : this.listNeighbor) {
					temp = i.getPosition();
					if (temp.x < this.getPosition().x)
						return i;
				}
				break;
			case DOWN :
				for (ModelObject i : this.listNeighbor) {
					temp = i.getPosition();
					if (temp.x > this.getPosition().x)
						return i;
				}
				break;
			case LEFT :
				for (ModelObject i : this.listNeighbor) {
					temp = i.getPosition();
					if (temp.y < this.getPosition().y)
						return i;
				}
				break;
			case RIGHT :
				for (ModelObject i : this.listNeighbor) {
					temp = i.getPosition();
					if (temp.y > this.getPosition().y)
						return i;
				}
				break;
		}

		return null;
	}

	/**
	 * Removes the neighbor.
	 * 
	 * @param modelObj
	 *            the model obj
	 * @return true, if successful
	 */
	public boolean removeNeighbor(ModelObject modelObj) {
		return this.listNeighbor.remove(modelObj);
	}

	/**
	 * Sets the flag.
	 * 
	 * @param flag
	 *            the new flag
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
