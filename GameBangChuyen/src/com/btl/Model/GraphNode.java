package com.btl.Model;

import java.awt.Point;
import java.util.ArrayList;

public abstract class GraphNode implements ModelObject {
	protected int flag = 0;
	private ArrayList<ModelObject> listNeighbor = new ArrayList<ModelObject>();

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public ArrayList<ModelObject> getListNeighbor() {
		return this.listNeighbor;
	}

	public void addNeighbor(ModelObject modelObj) {
		this.listNeighbor.add(modelObj);
	}

	public boolean removeNeighbor(ModelObject modelObj) {
		return this.listNeighbor.remove(modelObj);
	}

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
}
