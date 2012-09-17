package com.btl.GameEngine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

// TODO: Auto-generated Javadoc
/**
 * The Class Button.
 */
public abstract class Button implements Drawable, Clickable {

	private Point position;
	private int width, height;
	private Image originImg, img;

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
	 * @param width
	 *            chieu rong
	 * @param height
	 *            chieu cao
	 */
	public void setImage(Image img, int width, int height) {
		this.originImg = img;
		this.width = width;
		this.height = height;

		this.img = new BufferedImage(this.width, this.height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = this.img.getGraphics();
		g.drawImage(this.originImg, 0, 0, this.width, this.height, null);
		g.dispose();

	}

	/**
	 * Thay doi kich thuoc.
	 * 
	 * @param width
	 *            chieu rong
	 * @param height
	 *            chieu cao
	 */
	public void changeSize(int width, int height) {
		this.width = width;
		this.height = height;

		this.img = new BufferedImage(this.width, this.height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = this.img.getGraphics();
		g.drawImage(this.originImg, 0, 0, this.width, this.height, null);
		g.dispose();
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
