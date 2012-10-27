package com.btl.GameElements;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;
import com.btl.Model.Direction;
import com.btl.Model.ModelSwitch;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaySwitch.
 */
public class PlaySwitch extends ModelSwitch implements Drawable {

	/** The Constant SIZE. */
	public final static int SIZE = 16;
	private final static int WIDTH = 60;
	private final static int HEIGHT = 50;
	private ArrayList<Direction> input = new ArrayList<Direction>();
	public static final int PICCOUNT = PlaySquare.PICCOUNT;
	private int picIndex = 0;

	private BufferedImage buffer = null;

	public void addInput(final Direction d) {
		this.input.add(d);
	}
	public Image picture = null;
	/**
	 * Instantiates a new play switch.
	 * 
	 * @param p
	 *            the p
	 */
	public PlaySwitch(Point p) {
		super(p);
	}
	public PlaySwitch(ModelSwitch mSwitch) {
		super(mSwitch);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		Point coordinate = ConversionFunction.positionToLocation(getPosition(),
				SIZE);

		g.drawImage(this.buffer, coordinate.x - 13, coordinate.y - 11 - SIZE
				/ 2, null);

	}

	public void update() {
		if (this.picture == null) {
			initPicture();
		}

		if (this.buffer == null) {
			buffer = new BufferedImage(this.picture.getWidth(null),
					this.picture.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		}
		Graphics2D g = (Graphics2D) buffer.getGraphics();

		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, this.picture.getWidth(null),
				this.picture.getHeight(null));

		g.setComposite(AlphaComposite.SrcOver);

		g.drawImage(picture, 0, 0, null);

		Point coordinate = new Point(13, 11 + SIZE / 2);

		if (!(this.getListDirection().size() == 1 && this.input.size() == 1))
			g.drawImage(
					getArrow(getDirection(),
							this.getListDirection().size() == 1),
					coordinate.x + 1, coordinate.y - SIZE / 2, null);
		else {
			drawLine(g);
		}
	}

