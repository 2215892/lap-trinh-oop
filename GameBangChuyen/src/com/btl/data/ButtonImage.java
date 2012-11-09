package com.btl.data;

import java.awt.image.BufferedImage;

import com.btl.Model.ConversionFunction;

public abstract class ButtonImage {
	public final static String RES_DIR = ConversionFunction
			.getCurrentDirectory() + "res/";

	public final static BufferedImage PAUSE_BUTTON = ConversionFunction
			.loadImage(RES_DIR + "PAUSE_BUTTON.png");

	public final static BufferedImage ENDGAME_BUTTON = ConversionFunction
			.loadImage(RES_DIR + "ENDGAME_BUTTON.png");

	public final static BufferedImage CONTINUE_BUTTON = ConversionFunction
			.loadImage(RES_DIR + "CONTINUE_BUTTON.png");

	public final static BufferedImage START_BUTTON = ConversionFunction
			.loadImage(RES_DIR + "TESTSTART.png");

	public final static BufferedImage FACTORY_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "FACTORY_BUTTON_IMAGE.jpg");

	public final static BufferedImage ACTIVE_FACTORY_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "ACTIVE_FACTORY_BUTTON_IMAGE.jpg");
	public final static BufferedImage TERMINAL_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "TERMINAL_BUTTON_IMAGE.jpg");
	public final static BufferedImage ACTIVE_TERMINAL_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "ACTIVE_TERMINAL_BUTTON_IMAGE.jpg");
	public final static BufferedImage DEFAULT_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "DEFAULT_BUTTON_IMAGE.jpg");
	public final static BufferedImage ACTIVE_DEFAULT_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "ACTIVE_DEFAULT_BUTTON_IMAGE.jpg");
	public final static BufferedImage DELETE_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "DELETE_BUTTON_IMAGE.png");
	public final static BufferedImage ACTIVE_DELETE_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "ACTIVE_DELETE_BUTTON_IMAGE.png");

	public final static BufferedImage DELETEALL_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "DELETEALL_BUTTON_IMAGE.png");
	public final static BufferedImage ACTIVE_DELETEALL_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "ACTIVE_DELETEALL_BUTTON_IMAGE.png");
	public final static BufferedImage SQUARE_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "SQUARE_BUTTON_IMAGE.jpg");

	public final static BufferedImage ACTIVE_SQUARE_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "ACTIVE_SQUARE_BUTTON_IMAGE.jpg");
	public final static BufferedImage TREE_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "TREE_BUTTON_IMAGE.gif");
	public final static BufferedImage ACTIVE_TREE_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "ACTIVE_TREE_BUTTON_IMAGE.gif");
	public final static BufferedImage SAVE_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "SAVE_BUTTON_IMAGE.png");
	public final static BufferedImage ACTIVE_SAVE_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "ACTIVE_SAVE_BUTTON_IMAGE.png");
	public final static BufferedImage BACK_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "BACK_BUTTON_IMAGE.png");
	public final static BufferedImage ACTIVE_BACK_BUTTON_IMAGE = ConversionFunction
			.loadImage(RES_DIR + "ACTIVE_BACK_BUTTON_IMAGE.png");

}
