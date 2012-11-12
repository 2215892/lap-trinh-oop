package com.btl.data;

import java.awt.image.BufferedImage;

import com.btl.GameElements.playstate.TerminalColor;
import com.btl.Model.ConversionFunction;

/**
 * Lớp BoxImage. Cung cấp hình ảnh các box cho trò chơi
 */
public abstract class BoxImage {

	/** The Constant RES_DIR. */
	public final static String RES_DIR = ConversionFunction
			.getCurrentDirectory() + "res\\box\\";

	/** The Constant BLUE_BOX. */
	public final static BufferedImage BLUE_BOX = ConversionFunction
			.loadImage(RES_DIR + "BLUE_BOX.png");

	/** The Constant GREEN_BOX. */
	public final static BufferedImage GREEN_BOX = ConversionFunction
			.loadImage(RES_DIR + "GREEN_BOX.png");

	/** The Constant PINK_BOX. */
	public final static BufferedImage PINK_BOX = ConversionFunction
			.loadImage(RES_DIR + "PINK_BOX.png");

	/** The Constant RED_BOX. */
	public final static BufferedImage RED_BOX = ConversionFunction
			.loadImage(RES_DIR + "RED_BOX.png");

	/** The Constant YELLOW_BOX. */
	public final static BufferedImage YELLOW_BOX = ConversionFunction
			.loadImage(RES_DIR + "YELLOW_BOX.png");

	/**
	 * Lấy ảnh hộp tương ứng với màu.
	 * 
	 * @param color
	 *            màu của hộp
	 * @return ảnh hộp tương ứng với màu
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
