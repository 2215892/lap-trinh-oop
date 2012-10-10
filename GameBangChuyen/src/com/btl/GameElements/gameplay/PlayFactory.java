package com.btl.GameElements.gameplay;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelFactory;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Play objects.
 */
public class PlayFactory extends ModelFactory implements Drawable {

	public static final int SIZE = PlaySquare.SIZE;
	private static Image picture;
	private static final String resDir = "E:\\Working project\\OOP\\res\\factory.png";

	/**
	 * Instantiates a new play factory.
	 * 
	 * @param p
	 *            the p
	 */
	public PlayFactory(final Point p) {
		super(p);

		if (PlayFactory.picture == null) {
			PlayFactory.picture = ConversionFunction.loadImage(resDir);
		}
	}

	public PlayFactory(final ModelFactory factory) {
		super(factory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(final Graphics g) {
		Point coordinate = this.getCoordinate();
		int imageSize = 2 * SIZE;
		g.drawImage(PlayFactory.picture, coordinate.x, coordinate.y, imageSize,
				imageSize, null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	@Override
	public final boolean contains(final Point point) {
		return false;
	}

	/**
	 * Lay toa do de ve hinh
	 * 
	 * @return toa do goc tren ben trai de ve hinh
	 */
	private Point getCoordinate() {
		Point coordinateLogic = new Point(this.getPosition().x * SIZE,
				this.getPosition().y * SIZE);
		/* Goc tren ben trai */
		Point vertexA = ConversionFunction.logicToReal(coordinateLogic);
		/* Goc tren ben phai */
		Point vertexB = ConversionFunction.logicToReal(new Point(
				coordinateLogic.x + SIZE, coordinateLogic.y));

		return new Point(vertexA.x, vertexB.y);
	}
}
