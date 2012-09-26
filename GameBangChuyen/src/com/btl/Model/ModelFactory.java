package com.btl.Model;

import java.awt.Point;

public class ModelFactory {
	protected Point position;
	protected Direction direction;

	public ModelFactory(Point p) {
		position = p;
	}

	public Point getPosition() {
		return position;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction d) {
		direction = d;
	}
}
