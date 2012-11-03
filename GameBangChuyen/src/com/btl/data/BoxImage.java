package com.btl.data;

import java.awt.image.BufferedImage;

import com.btl.GameElements.playstate.TerminalColor;
import com.btl.Model.ConversionFunction;

// TODO: Auto-generated Javadoc
/**
 * The Class BoxImage.
 */
public abstract class BoxImage {

	public final static String RES_DIR = ButtonImage.RES_DIR;

	public final static BufferedImage BLUE_BOX = ConversionFunction
			.loadImage(RES_DIR + "BLUE_BOX.png");

	public final static BufferedImage GREEN_BOX = ConversionFunction
			.loadImage(RES_DIR + "GREEN_BOX.png");
	public final static BufferedImage PINK_BOX = ConversionFunction
			.loadImage(RES_DIR + "PINK_BOX.png");
	public final static BufferedImage RED_BOX = ConversionFunction
			.loadImage(RES_DIR + "RED_BOX.png");
	public final static BufferedImage YELLOW_BOX = ConversionFunction
			.loadImage(RES_DIR + "YELLOW_BOX.png");

	/**
	 * Gets the box image.
	 * 
	 * @param flag
	 *            the flag
	 * @return the box image
	 */
	public static BufferedImage getBoxImage(TerminalColor color) {
		switch (color) {
			case BLUE :
				return BLUE_BOX;
			case DEFAULT :
				return null;
			case GREEN :
				return GREEN_BOX;
			case PINK :
				return PINK_BOX;
			case RED :
				return RED_BOX;
			case YELLOW :
				return YELLOW_BOX;
			default :
				return null;
		}
	}
}
