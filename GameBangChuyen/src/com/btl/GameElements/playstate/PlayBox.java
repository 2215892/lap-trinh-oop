package com.btl.GameElements.playstate;

import java.awt.Graphics;
import java.awt.Point;

import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;
import com.btl.Model.Direction;
import com.btl.Model.ModelObject;
import com.btl.data.BoxImage;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayBox.
 */
public class PlayBox implements Drawable, ModelObject {

	/** The Constant SIZE. */
	public static final int SIZE = PlaySquare.SIZE;
	private TerminalColor color = TerminalColor.DEFAULT;

	private Point destination;

	private Direction direction;

	private boolean isMoving = false;
	/* Toa do logic */
	private Point location;
	private int step = 1;
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
	public PlayBox(Point location, TerminalColor color) {
		this.setLocation(location);
		this.setColor(color);
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

	public TerminalColor getColor() {
		return color;
	}

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	@Override
	public Point getPosition() {
		return new Point(ConversionFunction.locationToPosition(this.location,
				SIZE));
	}

	public int getStep() {
		return step;
	}

	/**
	 * Checks if is moving.
	 * 
	 * @return true, if is moving
	 */
	public boolean isMoving() {
		return isMoving;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(BoxImage.getBoxImage(color), location.x + 2,
				location.y - 21, null);

	}

	public void setColor(TerminalColor color) {
		this.color = color;
	}

	public boolean setDestination(final Point position) {
		if (this.isMoving())
			return false;
		else {
			this.destination = position;
			this.direction = caculateDirection(
					ConversionFunction.locationToPosition(this.location, SIZE),
					this.destination);
			this.isMoving = true;
			return true;
		}

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

	public void setStep(int step) {
		this.step = step;
	}

	public void update() {
		if (this.direction != null) {
			switch (this.direction) {
				case UP :
					this.location.x -= 2 * step;
					this.location.y -= step;
					break;
				case DOWN :
					this.location.x += 2 * step;
					this.location.y += step;
					break;
				case LEFT :
					this.location.x -= 2 * step;
					this.location.y += step;
					break;
				case RIGHT :
					this.location.x += 2 * step;
					this.location.y -= step;
					break;
				default :
			}
			if (this.getLocation().equals(
					ConversionFunction.positionToLocation(this.destination,
							SIZE))) {
				this.direction = null;
				this.isMoving = false;
			}
		}
	}

	private Direction caculateDirection(Point from, Point to) {
		if (from.x < to.x) {
			return Direction.DOWN;
		} else if (from.x > to.x) {
			return Direction.UP;
		} else if (from.y < to.y) {
			return Direction.RIGHT;
		} else
			return Direction.LEFT;
	}

}
