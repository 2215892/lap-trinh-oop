package com.oop.gamepanel;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// TODO: Auto-generated Javadoc
/**
 * The Class GamePanel.
 */
public class GamePanel extends JPanel {

	/** The Constant WIDTH. */
	public static final int WIDTH = 1;

	/** The width. */
	public int width = 685;

	/** The height. */
	public int height = 485;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new game panel.
	 */
	public GamePanel() {
		super();
		this.setPreferredSize(new Dimension(width, height));
	}

	private GameState currentState = null;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (this.currentState != null)
			this.currentState.gameRender(g);
	}

	/**
	 * Sets the state.
	 * 
	 * @param state
	 *            the new state
	 */
	public void setState(GameState state) {
		if (this.currentState != null)
			this.removeMouseListener(this.currentState);

		this.currentState = state;
		this.addMouseListener(this.currentState);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				repaint();

			}
		});

	}
}
