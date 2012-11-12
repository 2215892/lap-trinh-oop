package com.btl.GameElements.mapstate;

import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import com.btl.Model.AuxiliaryFunction;

public class NewButton extends ButtonForHandle {

	public NewButton(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p, normalImage, activeImage, controlCode);
		
	}

	@Override
	public void handle(MapCreation map) {
		/*nhac nguoi choi luu file cu*/
		String message = "Ban co muon luu file nay truoc khi chuyen sang ve file moi hay khong ? ";
		int check = JOptionPane.showConfirmDialog(map.getParent(), message);
		boolean saveState = true;
		if (check == JOptionPane.OK_OPTION){
			saveState = AuxiliaryFunction.handleMenuSave(map);
		}
		if (saveState){
			AuxiliaryFunction.deleteAll(map);
			map.setFileName(null);
		}
		map.setInitialMenuState();
	}

}
