package com.oop.mapcreation.buttons;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.mapcreation.MapCreation;
import com.oop.model.AuxiliaryFunction;

/**
 * The Class EditButton.
 */
public class EditButton extends ButtonForHandle {

	/**
	 * Instantiates a new edits the button.
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
	public EditButton(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p, normalImage, activeImage, controlCode);
		// TODO Auto-generated constructor stub
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
		AuxiliaryFunction.handleEditMap(map);
		this.normalRender();
		map.setInitialMenuState();

	}

}
