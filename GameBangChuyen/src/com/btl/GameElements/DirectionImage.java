package com.btl.GameElements;

import java.awt.Image;

import com.btl.Model.ConversionFunction;

// TODO: Auto-generated Javadoc
/**
 * The Class DirectionImage.
 */
public abstract class DirectionImage {

	/** The dir. */
	public static String dir = "E:\\Working project\\OOP\\res\\";

	/** The Constant SQUARE. */
	public final static Image SQUARE = ConversionFunction.loadImage(dir
			+ "SQUARE.png");

	public final static Image DOWN = ConversionFunction.loadImage(dir
			+ "DOWN.png");

	public final static Image LEFT = ConversionFunction.loadImage(dir
			+ "LEFT.png");

	public final static Image UPRIGHT = ConversionFunction.loadImage(dir
			+ "UPRIGHT.png");

	/** The Constant SDOWN. */
	public final static Image SDOWN = ConversionFunction.loadImage(dir
			+ "SDOWN.png");

	/** The Constant SLEFT. */
	public final static Image SLEFT = ConversionFunction.loadImage(dir
			+ "SLEFT.png");

	/** The Constant SRIGHT. */
	public final static Image SDOWNLEFT = ConversionFunction.loadImage(dir
			+ "SDOWNLEFT.png");

	public final static Image ALEFT1 = ConversionFunction.loadImage(dir
			+ "ALEFT1.png");

	public final static Image AUP1 = ConversionFunction.loadImage(dir
			+ "AUP1.png");

	public final static Image ALEFT = ConversionFunction.loadImage(dir
			+ "ALEFT.png");

	public final static Image AUP = ConversionFunction.loadImage(dir
			+ "AUP.png");

}
