package com.oop.data;

import java.awt.image.BufferedImage;

import com.oop.model.Helper;

// TODO: Auto-generated Javadoc
/**
 * Lớp OtherImage. Cung cập một số hình ảnh cho chương trình
 */
public abstract class OtherImage {

	/** The Constant RES_DIR. */
	public final static String RES_DIR = Helper.getCurrentDirectory() + "res\\";

	/** The Constant BG. */
	public final static BufferedImage BG = Helper.loadImage(RES_DIR + "BG.png");

	/** The Constant ICON. */
	public final static BufferedImage ICON = Helper.loadImage(RES_DIR
			+ "ICON.png");

	/** The Constant TITLE_BG. */
	public final static BufferedImage TITLE_BG = Helper.loadImage(RES_DIR
			+ "TITLE_BG.png");
}
