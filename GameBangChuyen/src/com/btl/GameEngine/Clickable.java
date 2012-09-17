package com.btl.GameEngine;

import java.awt.Point;

public interface Clickable {

	public boolean contains(Point p);

	public void onClick();

}
