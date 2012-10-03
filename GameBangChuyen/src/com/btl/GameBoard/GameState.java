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

    /**
     * Instantiates a new game state.
     * 
     * @param parent
     *            the parent
     */
    public GameState(GamePanel parent) {
	this.parent = parent;
    }

    /**
     * Update each state.
     */
    public abstract void update();

    /**
     * Render, draw graphic.
     * 
     * @param g
     *            the g
     */
    public abstract void gameRender(Graphics g);

}
