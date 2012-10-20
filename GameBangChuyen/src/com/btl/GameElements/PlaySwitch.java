package com.btl.GameElements;

import java.awt.Graphics;
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
		if (this.picture == null) {
			initPicture();
		}
		g.drawImage(this.picture, coordinate.x - 13, coordinate.y - 11 - SIZE
				/ 2, null);
		if (!(this.getListDirection().size() == 1 && this.input.size() == 1))
			g.drawImage(
					getArrow(getDirection(),
							this.getListDirection().size() == 1),
					coordinate.x + 1, coordinate.y - SIZE / 2, null);

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

		Point logicCoordinate = ConversionFunction.realToLogic(point);

		logicCoordinate.x = logicCoordinate.x / PlaySwitch.SIZE;
		logicCoordinate.y = logicCoordinate.y / PlaySwitch.SIZE;

		if (logicCoordinate.equals(getPosition()))
			return true;

		return false;
	}
}
