package com.btl.Model;

import java.awt.Point;
import java.util.ArrayList;

public abstract class ModelSwitch {

	protected ArrayList<Direction> listDirection;
	protected Point position;
	protected int currentDir;

	public ModelSwitch(Point p) {
		this.position = p;

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
