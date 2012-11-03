package com.btl.GameBoard;

import java.awt.Graphics;

import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class GamePanel.
 */
public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameState currentState = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
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

	}

}
