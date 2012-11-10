package com.btl.GameBoard;

import java.awt.Graphics;
import java.awt.event.MouseListener;

// TODO: Auto-generated Javadoc
/**
 * The Class GameState.
 */
public abstract class GameState implements MouseListener {

	/** The parent. */
	protected GamePanel parent;

	protected GameState lastState;

	/**
	 * Instantiates a new game state.
	 * 
	 * @param parent
	 *            the parent
	 */
	public GameState(GamePanel parent, GameState lastState) {
		this.parent = parent;
		this.lastState = lastState;
	}

	public void changeState(GameState state) {
		parent.setState(state);
	}

	/**
	 * Render, draw graphic.
	 * 
	 * @param g
	 *            the g
	 */
	public abstract void gameRender(Graphics g);

	/**
	 * Update each state.
	 */
	public abstract void update();

}
