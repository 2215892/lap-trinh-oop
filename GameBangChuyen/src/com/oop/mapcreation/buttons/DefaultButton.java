package com.oop.mapcreation.buttons;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.mapcreation.MapCreation;

/**
 * The Class DefaultButton.
 */
public class DefaultButton extends ButtonForHandle {

	/**
	 * Instantiates a new default button.
	 * 
	 * @param p
	 *            the p
	 * @param normalImage
	 *            the normal image
	 * @param activeImage
	 *            the active image
	 * @param controlCode
	 *            the control code
	 */
	public DefaultButton(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p, normalImage, activeImage, controlCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oop.mapcreation.buttons.ButtonForHandle#handle(com.oop.mapcreation
	 * .MapCreation)
	 */
	@Override
	public void handle(MapCreation map) {
		// TODO Auto-generated method stub

	}

}
