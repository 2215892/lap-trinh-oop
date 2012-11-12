package com.oop.play.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.oop.data.DirectionImage;
import com.oop.gamepanel.Drawable;
import com.oop.model.Helper;
import com.oop.model.ModelTerminal;
import com.oop.model.RandomEnum;
import com.oop.play.TerminalColor;

/**
 * The Class PlayTerminal.
 */
public class PlayTerminal extends ModelTerminal implements Drawable {

	/** The Constant SIZE. */
	public static final int SIZE = PlaySquare.SIZE;
	private static BufferedImage picture = DirectionImage.SQUARE;;
	private static RandomEnum<TerminalColor> rnd = new RandomEnum<TerminalColor>(
			TerminalColor.class);
	private BufferedImage buffer;
	private TerminalColor color = TerminalColor.DEFAULT;

	private boolean isWaiting = true;

	/**
	 * Instantiates a new play terminal.
	 * 
	 * @param terminal
	 *            the terminal
	 */
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

		if (buffer == null) {
			buffer = new BufferedImage(picture.getWidth(), picture.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			update();

		}
	}

	/**
	 * Instantiates a new play terminal.
	 * 
	 * @param p
	 *            the p
	 */
	public PlayTerminal(Point p) {
		super(p);
	}

	/**
	 * Box arrived.
	 * 
	 * @param box
	 *            the box
	 * @return true, if successful
	 */
	public boolean boxArrived(PlayBox box) {
		if (box.getColor() == this.getColor()) {
			this.setBoxCount(this.getBoxCount() - 1);

			TerminalColor color = null;
			if (this.getBoxCount() != 0) {
				do {
					color = rnd.random();
				} while (color == TerminalColor.DEFAULT || color == getColor());

			} else
				color = TerminalColor.DEFAULT;

			this.setColor(color);
			update();

			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean contains(Point point) {
		Point logicCoordinate = Helper.locationToPosition(point, SIZE);

		if (logicCoordinate.equals(getPosition()))
			return true;

		return false;
	}

	/**
	 * Gets the color.
	 * 
	 * @return the color
	 */
	public TerminalColor getColor() {
		return color;
	}

	/**
	 * Checks if is waiting.
	 * 
	 * @return true, if is waiting
	 */
	public boolean isWaiting() {
		return isWaiting;
	}

	@Override
	public void paint(Graphics g) {

		Point coordinate = Helper.positionToLocation(getPosition(), SIZE);
		coordinate.x -= 8;
		coordinate.y -= (SIZE / 2 + 9);

		g.drawImage(buffer, coordinate.x, coordinate.y, null);

	}

	/**
	 * Sets the color.
	 * 
	 * @param color
	 *            the new color
	 */
	public void setColor(TerminalColor color) {
		this.color = color;
	}

	/**
	 * Sets the waiting.
	 * 
	 * @param isWaiting
	 *            the new waiting
	 */
	public void setWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}

	/**
	 * Update.
	 */
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
