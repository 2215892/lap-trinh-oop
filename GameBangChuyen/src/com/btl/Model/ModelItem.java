package com.btl.Model;

import java.awt.Point;

public class ModelItem implements ModelObject {
	/** The position. */
	private Point position;

	private int type, id;

	public ModelItem() {

	}

	public ModelItem(ModelItem item) {
		this.setId(item.getId());
		this.setPosition(item.getPosition());
		this.setType(item.getType());
	}

	public int getId() {
		return id;
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	public int getType() {
		return type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public final void setPosition(Point position) {
		this.position = position;
	}

	public void setType(int type) {
		this.type = type;
	}
}
