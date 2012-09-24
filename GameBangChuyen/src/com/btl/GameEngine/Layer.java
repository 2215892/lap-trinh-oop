package com.btl.GameEngine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Layer.
 */
public class Layer {

	/** The buffer. */
	private BufferedImage buffer;
	private boolean isVisible = true;

	private ArrayList<Drawable> listDrawable;

	public Layer(int width, int height) {
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		listDrawable = new ArrayList<Drawable>();
	}

	/**
	 * Lay doi tuong bi click chuot
	 * 
	 * @param p
	 *            Toa do click chuot
	 * @return Doi tuong bi click, null neu khong co
	 */
	public Drawable getClickedObj(Point p) {
		if (this.isVisible) {
			for (Drawable i : listDrawable) {
				if (i.contains(p))
					return i;
			}
		}
		return null;
	}

	public boolean isVisible() {
		return this.isVisible;
	}

	public void addDrawable(Drawable drawable) {
		this.listDrawable.add(drawable);
	}

	public void render() {
		Graphics2D g = (Graphics2D) this.buffer.getGraphics();

		/* Xoa nen buffer */
		g.setBackground(new Color(255, 255, 255, 0));
		g.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());

		for (Drawable i : listDrawable) {
			i.paint(g);
		}

		g.dispose();
	}

	public void show() {
		this.isVisible = true;
	}

	public void hide() {
		this.isVisible = false;
	}

	/**
	 * Lay hinh anh cua layer.
	 * 
	 * @return the layer
	 */
	public Image getLayer() {
		if (this.isVisible)
			return buffer;
		else
			return new BufferedImage(buffer.getWidth(), buffer.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
	}
}
