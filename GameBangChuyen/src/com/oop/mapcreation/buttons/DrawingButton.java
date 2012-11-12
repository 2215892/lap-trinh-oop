package com.oop.mapcreation.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.gamepanel.Button;
// TODO: Auto-generated Javadoc
// TODO: Auto - generated Javadoc
/**
 * class n?�? mục đích tạo ra các đối tượng button trong phần vẽ map n?�?, tất cả
 * các class cho button trong phần vẽ map đều phải extends class n?�?.
 * 
 * @author mai tien khai
 * 
 */
public class DrawingButton extends Button {

	/** m?�? của button khi m?�?chuột đi v?�?. */
	public final Color hoverColor = new Color(255, 0, 0, 100);

	/** ảnh hiện th?�?của Button khi ?�?trạng thái bình thường. */
	private BufferedImage normalRender;

	/** ảnh hiển th?�?của Button khi ?�?trạng thái kích hoạt. */
	private BufferedImage activeRender;

	/** Biến kiểm tra xem trạng thái của Button có phải normal hau không. */
	private boolean normal;

	/** tên của Button. */
	private String name;

	/** mã điều khiển của button. */
	private int controlCode;

	/** The hover. */
	private boolean hover;

	private boolean visible = true;

	/**
	 * H?�? khởi tạo của button.
	 * 
	 * @param p
	 *            - tọa đ�?đặt Button
	 * @param normalImage
	 *            - ảnh hiển th?�?của Button ?�?trạng thái bình thường
	 * @param activeImage
	 *            - ảnh hiển th?�?của Button ?�?trạng thái kích hoạt (khi ấn v?�?)
	 * @param controlCode
	 *            - mã điều khiển của Button
	 */

	public DrawingButton(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p);
		/* ban dau nut o trang thai binh thuong */
		normal = true;
		normalRender = normalImage;
		activeRender = activeImage;
		this.controlCode = controlCode;
		hover = false;
	}

	/**
	 * dat chieu dai rong cho bieu tuong cua button.
	 * 
	 * @param height
	 *            chieu cao cua button can ve
	 * @param width
	 *            chieu rong cua button can ve
	 */
	public void setDimension(int height, int width) {
		this.height = height;
		this.width = width;
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.Button#paint(java.awt.Graphics)
	 */
	/* (non-Javadoc)
	 * @see com.btl.GameElements.playstate.Button#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		/* khi ma hien thi moi ve */
		if (visible) {
			g.drawImage(this.img, this.getPosition().x, this.getPosition().y,
					null);
			if (hover) {
				g.setColor(hoverColor);
				g.fillRect(getPosition().x, getPosition().y, width, height);
				g.setColor(Color.red);
				g.drawString(name, getPosition().x, getPosition().y + height
						+ 8);
			}
		}

	}

	/**
	 * Gets the state.
	 * 
	 * @return the state
	 */
	public boolean getState() {
		return normal;
	}

	/**
	 * Chuyển v?�?trạng thái bình thường cho Button.
	 */
	public void normalRender() {
		normal = true;
		this.setImage(normalRender, this.width, this.height);

	}

	/**
	 * Chuyển sang trạng thái kích hoạt cho Button.
	 */
	public void activeRender() {
		normal = false;
		this.setImage(activeRender, this.width, this.height);
	}

	/**
	 * Gets the control code.
	 * 
	 * @return the control code
	 */
	public int getControlCode() {
		return controlCode;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the hover state.
	 * 
	 * @param hoverState
	 *            the new hover state
	 */
	public void setHoverState(boolean hoverState) {
		hover = hoverState;
	}

	/**
	 * Gets the height.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public int getwidth() {
		return this.width;
	}

}
