package com.btl.GameEngine;

import java.awt.Color;
import java.awt.Graphics2D;
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
	private int width, height;

	protected ArrayList<Drawable> listDrawable;

	/**
	 * Instantiates a new layer.
	 * 
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public Layer(int width, int height) {
		this.width = width;
		this.height = height;

		listDrawable = new ArrayList<Drawable>();
	}

	/**
	 * Lay doi tuong bi click chuot.
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

	/**
	 * Lam rong layer.
	 */
	public void empty() {
		this.listDrawable.clear();
	}

	/**
	 * Checks if is visible.
	 * 
	 * @return true, if is visible
	 */
	public boolean isVisible() {
		return this.isVisible;
	}

	/**
	 * Them doi tuong drawable cho layer quan ly.
	 * 
	 * @param drawable
	 *            doi tuong can them
	 */
	public void addDrawable(Drawable drawable) {
		this.listDrawable.add(drawable);
	}

	/**
	 * Xoa doi tuong Drawable khoi danh sach quan ly.
	 * 
	 * @param drawable
	 *            doi tuong can xoa
	 */
	public void removeDrawable(Drawable drawable) {
		this.listDrawable.remove(drawable);
	}

	/**
	 * Render.
	 */
	public void render() {
		if (buffer == null)
			buffer = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) this.buffer.getGraphics();

		/* Xoa nen buffer */
		g.setBackground(new Color(255, 255, 255, 0));
		g.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
		int size = this.listDrawable.size();
		for (int i = 0; i < size; ++i) {
			this.listDrawable.get(i).paint(g);
		}

		g.dispose();
	}
	/**
	 * Show.
	 */
	public void show() {
		this.isVisible = true;
	}

	/**
	 * Hide.
	 */
	public void hide() {
		this.isVisible = false;
	}

	/**
	 * Lay hinh anh cua layer.
	 * 
	 * @return the layer
	 */
	public BufferedImage getLayer() {
		if (this.isVisible)
			return buffer;
		else
			return null;
	}

	public ArrayList<Drawable> getListDrawable() {
		return this.listDrawable;
	}
}
