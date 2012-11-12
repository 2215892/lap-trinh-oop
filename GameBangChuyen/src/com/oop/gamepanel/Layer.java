package com.oop.gamepanel;

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
	private BufferedImage buffer, background;
	private boolean isVisible = true;
	private int width, height;

	/** The list drawable. */
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
	 * Them doi tuong drawable cho layer quan ly.
	 * 
	 * @param drawable
	 *            doi tuong can them
	 */
	public void addDrawable(Drawable drawable) {
		this.listDrawable.add(drawable);
	}

	/**
	 * Lam rong layer.
	 */
	public void empty() {
		this.listDrawable.clear();
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
	 * Lay hinh anh cua layer.
	 * 
	 * @return the layer
	 */
	public BufferedImage getLayer() {
		if (this.isVisible) {
			return buffer;
		} else
			return null;
	}
	
	/**
	 * Gets the list drawable.
	 * 
	 * @return the list drawable
	 */
	public ArrayList<Drawable> getListDrawable() {
		return this.listDrawable;
	}

	/**
	 * Hide.
	 */
	public void hide() {
		this.isVisible = false;
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

		if (background != null) {
			g.drawImage(background, 0, 0, this.width, this.height, null);
		}

		for (int i = 0; i < this.listDrawable.size(); ++i) {
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
	 * Sets the background.
	 * 
	 * @param background
	 *            the new background
	 */
	public void setBackground(BufferedImage background) {
		this.background = background;
	}

	/**
	 * khi ma muon xoa het cac Drawable cua Layer.
	 */
	public void emptyLayer() {
		listDrawable.clear();
	}

}
