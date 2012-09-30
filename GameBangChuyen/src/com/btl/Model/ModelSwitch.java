package com.btl.Model;

import java.awt.Point;
import java.util.ArrayList;

public abstract class ModelSwitch {

	protected ArrayList<Direction> listDirection;
	protected Point position;
	protected int currentDir = -1; /* Khong co huong nao thi currentDir = -1 */

	public ModelSwitch(Point p) {
		this.position = p;

		listDirection = new ArrayList<Direction>();
	}

	public void addDirection(Direction d) {
		listDirection.add(d);

		if (this.listDirection.size() == 1) {
			this.currentDir = 0;
		}
	}

	public void removeDirection(Direction d) {
		listDirection.remove(d);

		if (this.listDirection.isEmpty()) {
			this.currentDir = -1;
		}
	}

	public Boolean containsDirection(Direction d) {
		return listDirection.contains(d);
	}

	public void changeDirection() {
		if (this.currentDir != -1) {
			this.currentDir = (this.currentDir + 1) / this.listDirection.size();
		}
	}
}
