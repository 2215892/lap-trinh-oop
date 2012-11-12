package com.oop.mapcreation.buttons;

import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import com.oop.mapcreation.MapCreation;
import com.oop.model.AuxiliaryFunction;

/**
 * The Class NewButton.
 */
public class NewButton extends ButtonForHandle {

	/**
	 * Instantiates a new new button.
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
	public NewButton(Point p, BufferedImage normalImage,
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
		/* nhac nguoi choi luu file cu */
		String message = "Ban co muon luu file nay truoc khi chuyen sang ve file moi hay khong ? ";
		int check = JOptionPane.showConfirmDialog(map.getParent(), message);
		boolean saveState = true;
		if (check == JOptionPane.OK_OPTION) {
			saveState = AuxiliaryFunction.handleMenuSave(map);
		}
		if (saveState) {
			AuxiliaryFunction.deleteAll(map);
			map.setFileName(null);
		}
		map.setInitialMenuState();
	}

}
