package com.btl.GameEngine;

import java.awt.Graphics;
import java.awt.Point;

// TODO: Auto-generated Javadoc
/**
 * The Interface Drawable.
 */
public interface Drawable {

    /**
     * Paint.
     * 
     * @param g
     *            the g
     */
    public void paint(Graphics g);

    /**
     * Contains.
     * 
     * @param point
     *            the point
     * @return true, if successful
     */
    public boolean contains(Point point);

}
