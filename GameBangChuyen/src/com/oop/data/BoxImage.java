package com.oop.data;

import java.awt.image.BufferedImage;

import com.oop.model.Helper;
import com.oop.play.TerminalColor;

/**
 * Lớp BoxImage. Cung cấp hình ảnh các box cho trò chơi
 */
public abstract class BoxImage {

	/** The Constant RES_DIR. */
	public final static String RES_DIR = Helper
			.getCurrentDirectory() + "res\\box\\";

	/** The Constant BLUE_BOX. */
	public final static BufferedImage BLUE_BOX = Helper
			.loadImage(RES_DIR + "BLUE_BOX.png");

	/** The Constant GREEN_BOX. */
	public final static BufferedImage GREEN_BOX = Helper
			.loadImage(RES_DIR + "GREEN_BOX.png");

	/** The Constant PINK_BOX. */
	public final static BufferedImage PINK_BOX = Helper
			.loadImage(RES_DIR + "PINK_BOX.png");

	/** The Constant RED_BOX. */
	public final static BufferedImage RED_BOX = Helper
			.loadImage(RES_DIR + "RED_BOX.png");

	/** The Constant YELLOW_BOX. */
	public final static BufferedImage YELLOW_BOX = Helper
			.loadImage(RES_DIR + "YELLOW_BOX.png");

	/**
	 * Lấy ảnh hộp tương ứng với m�?.
	 * 
	 * @param color
	 *            m�? của hộp
	 * @return ảnh hộp tương ứng với m�?
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
