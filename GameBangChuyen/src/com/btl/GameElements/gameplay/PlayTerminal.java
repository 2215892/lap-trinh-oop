package com.btl.GameElements.gameplay;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelTerminal;

public class PlayTerminal extends ModelTerminal implements Drawable {
	public static final int SIZE = PlaySquare.SIZE;
	private static Image picture;
	private static final String resDir = "E:\\Working project\\OOP\\res\\terminal.pnt";

	public PlayTerminal(Point p) {
		super(p);

		if (PlayTerminal.picture == null) {
			PlayTerminal.picture = ConversionFunction.loadImage(resDir);
		}
	}

	@Override
	public void paint(Graphics g) {
		Point coordinate = this.getCoordinate();
		int imageSize = 2 * SIZE;
		g.drawImage(PlayTerminal.picture, coordinate.x, coordinate.y,
				imageSize, imageSize, null);

	}

	@Override
	public boolean contains(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Lay toa do de ve hinh
	 * 
	 * @return toa do goc tren ben trai de ve hinh
	 */
	private Point getCoordinate() {
		Point coordinateLogic = new Point(this.getPosition().x * SIZE,
				this.getPosition().y * SIZE);
		/* Goc tren ben trai */
		Point vertexA = ConversionFunction.logicToReal(coordinateLogic);
		/* Goc tren ben phai */
		Point vertexB = ConversionFunction.logicToReal(new Point(
				coordinateLogic.x + SIZE, coordinateLogic.y));

		return new Point(vertexA.x, vertexB.y);
	}

}