	private void drawLine(Graphics g) {
		Point coordinate = new Point(13, 11 + SIZE / 2);
		Direction in = input.get(0);
		Direction out = this.getDirection();
		g.setColor(new Color(210, 125, 14));

		int x1, x2, y1, y2;
		x1 = x2 = y1 = y2 = 0;
		switch (in) {
			case UP :
				switch (out) {
					case LEFT :

						x1 = coordinate.x + SIZE;
						y1 = coordinate.y + SIZE / 2;

						if (picIndex < PICCOUNT / 2) {
							if (picIndex == 0) {
								g.drawLine(x1, y1, coordinate.x, coordinate.y);
							}

							x2 = coordinate.x + 2 * SIZE - 2 * SIZE * picIndex
									/ PICCOUNT;
							y2 = coordinate.y - SIZE * picIndex / PICCOUNT;
						} else {
							x2 = coordinate.x + SIZE - 2 * SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;
							y2 = coordinate.y - SIZE / 2 + SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;

						}
						break;
					case RIGHT :
						x1 = coordinate.x + 2 * SIZE;
						y1 = coordinate.y;

						if (picIndex < PICCOUNT / 2) {

							if (picIndex == 0) {
								g.drawLine(x1, y1, coordinate.x + SIZE,
										coordinate.y - SIZE / 2);
							}

							x2 = coordinate.x + SIZE - 2 * SIZE * picIndex
									/ PICCOUNT;
							y2 = coordinate.y + SIZE / 2 - SIZE * picIndex
									/ PICCOUNT;
						} else {
							x2 = coordinate.x + 2 * SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;
							y2 = coordinate.y - SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;

						}
						break;
					default :
						break;
				}
				break;
			case DOWN :
				switch (out) {
					case LEFT :
						x1 = coordinate.x;
						y1 = coordinate.y;

						if (picIndex < PICCOUNT / 2) {

							if (picIndex == 0) {
								g.drawLine(x1, y1, coordinate.x + SIZE,
										coordinate.y + SIZE / 2);
							}

							x2 = coordinate.x + SIZE + 2 * SIZE * picIndex
									/ PICCOUNT;
							y2 = coordinate.y - SIZE / 2 + SIZE * picIndex
									/ PICCOUNT;
						} else {
							x2 = coordinate.x + 2 * SIZE - 2 * SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;
							y2 = coordinate.y + SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;

						}
						break;
					case RIGHT :
						x1 = coordinate.x + SIZE;
						y1 = coordinate.y - SIZE / 2;

						if (picIndex < PICCOUNT / 2) {

							if (picIndex == 0) {
								g.drawLine(x1, y1, coordinate.x + 2 * SIZE,
										coordinate.y);
							}

							x2 = coordinate.x + 2 * SIZE * picIndex / PICCOUNT;
							y2 = coordinate.y + SIZE * picIndex / PICCOUNT;
						} else {
							x2 = coordinate.x + SIZE + 2 * SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;
							y2 = coordinate.y + SIZE / 2 - SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;

						}
						break;
					default :
						break;
				}
				break;
			case RIGHT :
				switch (out) {
					case UP :
						x1 = coordinate.x;
						y1 = coordinate.y;

						if (picIndex < PICCOUNT / 2) {

							if (picIndex == 0) {
								g.drawLine(x1, y1, coordinate.x + SIZE,
										coordinate.y - SIZE / 2);
							}

							x2 = coordinate.x + SIZE + SIZE - 2 * SIZE
									* picIndex / PICCOUNT;
							y2 = coordinate.y + SIZE / 2 - SIZE / 2 - SIZE
									* picIndex / PICCOUNT;
						} else {
							x2 = coordinate.x + 2 * SIZE - 2 * SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;
							y2 = coordinate.y - SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;

						}
						break;
					case DOWN :
						x1 = coordinate.x + SIZE;
						y1 = coordinate.y + SIZE / 2;

						if (picIndex < PICCOUNT / 2) {

							if (picIndex == 0) {
								g.drawLine(x1, y1, coordinate.x + 2 * SIZE,
										coordinate.y);
							}

							x2 = coordinate.x + 2 * SIZE * picIndex / PICCOUNT;
							y2 = coordinate.y - SIZE * picIndex / PICCOUNT;
						} else {
							x2 = coordinate.x + SIZE + 2 * SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;
							y2 = coordinate.y + SIZE / 2 - SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;

						}
						break;
					default :
						break;
				}
				break;
			case LEFT :
				switch (out) {
					case UP :
						x1 = coordinate.x + SIZE;
						y1 = coordinate.y - SIZE / 2;

						if (picIndex < PICCOUNT / 2) {

							if (picIndex == 0) {
								g.drawLine(x1, y1, coordinate.x, coordinate.y);
							}

							x2 = coordinate.x + 2 * SIZE - 2 * SIZE * picIndex
									/ PICCOUNT;
							y2 = coordinate.y + SIZE * picIndex / PICCOUNT;
						} else {
							x2 = coordinate.x + SIZE - 2 * SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;
							y2 = coordinate.y + SIZE / 2 - SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;

						}
						break;
					case DOWN :
						x1 = coordinate.x + 2 * SIZE;
						y1 = coordinate.y;

						if (picIndex < PICCOUNT / 2) {

							if (picIndex == 0) {
								g.drawLine(x1, y1, coordinate.x + SIZE,
										coordinate.y + SIZE / 2);
							}

							x2 = coordinate.x + SIZE - 2 * SIZE * picIndex
									/ PICCOUNT;
							y2 = coordinate.y - SIZE / 2 + SIZE * picIndex
									/ PICCOUNT;
						} else {
							x2 = coordinate.x + 2 * SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;
							y2 = coordinate.y + SIZE
									* (picIndex - PICCOUNT / 2) / PICCOUNT;

						}
						break;
					default :
						break;
				}
				break;

		}
		g.drawLine(x1, y1, x2, y2);
		picIndex = (picIndex + 1) % PICCOUNT;

	}
	private Image getArrow(final Direction d, boolean singleDirection) {
		switch (d) {
			case UP :
				if (singleDirection)
					return DirectionImage.AUP1;
				else
					return DirectionImage.AUP;
			case LEFT :
				if (singleDirection)
					return DirectionImage.ALEFT1;
				else
					return DirectionImage.ALEFT;
			case RIGHT :
				if (singleDirection)
					return ConversionFunction
							.flipHorizontally(DirectionImage.AUP1);
				else
					return ConversionFunction
							.flipHorizontally(DirectionImage.AUP);
			case DOWN :
				if (singleDirection)
					return ConversionFunction
							.flipHorizontally(DirectionImage.ALEFT1);
				else
					return ConversionFunction
							.flipHorizontally(DirectionImage.ALEFT);

			default :
				return null;
		}
	}

	private void initPicture() {
		this.picture = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = this.picture.getGraphics();
		if (this.getListDirection().size() == 1 && this.input.size() == 1) { /* Goc */
			Direction out = this.getListDirection().get(0);
			Direction in = this.input.get(0);

			if ((out == Direction.UP) && (in == Direction.LEFT))
				g.drawImage(DirectionImage.UPRIGHT, 5, 4, null);
			else if ((in == Direction.DOWN) && (out == Direction.RIGHT))
				g.drawImage(DirectionImage.UPRIGHT, 5, 4, null);
			else if ((out == Direction.LEFT) || (in == Direction.RIGHT))
				g.drawImage(DirectionImage.LEFT, 5, 4, null);
			else if ((out == Direction.DOWN) || (in == Direction.UP))
				g.drawImage(DirectionImage.DOWN, 5, 4, null);

		} else {
			ArrayList<Direction> output = this.getListDirection();
			boolean left = false;
			boolean down = false;
			if (input.contains(Direction.RIGHT)
					|| output.contains(Direction.LEFT))
				left = true;
			if (input.contains(Direction.UP) || output.contains(Direction.DOWN))
				down = true;

			if (left && down)
				g.drawImage(DirectionImage.SDOWNLEFT, 0, 0, null);
			else if (left)
				g.drawImage(DirectionImage.SLEFT, 0, 0, null);
			else if (down)
				g.drawImage(DirectionImage.SDOWN, 0, 0, null);
		}
		g.dispose();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point point) {

		Point logicCoordinate = ConversionFunction.locationToPosition(point,
				SIZE);

		if (logicCoordinate.equals(getPosition()))
			return true;

		return false;
	}
}
