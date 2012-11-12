package com.oop.data;

import java.awt.image.BufferedImage;

import com.oop.model.Helper;

/**
 * Lớp DirectionImage. Cung cấp ảnh cho các đối tượng PlaySwitch, PlaySquare,
 * PlayFactory, PlayTerminal
 */
public abstract class DirectionImage {

	/** The dir. */
	public static String dir = Helper.getCurrentDirectory()
			+ "res\\direction\\";

	/** The Constant ALEFT. */
	public final static BufferedImage ALEFT = Helper.loadImage(dir
			+ "ALEFT.png");

	/** The Constant ALEFT1. */
	public final static BufferedImage ALEFT1 = Helper.loadImage(dir
			+ "ALEFT1.png");

	/** The Constant AUP. */
	public final static BufferedImage AUP = Helper.loadImage(dir
			+ "AUP.png");

	/** The Constant AUP1. */
	public final static BufferedImage AUP1 = Helper.loadImage(dir
			+ "AUP1.png");

	/** The Constant DOWN. */
	public final static BufferedImage DOWN = Helper.loadImage(dir
			+ "DOWN.png");

	/** The Constant LEFT. */
	public final static BufferedImage LEFT = Helper.loadImage(dir
			+ "LEFT.png");

	/** The Constant LINE. */
	public final static BufferedImage LINE = Helper.loadImage(dir
			+ "LINE.png");

	/** The Constant SDOWN. */
	public final static BufferedImage SDOWN = Helper.loadImage(dir
			+ "SDOWN.png");

	/** The Constant SRIGHT. */
	public final static BufferedImage SDOWNLEFT = Helper
			.loadImage(dir + "SDOWNLEFT.png");

	/** The Constant SLEFT. */
	public final static BufferedImage SLEFT = Helper.loadImage(dir
			+ "SLEFT.png");

	/** The Constant SQUARE. */
	public final static BufferedImage SQUARE = Helper.loadImage(dir
			+ "SQUARE.png");

	/** The Constant UPRIGHT. */
	public final static BufferedImage UPRIGHT = Helper
			.loadImage(dir + "UPRIGHT.png");

}
