package com.btl.GameElements;

import java.awt.Color;
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
	private static final int PICCOUNT = 8;

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

		g.drawImage(this.picture, coordinate.x - 8,
				coordinate.y - 7 - SIZE / 2, null);
		drawLine(g, coordinate);

	}

	private void drawLine(Graphics g, Point coordinate) {
		// TODO
		g.setColor(new Color(210, 125, 14));
		int x1, y1, x2, y2;
		x1 = x2 = y1 = y2 = 0;
		switch (direction) {
			case UP :
				x1 = coordinate.x + SIZE - SIZE * picIndex / PICCOUNT;
				x2 = coordinate.x + 2 * SIZE - SIZE * picIndex / PICCOUNT;
				y1 = coordinate.y + SIZE / 2 - SIZE / 2 * picIndex / PICCOUNT;
				y2 = coordinate.y - SIZE / 2 * picIndex / PICCOUNT;
				break;
			case DOWN :
				x1 = coordinate.x + SIZE * picIndex / PICCOUNT;
				x2 = coordinate.x + SIZE + SIZE * picIndex / PICCOUNT;
				y1 = coordinate.y + SIZE * picIndex / PICCOUNT / 2;
				y2 = coordinate.y - SIZE / 2 + SIZE * picIndex / PICCOUNT / 2;
				break;
			case RIGHT :
				x1 = coordinate.x + SIZE * picIndex / PICCOUNT;
				x2 = coordinate.x + SIZE + SIZE * picIndex / PICCOUNT;
				y1 = coordinate.y - SIZE / 2 * picIndex / PICCOUNT;
				y2 = coordinate.y + SIZE / 2 - SIZE / 2 * picIndex / PICCOUNT;
				break;
			case LEFT :
				x1 = coordinate.x + SIZE - SIZE * picIndex / PICCOUNT;
				x2 = coordinate.x + 2 * SIZE - SIZE * picIndex / PICCOUNT;
				y1 = coordinate.y - SIZE / 2 + SIZE / 2 * picIndex / PICCOUNT;
				y2 = coordinate.y + SIZE / 2 * picIndex / PICCOUNT;
				break;
		}
		g.drawLine(x1, y1, x2, y2);

		picIndex = (picIndex + 1) % PICCOUNT;
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
