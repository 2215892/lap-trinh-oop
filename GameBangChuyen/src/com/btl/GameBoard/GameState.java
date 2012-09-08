package com.btl.GameBoard;

import java.awt.Graphics;
import java.awt.event.MouseListener;

public abstract class GameState implements MouseListener {

	protected GamePanel parent;

	public GameState(GamePanel parent) {
		this.parent = parent;
	}

	/**
	 * Update each state
	 */
	public abstract void update();

	/**
	 * Render, draw graphic
	 */
	public abstract void gameRender(Graphics g);

}
