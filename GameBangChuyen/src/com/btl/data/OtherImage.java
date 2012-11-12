package com.btl.data;

import java.awt.image.BufferedImage;

import com.btl.Model.ConversionFunction;

/**
 * Lớp OtherImage. Cung cập một số hình ảnh cho chương trình
 */
public abstract class OtherImage {

	/** The Constant RES_DIR. */
	public final static String RES_DIR = ConversionFunction
			.getCurrentDirectory() + "res\\";

	/** The Constant BG. */
	public final static BufferedImage BG = ConversionFunction.loadImage(RES_DIR
			+ "BG.bmp");

	/** The Constant ICON. */
	public final static BufferedImage ICON = ConversionFunction
			.loadImage(RES_DIR + "ICON.png");
}
