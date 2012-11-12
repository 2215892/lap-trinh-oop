package com.oop.mapcreation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.gamepanel.Drawable;
import com.oop.mapcreation.buttons.ButtonForDraw;
import com.oop.mapcreation.buttons.DrawingButton;

// TODO: Auto-generated Javadoc
/**
 * class này dùng để định nghĩa các đối tượng là một item trong menu cho các
 * button.
 * 
 * @author mai tien khai
 */
public class MenuItem implements Drawable {

	/** The hover color. */
	public final Color hoverColor = new Color(255, 0, 0, 100);

	/** ảnh hiện thị cho item. */
	private BufferedImage itemImage;

	/** chiều cao của ảnh hiển thị. */
	private int height;

	/** chiều rộng của ảnh hiển thị. */
	private int width;

	/** xem item có ẩn hay không. */
	private boolean isHidden;

	/** vị trí để vẽ item. */
	private Point position;

	/** button chua menuitem nay. */
	private ButtonForDraw button;

	/** biến kiểm tra trang thái chuột có hover vào item hay không. */
	private boolean hover;

	/**
	 * Khởi tạo một đối tượng MenuItem.
	 * 
	 * @param itemImage
	 *            - ảnh hiển thị.
	 * @param position
	 *            - vị trí của ảnh hiển thị(góc trái trên cùng).
	 * @param width
	 *            - chiều rộng ảnh hiển thị.
	 * @param height
	 *            - chiều cao ảnh hiển thị.
	 * @param button
	 *            - button tương ứng của Menu Item này.
	 */
	public MenuItem(BufferedImage itemImage, Point position, int width,
			int height, ButtonForDraw button) {
		this.itemImage = itemImage;
		this.height = height;
		this.width = width;
		isHidden = true;
		this.position = position;
		this.button = button;
		hover = false;
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		if (!isHidden) {
			/* ve nen cho menu render */

			g.setColor(Color.white);
			g.fillRect(position.x, position.y, width, height);
			/* ve bieu tuong cho menu item */
			g.drawImage(itemImage, position.x, position.y, width, height, null);

			if (hover) {
				g.setColor(hoverColor);
				g.fillRect(position.x, position.y, width, height);
			}

		}

	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point p) {
		/* khi ma item menu hien ra thi moi tinh la click chuot vao hay khong */
		if ((!isHidden)
				&& (this.position.x <= p.x && (this.position.x + this.width) >= p.x)
				&& (this.position.y <= p.y && (this.position.y + this.height) >= p.y)) {
			return true;
		}
		return false;

	}

	/**
	 * thay đổi trạng thái của MenuItemm.
	 */
	public void changeState() {
		if (isHidden)
			isHidden = false;
		else
			isHidden = true;
	}

	/**
	 * Gửi yêu cầu cho Button chọn Item này.
	 */
	public void choose() {
		button.selectItem(this);
	}

	/**
	 * Hiển thị MenuItem.
	 */
	public void show() {
		isHidden = false;
	}

	/**
	 * Ẩn Menuitem.
	 */
	public void hide() {
		isHidden = true;
	}

	/**
	 * Khi được ấn vào thì ẩn đi menu của Button tương ứng.
	 */
	public void hideButtonMenu() {
		button.hideMenu();
	}

	/**
	 * Gets the image.
	 * 
	 * @return the image
	 */
	public BufferedImage getImage() {
		return itemImage;
	}

	/**
	 * Gets the button.
	 * 
	 * @return the button
	 */
	public DrawingButton getButton() {
		return button;
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
}
