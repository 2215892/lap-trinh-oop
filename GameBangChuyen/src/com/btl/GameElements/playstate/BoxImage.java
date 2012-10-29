package com.btl.GameElements.playstate;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.btl.Model.ConversionFunction;

// TODO: Auto-generated Javadoc
/**
 * The Class BoxImage.
 */
public abstract class BoxImage {

	/** The Constant RES_DIR. */
	public final static String RES_DIR = "E:\\Working project\\OOP\\res\\";

	/** The Constant BOX_IMAGE. */
	public final static BufferedImage BOX_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "box.png");

	/**
	 * Gets the box image.
	 * 
	 * @param flag
	 *            the flag
	 * @return the box image
	 */
	public static BufferedImage getBoxImage(Color color) {
		return BOX_IMAGE;
	}
}
