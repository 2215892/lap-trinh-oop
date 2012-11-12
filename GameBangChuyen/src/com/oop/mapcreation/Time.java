package com.oop.mapcreation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.oop.gamepanel.Drawable;

/**
 * class này để hiển thị thời gian chơi cho game.
 * 
 * @author mai tien khai
 */
public class Time implements Drawable {

	/** kích thước của ảnh hiển thị thời gian. */
	private int height;

	/** vị trí hiển thị thời gian trên map đang vẽ. */
	private Point position;

	/** thời gian hiển thị. */
	private int time;

	/** đơn vị thời gian: giây phút. */
	private String unit;

	/** chiều rộng của ảnh hiên thị. */
	private int width;

	/**
	 * Hàm khởi tạo đối tượng.
	 * 
	 * @param position
	 *            - vị trí đặt ảnh hiển thị
	 * @param width
	 *            - chiều rộng của ảnh
	 * @param height
	 *            - chiều cao của ảnh
	 * @param unit
	 *            - đơn vị thời gian
	 */
	public Time(Point position, int width, int height, String unit) {
		this.position = position;
		this.width = width;
		this.height = height;
		this.unit = unit;
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.gamepanel.Drawable#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point p) {
		/* Kiem tra p co nam trong nut khong */
		if ((this.position.x <= p.x && (this.position.x + this.width) >= p.x)
				&& (this.position.y <= p.y && (this.position.y + this.height) >= p.y)) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Gets the unit.
	 * 
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.gamepanel.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(position.x, position.y, width, height);
		g.setColor(Color.red);
		g.drawRect(position.x, position.y, width, height);
		g.drawString(unit, position.x, position.y + height / 3);
		g.drawString("" + time, position.x + width / 3, position.y + 2 * height
				/ 3);
	}

	/**
	 * Sets the time.
	 * 
	 * @param time
	 *            the new time
	 */
	public void setTime(int time) {
		if ((0 < time) && (time < 60))
			this.time = time;
	}
}
