package com.btl.Model;

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
    public static Point logicToReal(final Point t) {
	Double uy = (double) (t.x - t.y) / Math.sqrt(3);
	Double ux = (double) (t.y + t.x);
	return new Point(ux.intValue(), uy.intValue());
    }

    /**
     * Doi toa do thuc sang logic.
     * 
     * @param t
     *            : toa do thuc can doi
     * @return toa do logic
     */
    public static Point realToLogic(final Point t) {
	Double ux = (double) (Math.sqrt(3) * t.y + t.x) / 2;
	Double uy = (double) (t.x - Math.sqrt(3) * t.y) / 2;
	return new Point(ux.intValue(), uy.intValue());
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

}
