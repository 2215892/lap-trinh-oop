package com.oop.play.objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Random;

import com.oop.gamepanel.Drawable;
import com.oop.model.Helper;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayScore.
 */
public class PlayScore implements Drawable {

	private static Random rnd = new Random();
	private static final int TIME_SPAN = 50;
	private float alpha = 1.0f;
	private int count = 0;
	private int height;
	private boolean isDone = false;
	private Point position;

	private int score;
	
	/**
	 * Instantiates a new play score.
	 */
	public PlayScore() {
		this.height = 20 + rnd.nextInt(25);
	}

	/**
	 * Instantiates a new play score.
	 * 
	 * @param position
	 *            the position
	 * @param score
	 *            the score
	 */
	public PlayScore(Point position, int score) {
		this();
		this.setPosition(position);
		this.setScore(score);
	}

	/* (non-Javadoc)
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point point) {
		return false;
	}

	/**
	 * Gets the position.
	 * 
	 * @return the position
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Gets the score.
	 * 
	 * @return the score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Checks if is done.
	 * 
	 * @return true, if is done
	 */
	public boolean isDone() {
		return isDone;
	}

	/* (non-Javadoc)
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		if (!isDone) {
			count++;
			Graphics2D g2d = (Graphics2D) g;

			// set the opacity
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, alpha));
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			if (this.score < 0) {
				g2d.setColor(Color.red);
			} else {
				g2d.setColor(Color.blue);
			}
			Point coordinate = Helper.positionToLocation(
					getPosition(), PlaySwitch.SIZE);

			String text = Integer.toString(score);
			if (score > 0)
				text = "+" + text;
			Font f = new Font("Dialog", Font.BOLD, 14);
			g.setFont(f);
			g2d.drawString(text, coordinate.x, coordinate.y - height);

			isDone = count == TIME_SPAN;
			alpha -= 1.0f / 50;
			height++;
		}
	}

	/**
	 * Sets the position.
	 * 
	 * @param position
	 *            the new position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}
	
	/**
	 * Sets the score.
	 * 
	 * @param score
	 *            the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

}
