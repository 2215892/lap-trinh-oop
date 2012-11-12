package com.oop.mapcreation.buttons;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.mapcreation.MapCreation;
import com.oop.model.AuxiliaryFunction;

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
