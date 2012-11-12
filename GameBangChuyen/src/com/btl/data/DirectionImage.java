package com.btl.data;

import java.awt.image.BufferedImage;

import com.btl.Model.ConversionFunction;

/**
 * Lớp DirectionImage. Cung cấp ảnh cho các đối tượng PlaySwitch, PlaySquare,
 * PlayFactory, PlayTerminal
 */
public abstract class DirectionImage {

	/** The dir. */
	public static String dir = ConversionFunction.getCurrentDirectory()
			+ "res\\direction\\";

	/** The Constant ALEFT. */
	public final static BufferedImage ALEFT = ConversionFunction.loadImage(dir
			+ "ALEFT.png");

	/** The Constant ALEFT1. */
	public final static BufferedImage ALEFT1 = ConversionFunction.loadImage(dir
			+ "ALEFT1.png");

	/** The Constant AUP. */
	public final static BufferedImage AUP = ConversionFunction.loadImage(dir
			+ "AUP.png");

	/** The Constant AUP1. */
	public final static BufferedImage AUP1 = ConversionFunction.loadImage(dir
			+ "AUP1.png");

	/** The Constant DOWN. */
	public final static BufferedImage DOWN = ConversionFunction.loadImage(dir
			+ "DOWN.png");

	/** The Constant LEFT. */
	public final static BufferedImage LEFT = ConversionFunction.loadImage(dir
			+ "LEFT.png");

	/** The Constant LINE. */
	public final static BufferedImage LINE = ConversionFunction.loadImage(dir
			+ "LINE.png");

	/** The Constant SDOWN. */
	public final static BufferedImage SDOWN = ConversionFunction.loadImage(dir
			+ "SDOWN.png");

	/** The Constant SRIGHT. */
	public final static BufferedImage SDOWNLEFT = ConversionFunction
			.loadImage(dir + "SDOWNLEFT.png");

	/** The Constant SLEFT. */
	public final static BufferedImage SLEFT = ConversionFunction.loadImage(dir
			+ "SLEFT.png");

	/** The Constant SQUARE. */
	public final static BufferedImage SQUARE = ConversionFunction.loadImage(dir
			+ "SQUARE.png");

	/** The Constant UPRIGHT. */
	public final static BufferedImage UPRIGHT = ConversionFunction
			.loadImage(dir + "UPRIGHT.png");

}
