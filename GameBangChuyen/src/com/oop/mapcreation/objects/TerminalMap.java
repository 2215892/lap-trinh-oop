package com.oop.mapcreation.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.oop.gamepanel.Drawable;
import com.oop.model.Helper;
import com.oop.model.ModelTerminal;

/**
 * class này là đối tượng terminal trong map vẽ, được kế thừa từ ModelTerminal
 * nó hiển thị trên hình vẽ ô vuông hiển thị cho termional, khi chơi game box
 * chạy vào ô này sẽ được điểm.
 * 
 * @author mai tien khai
 * 
 */
public class TerminalMap extends ModelTerminal implements Drawable {

	/** màu hiển thị của ô vuông terminl. */
	public final Color terminalColor = Color.yellow;

	/** The no box. */
	private final int NO_BOX = 2;

	/** The no box. */
	private int noBox;

	/** cạnh hình vuông trong lưới ô vuông trong map. */
	private int side;

	/**
	 * Hàm khởi tạo đối tượng.
	 * 
	 * @param p
	 *            - vị trí ô hiển thị
	 * @param side
	 *            - cạnh lưới ô vuông
	 */
	public TerminalMap(Point p, int side) {
		super(p);
		this.side = side;
		noBox = NO_BOX;
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
		// TODO Auto - generated method stub
		return false;
	}

	/**
	 * Gets the box number.
	 * 
	 * @return the box number
	 */
	public int getBoxNumber() {
		return noBox;
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
		g.setColor(terminalColor);
		g.fillPolygon(Helper.polygon(position, side, side));
	}

	/**
	 * Sets the box bumber.
	 * 
	 * @param boxNumber
	 *            the new box bumber
	 */
	public void setBoxBumber(int boxNumber) {
		noBox = boxNumber;
	}

}
