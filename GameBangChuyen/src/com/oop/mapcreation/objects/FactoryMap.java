package com.oop.mapcreation.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.oop.gamepanel.Drawable;
import com.oop.model.Helper;
import com.oop.model.ModelFactory;
import com.oop.model.ModelObject;

// TODO: Auto-generated Javadoc
/**
 * class này là đối tượng factory trong map vẽ, được kế thừa từ ModelFactory nó
 * hiển thị trên hình vẽ ô vuông hiển thị cho Factory, khi chơi game box chạy ra
 * từ ô này.
 * 
 * @author mai tien khai
 */
public class FactoryMap extends ModelFactory implements Drawable, ModelObject {

	/** màu của ô factory không có switch đi ra. */
	public final Color WRONG_COLOR = Color.pink;

	/** màu hiển thi ô factory trên lưới ô vuông. */
	private Color factoryColor = Color.black;

	/** chiều cao của ô vuông Factory. */
	private int height;

	/** chiểu rộng của ô vuông Factory. */
	private int width;

	/** kiểm tra xem factory có đầu ra hay không. */
	private boolean outlet;

	/**
	 * Hàm khởi tạo đối tượng này.
	 * 
	 * @param p
	 *            - vị trí ô click chuột vào
	 * @param width
	 *            - chiều rộng logic ô hiển thị
	 * @param height
	 *            - chiều cao logic ô hiển thị
	 */
	public FactoryMap(Point p, int width, int height) {
		super(p);
		this.height = height;
		this.width = width;
		outlet = false;
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		if (outlet)
			g.setColor(factoryColor);
		else
			g.setColor(WRONG_COLOR);
		g.fillPolygon(Helper.polygon(this.position, width, height));
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point p) {

		Point logicPoint = Helper.realToLogic(p);
		if ((this.position.x <= logicPoint.x && (this.position.x + this.width) >= logicPoint.x)
				&& (this.position.y <= logicPoint.y && (this.position.y + this.height) >= logicPoint.y)) {
			return true;
		} else
			return false;

	}

	/**
	 * Sets the outlet state.
	 * 
	 * @param outletState
	 *            the new outlet state
	 */
	public void setOutletState(boolean outletState) {
		this.outlet = outletState;
	}

}
