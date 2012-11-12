package com.oop.gamepanel;

import java.awt.Graphics;
import java.awt.Point;

/**
 * The Interface Drawable.
 */
public interface Drawable {

	/**
	 * Contains.
	 * 
	 * @param point
	 *            the point
	 * @return true, if successful
	 */
	public boolean contains(Point point);

	/**
	 * Paint.
	 * 
	 * @param g
	 *            the g
	 */
	public void paint(Graphics g);

}
