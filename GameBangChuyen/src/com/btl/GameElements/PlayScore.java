package com.btl.GameElements;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Random;

import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;

public class PlayScore implements Drawable {

	private static Random rnd = new Random();
	private int score;
	private float alpha = 1.0f;
	private static final int TIME_SPAN = 50;
	private Point position;
	private int count = 0;
	private int height;

	private boolean isDone = false;
	public boolean isDone() {
		return isDone;
	}

	public PlayScore() {
		this.height = 20 + rnd.nextInt(25);
	}

	public PlayScore(Point position, int score) {
		this();
		this.setPosition(position);
		this.setScore(score);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}

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
			Point coordinate = ConversionFunction.positionToLocation(
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
	@Override
	public boolean contains(Point point) {
		return false;
	}

}
