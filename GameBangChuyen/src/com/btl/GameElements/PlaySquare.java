package com.btl.GameElements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;
import com.btl.Model.Direction;
import com.btl.Model.ModelObject;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaySquare.
 */
public class PlaySquare implements Drawable, ModelObject {
	private Direction direction;
	private Point position;
	private Image picture = null;
	private int picIndex = 0;
	private static final int PICCOUNT = 3;

	/** The Constant SIZE. */
	public final static int SIZE = PlaySwitch.SIZE;

	/**
	 * Instantiates a new play square.
	 * 
	 * @param direction
	 *            the direction
	 * @param position
	 *            the position
	 */
	public PlaySquare(Direction direction, Point position) {
		this.direction = direction;
		this.position = position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		Point coordinate = ConversionFunction
				.positionToLocation(position, SIZE);
		if (this.picture == null)
			this.picture = DirectionImage.SQUARE;

		g.drawImage(this.picture, coordinate.x - 8, coordinate.y - 7, null);
		drawLine(g, coordinate);

	}

	private void drawLine(Graphics g, Point coordinate) {
		// TODO
		switch (direction) {
			case UP :
				break;
			case DOWN :
				break;
			case RIGHT :
				break;
			case LEFT :
				break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point point) {
		return false;
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

}
