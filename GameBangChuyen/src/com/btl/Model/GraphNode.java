package com.btl.Model;

import java.awt.Point;
import java.util.ArrayList;

public abstract class GraphNode implements ModelObject {
	private ArrayList<ModelObject> listNeighbor = new ArrayList<ModelObject>();
	protected int flag = 0;

	public void addNeighbor(ModelObject modelObj) {
		this.listNeighbor.add(modelObj);
	}

	public int getFlag() {
		return flag;
	}

	public ArrayList<ModelObject> getListNeighbor() {
		return this.listNeighbor;
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

	public boolean removeNeighbor(ModelObject modelObj) {
		return this.listNeighbor.remove(modelObj);
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
