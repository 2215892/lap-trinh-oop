package com.btl.GameElements.playstate;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

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
	private BufferedImage picture = null;
	private int picIndex = 0;
	public static final int PICCOUNT = 8;

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

		g.drawImage(this.buffer, coordinate.x - 8, coordinate.y - 7 - SIZE / 2,
				null);

	}

	private BufferedImage buffer = null;

	public void update() {
		if (this.picture == null)
			this.picture = DirectionImage.SQUARE;
		if (this.buffer == null) {
			buffer = new BufferedImage(this.picture.getWidth(null),
					this.picture.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		}

		Point coordinate = new Point(8, 7 + SIZE / 2);
		Graphics2D g = (Graphics2D) buffer.getGraphics();

		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, this.picture.getWidth(null),
				this.picture.getHeight(null));
		g.setComposite(AlphaComposite.SrcOver);

		g.drawImage(picture, 0, 0, null);

		g.setColor(new Color(210, 125, 14));
		int x1, y1;
		x1 = y1 = 0;
		switch (direction) {
			case UP :
				x1 = coordinate.x + SIZE - SIZE * picIndex / PICCOUNT;
				y1 = coordinate.y + SIZE / 2 - SIZE / 2 * picIndex / PICCOUNT;
				g.drawImage(ConversionFunction
						.flipHorizontally(DirectionImage.LINE), x1 - 2, y1 - 9,
						null);

				break;
			case DOWN :
				x1 = coordinate.x + SIZE * picIndex / PICCOUNT;
				y1 = coordinate.y + SIZE * picIndex / PICCOUNT / 2;
				g.drawImage(ConversionFunction
						.flipHorizontally(DirectionImage.LINE), x1 - 2, y1 - 9,
						null);
				break;
			case RIGHT :
				x1 = coordinate.x + SIZE * picIndex / PICCOUNT;
				y1 = coordinate.y - SIZE / 2 * picIndex / PICCOUNT;
				if (x1 == coordinate.x && y1 == coordinate.y) {
					x1 = coordinate.x + SIZE;
					y1 = coordinate.y - SIZE / 2;
				}
				g.drawImage(DirectionImage.LINE, x1 - 2, y1 - 1, null);
				break;
			case LEFT :
				x1 = coordinate.x + SIZE - SIZE * picIndex / PICCOUNT;
				y1 = coordinate.y - SIZE / 2 + SIZE / 2 * picIndex / PICCOUNT;
				g.drawImage(DirectionImage.LINE, x1 - 2, y1 - 1, null);
				break;
		}

		g.dispose();

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
