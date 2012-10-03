package com.btl.GameEngine;

import java.awt.Graphics;

// TODO: Auto-generated Javadoc
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
     * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
	// TODO Auto-generated method stub

    }

}
