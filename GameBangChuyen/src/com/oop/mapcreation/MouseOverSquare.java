package com.oop.mapcreation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.oop.gamepanel.Drawable;
import com.oop.model.Helper;
// TODO: Auto-generated Javadoc
// TODO: Auto - generated Javadoc
/**
 * class này định nghĩa đối tượng hiện thị phản ánh tính vẽ được hay không tại
 * vị trí di chuyển của chuột trên lưới ô vuông. Nhưng chỉ trong trường hợp là
 * vẽ switch
 * 
 * @author mai tien khai
 * 
 */
public class MouseOverSquare implements Drawable {

	/** màu hiển thị có thể vẽ được switch ở đây. */
	public static Color VALID_COLOR = new Color(0, 0, 255, 150);

	/** màu hiển thị không thể vẽ được switch ở đây. */
	public static Color INVALID_COLOR = new Color(255, 0, 0, 150);

	/** vi tri góc trái trên cung của ô chuột move vào. */
	private Point position;

	/** chiều dài logic ô vuông cần hiển thị. */
	private int height;

	/** chiều rộng logic của ô vuông cần hiển thị. */
	private int width;

	/** tinh hợp lệ của ô vuông định vẽ switch. */
	private boolean isValid;

	/**
	 * Khởi tạo đối tượng.
	 * 
	 * @param position
	 *            - vị trí ô hiển thị
	 * @param width
	 *            - chiều rộng logic
	 * @param height
	 *            - chiều cao logic
	 */
	public MouseOverSquare(Point position, int width, int height) {
		this.position = position;
		this.height = height;
		this.width = width;
		isValid = false;
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		if (isValid)
			g.setColor(VALID_COLOR);
		else
			g.setColor(INVALID_COLOR);
		g.fillPolygon(Helper.polygon(position, width, height));

	}

	/**
	 * Sets the validation.
	 * 
	 * @param isvalid
	 *            the new validation
	 */
	public void setValidation(boolean isvalid) {
		this.isValid = isvalid;
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

}
