package com.btl.Model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ConversionFunction {
	/**
	 * 
	 * @param t
	 *            : point in logic axis
	 * @return: point in real axis
	 */
	public static Point logicToReal(Point t) {
		Double uy = (double) (t.x - t.y) / Math.sqrt(3);
		Double ux = (double) (t.y + t.x);
		return new Point(ux.intValue(), uy.intValue());
	}

	/**
	 * 
	 * @param t
	 *            : point in real axis
	 * @return: point in logic axis
	 */
	public static Point realToLogic(Point t) {
		Double ux = (double) (Math.sqrt(3) * t.y + t.x) / 2;
		Double uy = (double) (t.x - Math.sqrt(3) * t.y) / 2;
		return new Point(ux.intValue(), uy.intValue());
	}

	public static BufferedImage loadImage(String fileDir) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileDir));
		} catch (IOException e) {
		}
		return img;
	}

}
