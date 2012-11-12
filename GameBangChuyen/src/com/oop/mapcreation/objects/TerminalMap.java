package com.oop.mapcreation.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.oop.gamepanel.Drawable;
import com.oop.model.Helper;
import com.oop.model.ModelTerminal;
// TODO: Auto-generated Javadoc
// TODO: Auto - generated Javadoc
/**
 * class n?�? l?�?đối tượng terminal trong map vẽ, được kế thừa từ ModelTerminal
 * nó hiển th?�?trên hình vẽ ô vuông hiển th?�?cho termional, khi chơi game box
 * chạy v?�? ô n?�? sẽ được điểm.
 * 
 * @author mai tien khai
 * 
 */
public class TerminalMap extends ModelTerminal implements Drawable {

	/** The no box. */
	private final int NO_BOX = 2;

	/** m?�? hiển th?�?của ô vuông terminl. */
	public final Color terminalColor = Color.yellow;

	/** The no box. */
	private int noBox;

	/** cạnh hình vuông trong lưới ô vuông trong map. */
	private int side;

	/**
	 * H?�? khởi tạo đối tượng.
	 * 
	 * @param p
	 *            - v?�?trí ô hiển th?�?
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
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	/* (non-Javadoc)
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(terminalColor);
		g.fillPolygon(Helper.polygon(position, side, side));
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	/* (non-Javadoc)
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point p) {
		// TODO Auto - generated method stub
		return false;
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

	/**
	 * Gets the box number.
	 * 
	 * @return the box number
	 */
	public int getBoxNumber() {
		return noBox;
	}

}
