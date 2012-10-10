package com.btl.GameElements.gameplay;

import java.awt.Image;

import com.btl.Model.ConversionFunction;

// TODO: Auto-generated Javadoc
/**
 * The Class DirectionImage.
 */
public abstract class DirectionImage {

    /** The dir. */
    public static String dir = "E:\\Working project\\OOP\\res\\";

    /** The Constant UP. */
    public final static Image UP = ConversionFunction.loadImage(dir + "UP.png");

    /** The Constant DOWN. */
    public final static Image DOWN = ConversionFunction.loadImage(dir
	    + "DOWN.png");

    /** The Constant LEFT. */
    public final static Image LEFT = ConversionFunction.loadImage(dir
	    + "LEFT.png");

    /** The Constant RIGHT. */
    public final static Image RIGHT = ConversionFunction.loadImage(dir
	    + "RIGHT.png");

    /** The Constant SUP. */
    public final static Image SUP = ConversionFunction.loadImage(dir
	    + "SUP.png");

    /** The Constant SDOWN. */
    public final static Image SDOWN = ConversionFunction.loadImage(dir
	    + "SDOWN.png");

    /** The Constant SLEFT. */
    public final static Image SLEFT = ConversionFunction.loadImage(dir
	    + "SLEFT.png");

    /** The Constant SRIGHT. */
    public final static Image SRIGHT = ConversionFunction.loadImage(dir
	    + "SRIGHT.png");

}
