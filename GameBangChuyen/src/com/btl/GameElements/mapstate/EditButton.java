package com.btl.GameElements.mapstate;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.btl.Model.AuxiliaryFunction;

public class EditButton extends ButtonForHandle {

	public EditButton(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p, normalImage, activeImage, controlCode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(MapCreation map) {
		AuxiliaryFunction.handleEditMap(map);
		this.normalRender();
		map.setInitialMenuState();
		
	}

}
