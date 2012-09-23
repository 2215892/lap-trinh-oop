package com.btl.GameBoard;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameState currentState = null;

	public void setState(GameState state) {
		if (this.currentState != null)
			this.removeMouseListener(this.currentState);

		this.currentState = state;
		this.addMouseListener(this.currentState);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (this.currentState != null)
			this.currentState.gameRender(g);
	}

}
