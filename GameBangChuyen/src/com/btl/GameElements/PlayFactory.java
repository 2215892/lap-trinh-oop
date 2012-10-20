package com.btl.GameElements;

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

		if (PlayFactory.picture == null) {
			PlayFactory.picture = ConversionFunction.loadImage(resDir);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(final Graphics g) {
		Point coordinate = ConversionFunction.positionToLocation(getPosition(),
				SIZE);
		g.drawImage(PlayFactory.picture, coordinate.x, coordinate.y - SIZE / 2,
				null);

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

}
