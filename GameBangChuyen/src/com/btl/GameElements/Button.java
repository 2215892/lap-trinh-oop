package com.btl.GameElements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import com.btl.GameEngine.Clickable;
import com.btl.GameEngine.Drawable;

// TODO: Auto-generated Javadoc
/**
 * The Class Button.
 */
public abstract class Button implements Drawable, Clickable {

	private Point position;
	private int width, height;
	private Image img;

	/**
	 * Khoi tao button.
	 * 
	 * @param p
	 *            toa do cua button
	 */
	public Button(Point p) {
		this.position = p;
		this.width = 0;
		this.height = 0;
	}

	/**
	 * Dat hinh anh cho button.
	 * 
	 * @param img
	 *            hinh anh muon dat
	 */
	public void setImage(Image img) {
		this.img = img;

		/* Dat lai kich thuoc cua nut */
		this.width = this.img.getWidth(null);
		this.height = this.img.getHeight(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Clickable#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point p) {

		/* Kiem tra p co nam trong nut khong */
		if ((this.position.x <= p.x && (this.position.x + this.width) >= p.x)
				&& (this.position.y <= p.y && (this.position.y + this.height) >= p.y)) {
			this.onClick();
			return true;
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Clickable#onClick()
	 */

	@Override
	public void paint(Graphics g) {
		g.drawImage(this.img, this.position.x, this.position.y, null);

	}

}
