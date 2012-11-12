package com.btl.GameElements.playstate;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelFactory;
import com.btl.data.DirectionImage;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Play objects.
 */
public class PlayFactory extends ModelFactory implements Drawable {

	/** The Constant SIZE. */
	public static final int SIZE = PlaySquare.SIZE;

	private static BufferedImage picture;
	private static Random rnd = new Random();
	private ArrayList<PlayTerminal> listTerminals = new ArrayList<PlayTerminal>();

	/**
	 * Instantiates a new play factory.
	 * 
	 * @param factory
	 *            the factory
	 */
	public PlayFactory(final ModelFactory factory) {
		super(factory);
	}

	/**
	 * Instantiates a new play factory.
	 * 
	 * @param p
	 *            the p
	 */
	public PlayFactory(final Point p) {
		super(p);
	}

	/**
	 * Adds the terminal.
	 * 
	 * @param terminal
	 *            the terminal
	 */
	public void addTerminal(PlayTerminal terminal) {
		if (terminal.getColor() != TerminalColor.DEFAULT)
			this.listTerminals.add(terminal);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	@Override
	public final boolean contains(final Point point) {
		Point logicCoordinate = ConversionFunction.locationToPosition(point,
				SIZE);

		if (logicCoordinate.equals(getPosition()))
			return true;

		return false;
	}

	/**
	 * Make box.
	 * 
	 * @return the play box
	 */
	public PlayBox makeBox() {

		ArrayList<PlayTerminal> terminalWaiting = new ArrayList<PlayTerminal>();

		for (PlayTerminal terminal : this.listTerminals) {
			if (terminal.isWaiting()
					&& terminal.getColor() != TerminalColor.DEFAULT)
				terminalWaiting.add(terminal);
		}

		if (terminalWaiting.size() != 0) {
			PlayTerminal terminal = terminalWaiting.get(rnd
					.nextInt(terminalWaiting.size()));
			PlayBox box = new PlayBox(ConversionFunction.positionToLocation(
					this.getPosition(), PlaySwitch.SIZE), terminal.getColor());
			box.setDestination(this.getNeighbor(this.getDirection())
					.getPosition());
			return box;
		}

		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(final Graphics g) {
		if (PlayFactory.picture == null) {
			PlayFactory.picture = DirectionImage.SQUARE;
		}
		Point coordinate = ConversionFunction.positionToLocation(getPosition(),
				SIZE);
		g.drawImage(PlayFactory.picture, coordinate.x - 8, coordinate.y - 9
				- SIZE / 2, null);

	}
}
