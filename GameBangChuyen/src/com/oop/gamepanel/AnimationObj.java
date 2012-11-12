package com.oop.gamepanel;

import java.awt.Graphics;

/**
 * The Class AnimationObj.
 */
public abstract class AnimationObj implements Drawable {

	private boolean isRunning = false;

	/**
	 * Checks if is running.
	 * 
	 * @return true, if is running
	 */
	public boolean isRunning() {
		return this.isRunning;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.gamepanel.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}

}
