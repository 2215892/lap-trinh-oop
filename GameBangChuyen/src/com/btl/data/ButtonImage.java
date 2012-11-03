package com.btl.data;

import java.awt.image.BufferedImage;

import com.btl.Model.ConversionFunction;

public abstract class ButtonImage {
	public final static String RES_DIR = "E:\\Working project\\OOP\\res\\";

	public final static BufferedImage PAUSE_BUTTON = ConversionFunction
			.loadImage(RES_DIR + "PAUSE_BUTTON.png");

	public final static BufferedImage ENDGAME_BUTTON = ConversionFunction
			.loadImage(RES_DIR + "ENDGAME_BUTTON.png");

	public final static BufferedImage CONTINUE_BUTTON = ConversionFunction
			.loadImage(RES_DIR + "CONTINUE_BUTTON.png");
}
