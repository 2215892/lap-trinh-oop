package com.btl.GameElements.playstate;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.btl.GameEngine.Drawable;

// TODO: Auto-generated Javadoc
/**
 * The Class Button.
 */
public class Button implements Drawable {

	private BufferedImage originImg;
	protected BufferedImage img;
	private Point position;
	protected int width;
	protected int height;

	/**
	 * Khoi tao button.
	 * 
	 * @param p
	 *            toa do cua button
	 */
	public Button(Point p) {
		this.setPosition(p);
		this.width = 0;
		this.height = 0;
	}

	public Button() {
		this.setPosition(new Point(0, 0));
		this.width = 0;
		this.height = 0;
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
		if ((this.getPosition().x <= p.x && (this.getPosition().x + this.width) >= p.x)
				&& (this.getPosition().y <= p.y && (this.getPosition().y + this.height) >= p.y)) {
			return true;
		} else
			return false;
	}

	@Override
	public void paint(Graphics g) {
		if (img != null)
			g.drawImage(this.img, this.getPosition().x, this.getPosition().y,
					null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.btl.GameEngine.Clickable#onClick()
	 */

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
	public void setImage(BufferedImage img, int width, int height) {
		this.originImg = img;
		this.width = width;
		this.height = height;

		this.img = new BufferedImage(this.width, this.height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = this.img.getGraphics();
		g.drawImage(this.originImg, 0, 0, this.width, this.height, null);
		g.dispose();

	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

}
