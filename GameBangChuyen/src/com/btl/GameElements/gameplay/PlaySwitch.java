package com.btl.GameElements.gameplay;

import java.awt.Graphics;
import java.awt.Point;

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
	public final static int SIZE = 30;

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
		this.setPosition(mSwitch.getPosition());
		this.setCurrentDir(mSwitch.getCurrentDir());
		this.setListDirection(mSwitch.getListDirection());
	}
	/**
	 * Lay toa do de ve hinh
	 * 
	 * @return toa do goc tren ben trai de ve hinh
	 */
	private Point getCoordinate() {
		Point coordinateLogic = new Point(getPosition().x * SIZE,
				getPosition().y * SIZE);
		/* Goc tren ben trai */
		Point vertexA = ConversionFunction.logicToReal(coordinateLogic);
		/* Goc tren ben phai */
		Point vertexB = ConversionFunction.logicToReal(new Point(
				coordinateLogic.x + SIZE, coordinateLogic.y));

		return new Point(vertexA.x, vertexB.y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		Point coordinate = this.getCoordinate();
		int imageSize = 2 * SIZE;
		if (this.getListDirection().size() == 1) { /* Co 1 huong */

			Direction direction = this.getListDirection().get(
					this.getCurrentDir());

			switch (direction) { /* Xet huong de chon hinh phu hop */
				case UP : {
					g.drawImage(DirectionImage.UP, coordinate.x, coordinate.y,
							imageSize, imageSize, null);
					break;
				}
				case DOWN : {
					g.drawImage(DirectionImage.DOWN, coordinate.x,
							coordinate.y, imageSize, imageSize, null);
					break;
				}
				case RIGHT : {
					g.drawImage(DirectionImage.RIGHT, coordinate.x,
							coordinate.y, imageSize, imageSize, null);
					break;
				}
				case LEFT : {
					g.drawImage(DirectionImage.LEFT, coordinate.x,
							coordinate.y, imageSize, imageSize, null);
					break;
				}
			}

		} else { /* Co nhieu hon 1 huong */
			Direction direction = this.getListDirection().get(
					this.getCurrentDir());

			switch (direction) { /* Xet huong de chon hinh phu hop */
				case UP : {
					g.drawImage(DirectionImage.SUP, coordinate.x, coordinate.y,
							imageSize, imageSize, null);
					break;
				}
				case DOWN : {
					g.drawImage(DirectionImage.SDOWN, coordinate.x,
							coordinate.y, imageSize, imageSize, null);
					break;
				}
				case RIGHT : {
					g.drawImage(DirectionImage.SRIGHT, coordinate.x,
							coordinate.y, imageSize, imageSize, null);
					break;
				}
				case LEFT : {
					g.drawImage(DirectionImage.SLEFT, coordinate.x,
							coordinate.y, imageSize, imageSize, null);
					break;
				}
			}
		}

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
