package com.btl.GameElements;

import java.awt.Color;
import java.awt.Image;

import com.btl.Model.ConversionFunction;

// TODO: Auto-generated Javadoc
/**
 * The Class BoxImage.
 */
public abstract class BoxImage {

	/** The Constant RES_DIR. */
	public final static String RES_DIR = "E:\\Working project\\OOP\\res\\";

	/** The Constant BOX_IMAGE. */
	public final static Image BOX_IMAGE = ConversionFunction.loadImage(RES_DIR
			+ "box.png");

	/**
	 * Gets the box image.
	 * 
	 * @param flag
	 *            the flag
	 * @return the box image
	 */
	public static Image getBoxImage(Color color) {
		return BOX_IMAGE;
	}
}
