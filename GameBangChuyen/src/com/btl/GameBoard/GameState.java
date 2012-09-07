package com.btl.GameBoard;

import java.awt.Graphics;
import java.awt.event.MouseListener;

public abstract class GameState implements MouseListener {

	protected GamePanel parent;

	public GameState(GamePanel parent) {
		this.parent = parent;
		this.parent.addMouseListener(this);
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
