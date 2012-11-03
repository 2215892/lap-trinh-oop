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
	public static final int SIZE = PlaySquare.SIZE;
	private static BufferedImage picture;
	private static final String resDir = "E:\\Working project\\OOP\\res\\SQUARE.png";
	private static RandomEnum<TerminalColor> rnd = new RandomEnum<TerminalColor>(
			TerminalColor.class);
	private BufferedImage buffer;
	private TerminalColor color = TerminalColor.DEFAULT;

	private boolean isWaiting = true;

	public PlayTerminal(ModelTerminal terminal) {
		super(terminal);

		if (this.getType() == 0) {
			this.setColor(null);
			isWaiting = false;
		} else {
			TerminalColor color = null;
			do {
				color = rnd.random();
			} while (color == TerminalColor.DEFAULT);

			this.setColor(color);
		}

		if (PlayTerminal.picture == null) {
			PlayTerminal.picture = ConversionFunction.loadImage(resDir);
		}

		if (buffer == null) {
			buffer = new BufferedImage(picture.getWidth(), picture.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			update();

		}
	}

	public PlayTerminal(Point p) {
		super(p);

		if (PlayTerminal.picture == null) {
			PlayTerminal.picture = ConversionFunction.loadImage(resDir);
		}
	}
	public boolean boxArrived(PlayBox box) {
		if (box.getColor() == this.getColor()) {
			TerminalColor color = null;
			do {
				color = rnd.random();
			} while (color == TerminalColor.DEFAULT || color == getColor());

			this.setColor(color);
			update();
			return true;
		} else {
			return false;
		}

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

	public boolean isWaiting() {
		return isWaiting;
	}

	@Override
	public void paint(Graphics g) {
		Point coordinate = ConversionFunction.positionToLocation(getPosition(),
				SIZE);
		coordinate.x -= 8;
		coordinate.y -= (SIZE / 2 + 9);

		g.drawImage(buffer, coordinate.x, coordinate.y, null);

	}

	public void setColor(TerminalColor color) {
		this.color = color;
	}

	public void setWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}

	public void update() {
		Graphics2D g2 = (Graphics2D) buffer.getGraphics();

		if (this.color != null) {
			g2.drawImage(picture, 0, 0, null);

			Color color = null;
			switch (this.getColor()) {
				case DEFAULT :
					color = Color.gray;
					break;
				case PINK :
					color = new Color(255, 0, 247);
					break;
				case BLUE :
					color = new Color(105, 162, 214);
					break;
				case GREEN :
					color = new Color(74, 219, 54);
					break;
				case RED :
					color = Color.red;
					break;
				case YELLOW :
					color = new Color(232, 240, 24);
					break;
				default :
					break;
			}
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
					RenderingHints.VALUE_ANTIALIAS_ON);

			g2.setColor(Color.black);
			g2.fillOval(19, 12, 12, 7);
			g2.setColor(color);
			g2.fillOval(20, 13, 10, 5);
		}
	}

}
