package com.oop.mapcreation.buttons;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.mapcreation.MapCreation;

public class DefaultButton extends ButtonForHandle {

	public DefaultButton(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p, normalImage, activeImage, controlCode);
	}

	@Override
	public void handle(MapCreation map) {
		// TODO Auto-generated method stub
		
	}

}
