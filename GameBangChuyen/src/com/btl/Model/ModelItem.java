package com.btl.Model;

import java.awt.Point;

public class ModelItem implements ModelObject {
	/** The position. */
	private Point position;

	private int type, id;

	@Override
	public Point getPosition() {
		return this.position;
	}

	public final void setPosition(Point position) {
		this.position = position;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
