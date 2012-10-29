package com.btl.GameElements.playstate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelTerminal;
import com.btl.Model.RandomEnum;

public class PlayTerminal extends ModelTerminal implements Drawable {
	private static RandomEnum<TerminalColor> rnd = new RandomEnum<TerminalColor>(
			TerminalColor.class);
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

		if (this.getType() == 0) {
			this.setColor(TerminalColor.DEFAULT);
		} else {
			this.setColor(rnd.random());
		}

		if (PlayTerminal.picture == null) {
			PlayTerminal.picture = ConversionFunction.loadImage(resDir);

		}
	}
	@Override
	public void paint(Graphics g) {
		Point coordinate = ConversionFunction.positionToLocation(getPosition(),
				SIZE);
		coordinate.x -= 8;
		coordinate.y -= (SIZE / 2 + 7);

		g.drawImage(PlayTerminal.picture, coordinate.x, coordinate.y, null);

		Graphics2D g2 = (Graphics2D) g;
		Color color = null;
		switch (this.getColor()) {
			case DEFAULT :
				color = Color.gray;
				break;
			case PINK :
				color = Color.pink;
				break;
			case BLUE :
				color = Color.blue;
				break;
			case GREEN :
				color = Color.green;
				break;
			case RED :
				color = Color.red;
				break;
			case YELLOW :
				color = Color.yellow;
				break;
			default :
				break;
		}
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(color);
		g2.fillOval(coordinate.x + 20, coordinate.y + 13, 10, 5);

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
