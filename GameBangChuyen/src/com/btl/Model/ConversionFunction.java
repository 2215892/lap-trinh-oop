package com.btl.Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The Class ConversionFunction.
 */
public abstract class ConversionFunction {

	/**
	 * Doi toa do logic sang toa do thuc.
	 * 
	 * @param t
	 *            : toa do logic can doi
	 * @return toa do thuc
	 */
	public static Point logicToReal(Point t) {

		Double ux = (double) (t.x + t.y);
		Double uy = (double) (t.x - t.y) / 2;
		return new Point(ux.intValue(), uy.intValue());
	}

	/**
	 * 
	 * @param t
	 *            : point in real axis
	 * @return: point in logic axis
	 */
	public static Point realToLogic(Point t) {

		Double ux = (double) (2 * t.y + t.x) / 2;
		Double uy = (double) (t.x - 2 * t.y) / 2;
		return new Point(ux.intValue(), uy.intValue());
	}

	public static Point positionToLocation(Point position, int size) {
		return new Point((position.x + position.y) * size,
				(position.x - position.y) * size / 2);
	}

	public static Point locationToPosition(Point location, int size) {
		return new Point((location.x + 2 * location.y) / 2 / size,
				(location.x - 2 * location.y) / 2 / size);
	}

	/**
	 * Load anh.
	 * 
	 * @param fileDir
	 *            duong dan den file anh
	 * @return BufferedImage cua anh
	 */
	public static BufferedImage loadImage(final String fileDir) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileDir));
		} catch (IOException e) {
			System.out.print("\nerror load file" + fileDir);
		}
		return img;
	}

	public static Image flipHorizontally(Image pic) {
		Image img = new BufferedImage(pic.getWidth(null), pic.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.drawImage(pic, pic.getWidth(null), 0, 0, pic.getHeight(null), 0, 0,
				pic.getWidth(null), pic.getHeight(null), null);
		g.dispose();
		return img;
	}

}
