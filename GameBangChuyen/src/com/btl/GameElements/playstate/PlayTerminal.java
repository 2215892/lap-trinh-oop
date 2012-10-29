package com.btl.GameElements.playstate;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelTerminal;

public class PlayTerminal extends ModelTerminal implements Drawable {
	private TerminalColor color = TerminalColor.DEFAULT;
	public static final int SIZE = PlaySquare.SIZE;
	private static BufferedImage picture;
	private static final String resDir = "E:\\Working project\\OOP\\res\\SQUARE.png";

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
		if (this.getType() == 0) {
			this.setColor(TerminalColor.DEFAULT);
		}
	}

	@Override
	public void paint(Graphics g) {
		Point coordinate = ConversionFunction.positionToLocation(getPosition(),
				SIZE);

		g.drawImage(PlayTerminal.picture, coordinate.x - 8, coordinate.y - SIZE
				/ 2 - 7, null);

	}

	@Override
	public boolean contains(Point point) {
		Point logicCoordinate = ConversionFunction.locationToPosition(point,
				SIZE);

		if (logicCoordinate.equals(getPosition()))
			return true;

		return false;
	}

	public TerminalColor getColor() {
		return color;
	}

	public void setColor(TerminalColor color) {
		this.color = color;
	}

}
