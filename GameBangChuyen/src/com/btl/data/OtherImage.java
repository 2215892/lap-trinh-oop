package com.btl.data;

import java.awt.image.BufferedImage;

import com.btl.Model.ConversionFunction;

public abstract class OtherImage {
	public final static String RES_DIR = ConversionFunction
			.getCurrentDirectory() + "res\\";

	public final static BufferedImage BG = ConversionFunction.loadImage(RES_DIR
			+ "BG.bmp");
}
