package com.btl.GameElements;

import java.awt.Graphics;
import java.awt.Point;

import com.btl.GameElements.gameplay.BoxImage;
import com.btl.GameElements.gameplay.PlayFlag;
import com.btl.GameElements.gameplay.PlaySquare;
import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayBox.
 */
public class PlayBox implements Drawable {

    /** The Constant SIZE. */
    public static final int SIZE = 2 * PlaySquare.SIZE;

    /* Toa do logic */
    private Point location;
    private PlayFlag flag;
    private boolean isMoving = false;

    /**
     * Instantiates a new play box.
     */
    public PlayBox() {

    }

    /**
     * Instantiates a new play box.
     * 
     * @param location
     *            the location
     * @param flag
     *            the flag
     */
    public PlayBox(Point location, PlayFlag flag) {
	this.setLocation(location);
	this.setFlag(flag);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
	Point realLocation = ConversionFunction.logicToReal(location);
	g.drawImage(BoxImage.getBoxImage(flag), realLocation.x, realLocation.y,
		SIZE, SIZE, null);

    }

    /**
     * Move up.
     * 
     * @return true, if successful
     */
    public boolean moveUp() {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
     */
    @Override
    public boolean contains(Point point) {
	/* TODO tam khong cho click */
	return false;
    }

    /**
     * Gets the location.
     * 
     * @return the location
     */
    public Point getLocation() {
	return location;
    }

    /**
     * Sets the location.
     * 
     * @param location
     *            the new location
     */
    public void setLocation(Point location) {
	this.location = location;
    }

    /**
     * Gets the flag.
     * 
     * @return the flag
     */
    public PlayFlag getFlag() {
	return flag;
    }

    /**
     * Sets the flag.
     * 
     * @param flag
     *            the new flag
     */
    public void setFlag(PlayFlag flag) {
	this.flag = flag;
    }

    /**
     * Checks if is moving.
     * 
     * @return true, if is moving
     */
    public boolean isMoving() {
	return isMoving;
    }

}
