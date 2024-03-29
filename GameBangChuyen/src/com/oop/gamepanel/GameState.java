package com.oop.gamepanel;

import java.awt.Graphics;
import java.awt.event.MouseListener;

/**
 * The Class GameState.
 */
public abstract class GameState implements MouseListener {

	/** The last state. */
	protected GameState lastState;

	/** The parent. */
	protected GamePanel parent;

	/**
	 * Instantiates a new game state.
	 * 
	 * @param parent
	 *            the parent
	 * @param lastState
	 *            the last state
	 */
	public GameState(GamePanel parent, GameState lastState) {
		this.parent = parent;
		this.lastState = lastState;
	}

	/**
	 * Change state.
	 * 
	 * @param state
	 *            the state
	 */
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
