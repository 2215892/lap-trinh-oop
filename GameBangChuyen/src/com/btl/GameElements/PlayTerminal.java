package com.btl.GameElements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelTerminal;

public class PlayTerminal extends ModelTerminal implements Drawable {
	public static final int SIZE = PlaySquare.SIZE;
	private static Image picture;
	private static final String resDir = "E:\\Working project\\OOP\\res\\terminal.png";

	public PlayTerminal(Point p) {
		super(p);

		if (PlayTerminal.picture == null) {
			PlayTerminal.picture = ConversionFunction.loadImage(resDir);
		}
	}

	public PlayTerminal(ModelTerminal terminal) {
		super(terminal);
		if (PlayTerminal.picture == null) {
			PlayTerminal.picture = ConversionFunction.loadImage(resDir);
		}
	}

	@Override
	public void paint(Graphics g) {
		Point coordinate = ConversionFunction.positionToLocation(getPosition(),
				SIZE);
		int imageSizeWidth = 2 * SIZE;
		int imageSizeHeigh = (int) (imageSizeWidth / Math.sqrt(3));

		g.drawImage(PlayTerminal.picture, coordinate.x, coordinate.y,
				imageSizeWidth, imageSizeHeigh, null);

	}

	@Override
	public boolean contains(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

}
