package com.btl.Model;

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

}
