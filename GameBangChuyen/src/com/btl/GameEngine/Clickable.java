package com.btl.GameEngine;

import java.awt.Point;

public interface Clickable {

	public Boolean contains(Point p);

	public void onClick();

}
