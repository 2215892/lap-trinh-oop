package com.oop.mapcreation.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import com.oop.gamepanel.Drawable;
import com.oop.model.Helper;
import com.oop.model.Direction;
import com.oop.model.ModelSwitch;

/**
 * class n?�? l?�?đối tượng switch trong map vẽ, được kế thừa từ ModelSwitch nó
 * hiển th?�?trên hình vẽ ô vuông hiển th?�?cho Switch, ô vuông hiển th?�?hướng
 * hiện thời của switch.
 * 
 * @author mai tien khai
 */
public class SwitchMap extends ModelSwitch implements Drawable {

	/** hiện th?�?khi m?�?switch không đi tới một terminal n?�?. */
	public final Color WRONG_COLOR = Color.PINK;

	/** m?�? của ô hiển th?�? */
	private Color switchColor = Color.orange;

	/** m?�? hiển th?�? */
	private Color renderColor;

	/** chiều cao logic ô switch. */
	private int height;

	/** chiều rộng ô switch. */
	private int width;

	/**
	 * dùng đ�?kiểm tra khi box đi theo switch n?�? có đi mãi không dừng hay
	 * không.
	 */
	private boolean infinity;

	/**
	 * H?�? khởi tạo đối tượng n?�?.
	 * 
	 * @param p
	 *            - v?�?trí ô click chuột v?�?
	 * @param width
	 *            - chiều rộng logic ô hiển th?�?
	 * @param height
	 *            - chiều cao logic ô hiển th?�?
	 */
	public SwitchMap(Point p, int width, int height) {
		super(p);
		this.height = height;
		this.width = width;
		renderColor = switchColor;
		infinity = false;
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
		if (!infinity)
			g.setColor(renderColor);
		else
			g.setColor(WRONG_COLOR);
		g.fillPolygon(Helper.polygon(this.position, width, height));

		// Point realPosition = ConversionFunction.logicToReal(position);
		Double realSquareSide = (double) width * 2 / (Math.sqrt(5));
		int side = realSquareSide.intValue();
		// Point topLeft = new Point(realPosition.x - side,realPosition.y);

		Point a = position;
		Point b = new Point(a.x + side, a.y);
		Point c = new Point(a.x + side, a.y - side);
		Point d = new Point(a.x, a.y - side);
		Point mad = getMidPoint(a, d);
		Point rmad = Helper.logicToReal(mad);
		Point mbc = getMidPoint(b, c);
		Point rmbc = Helper.logicToReal(mbc);
		Point mcd = getMidPoint(c, d);
		Point rmcd = Helper.logicToReal(mcd);
		Point mab = getMidPoint(a, b);
		Point rmab = Helper.logicToReal(mab);
		Polygon p = new Polygon();
		Point base = null;
		g.setColor(Color.blue);
		Direction currentDirection = this.getDirection();
		if (currentDirection != null) {

			if (currentDirection == Direction.LEFT) {
				p.addPoint(rmad.x, rmad.y);
				p.addPoint(rmbc.x, rmbc.y);
				p.addPoint(rmcd.x, rmcd.y);
				base = new Point(a.x + side / 4, a.y);

			} else if (currentDirection == Direction.RIGHT) {
				p.addPoint(rmab.x, rmab.y);
				p.addPoint(rmbc.x, rmbc.y);
				p.addPoint(rmad.x, rmad.y);

				base = new Point(a.x + side / 4, a.y - side / 2);
			} else if (currentDirection == Direction.DOWN) {
				p.addPoint(rmab.x, rmab.y);
				p.addPoint(rmbc.x, rmbc.y);

				p.addPoint(rmcd.x, rmcd.y);
				base = new Point(a.x, a.y - side / 4);
			} else {
				p.addPoint(rmab.x, rmab.y);
				// p.addPoint(rmbc.x,rmbc.y);
				p.addPoint(rmad.x, rmad.y);
				p.addPoint(rmcd.x, rmcd.y);
				base = new Point(a.x + side / 2, a.y - side / 4);
			}

			g.fillPolygon(p);
			g.fillPolygon(Helper.polygon(base, side / 2, side / 2));

		}

	}

	/**
	 * Tính trung điểm của một đoạn thẳng.
	 * 
	 * @param p1
	 *            the p1
	 * @param p2
	 *            the p2
	 * @return trung điểm của đoạn thẳng
	 */
	private Point getMidPoint(Point p1, Point p2) {
		return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	}

	/**
	 * Đặt kích thước.
	 * 
	 * @param width
	 *            the width
	 * @param height
	 *            chiều cao logic
	 */
	public void setDimension(int width, int height) {
		this.width = width;
		this.height = height;
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

		Point logicPoint = Helper.realToLogic(p);
		if ((this.position.x <= logicPoint.x && (this.position.x + this.width) >= logicPoint.x)
				&& (this.position.y <= logicPoint.y && (this.position.y + this.height) >= logicPoint.y)) {
			return true;
		} else
			return false;

	}

	/**
	 * đặt m?�? ?�?trạng thái mặc định.
	 */
	public void setDefaultColor() {
		renderColor = switchColor;
	}

	/**
	 * Gets the height.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the infinity state.
	 * 
	 * @return the infinity state
	 */
	public boolean getInfinityState() {
		return infinity;
	}

	/**
	 * Sets the infinity state.
	 * 
	 * @param infinity
	 *            the new infinity state
	 */
	public void setInfinityState(boolean infinity) {
		this.infinity = infinity;
	}
}
