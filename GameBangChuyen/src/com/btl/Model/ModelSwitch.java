package com.btl.Model;

import java.util.ArrayList;

public abstract class ModelSwitch {

	protected ArrayList<Direction> listDirection;
	protected int x;
	protected int y;
	protected int currentDir;

	public ModelSwitch(int x, int y) {
		this.x = x;
		this.y = y;

		listDirection = new ArrayList<Direction>();
	}

	public void addDirection(Direction d) {
		listDirection.add(d);
	}

	public void removeDirection(Direction d) {
		listDirection.remove(d);
	}

	public Boolean containsDirection(Direction d) {
		return listDirection.contains(d);
	}

}
