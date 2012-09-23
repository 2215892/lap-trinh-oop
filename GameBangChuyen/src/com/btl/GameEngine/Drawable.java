package com.btl.GameEngine;

import java.awt.Graphics;
import java.awt.Point;

public interface Drawable {

	public void paint(Graphics g);

	public boolean contains(Point p);

}
