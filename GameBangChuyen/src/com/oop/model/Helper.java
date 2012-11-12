package com.oop.model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

import javax.imageio.ImageIO;

/**
 * The Class ConversionFunction.
 */
public abstract class Helper {

	/**
	 * Flip horizontally.
	 * 
	 * @param pic
	 *            the pic
	 * @return the buffered image
	 */
	public static BufferedImage flipHorizontally(BufferedImage pic) {
		BufferedImage img = new BufferedImage(pic.getWidth(null),
				pic.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.drawImage(pic, pic.getWidth(null), 0, 0, pic.getHeight(null), 0, 0,
				pic.getWidth(null), pic.getHeight(null), null);
		g.dispose();
		return img;
	}

	/**
	 * Gets the current directory.
	 * 
	 * @return the current directory
	 */
	public static String getCurrentDirectory() {
		String decodedPath = null;
		try {
			decodedPath = (URLDecoder.decode(ClassLoader.getSystemClassLoader()
					.getResource(".").getPath(), "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decodedPath;
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

	/**
	 * Location to position.
	 * 
	 * @param location
	 *            the location
	 * @param size
	 *            the size
	 * @return the point
	 */
	public static Point locationToPosition(Point location, int size) {
		int x = (location.x + 2 * location.y) / 2;
		int y = (location.x - 2 * location.y) / 2;
		if (x < 0 && (x % size != 0))
			x -= size;
		if (y < 0 && (y % size != 0))
			y -= size;
		return new Point(x / size, y / size);
	}

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
	 * chuyen tu toa do ao sang toa do thuc bien mot hinh vuong trong toa do ao
	 * sang toa do thuc.
	 * 
	 * @param s
	 *            the s
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return the polygon
	 */
	public static Polygon polygon(Point s, int width, int height) {
		Point temp;
		Polygon poly = new Polygon();

		temp = Helper.logicToReal(new Point(s.x, s.y));
		poly.addPoint(temp.x, temp.y);

		temp = Helper.logicToReal(new Point(s.x + width, s.y));
		poly.addPoint(temp.x, temp.y);

		temp = Helper.logicToReal(new Point(s.x + width, s.y - height));
		poly.addPoint(temp.x, temp.y);

		temp = Helper.logicToReal(new Point(s.x, s.y - height));
		poly.addPoint(temp.x, temp.y);

		return poly;

	}

	/**
	 * Position to location.
	 * 
	 * @param position
	 *            the position
	 * @param size
	 *            the size
	 * @return the point
	 */
	public static Point positionToLocation(Point position, int size) {
		return new Point((position.x + position.y) * size,
				(position.x - position.y) * size / 2);
	}

	/**
	 * Real to logic.
	 * 
	 * @param t
	 *            : point in real axis
	 * @return the point
	 * @return: point in logic axis
	 */
	public static Point realToLogic(Point t) {

		Double ux = (double) (2 * t.y + t.x) / 2;
		Double uy = (double) (t.x - 2 * t.y) / 2;
		return new Point(ux.intValue(), uy.intValue());
	}
}
