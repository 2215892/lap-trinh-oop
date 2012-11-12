package com.oop.mapcreation.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.data.ItemImage;
import com.oop.gamepanel.Drawable;
import com.oop.mapcreation.MapCreation;
import com.oop.model.ModelObject;

/**
 * class này là abtract class cha của tất cả các class của các đối tượng hình
 * ảnh hiển thị ra màn hình class này định nghĩa tất cả các phương thức
 * (abstract method) mà các đối tượng vẽ cấn thực thi để có thể hiển ra màn hình
 * vẽ tất cả các class con như TreeMap(để vẽ cây ),TerminalIcon (để vẽ
 * terminal)... đều phải extends class này
 * 
 * @author mai tien khai
 * 
 */
public abstract class ItemMap implements Drawable, ModelObject {

	/** chiều cao của ảnh cần vẽ. */
	protected int height;

	/** anh cần vẽ ra màn hình. */
	protected BufferedImage image;

	/** id của ảnh vẽ ra. */
	protected int imageId;

	/** Màu hiển thị vị trí này không được phép vẽ ảnh. */
	protected final Color INVALID_COLOR = new Color(255, 0, 0, 150);

	/** biến kiểm tra xem có thể vẽ với vị trí đầu vào position hay không. */
	protected boolean isValid;

	/** điểm gần nhất xo với mắt nhìn vào lưới ô vuông. */
	protected Point nearestPoint;

	/** điểm đầu vào cho đối tượng vẽ. */
	protected Point position;

	/** cạnh lưới ô vuông của bản đồ cần vẽ. */
	protected int side;
	/**
	 * vị trí góc trái trên cùng để đặt ảnh vào của đối tượng hiển thị trên màn
	 * hình.
	 */
	protected Point topLeftPoint;

	/** loại của ảnh vẽ ra, ví dụ : cây, ô vuông, terminal ... */
	protected int type;

	/** chiều rộng của ảnh cần vẽ ra màn hình. */
	protected int width;

	/**
	 * Hàm khởi tạo đối tượng.
	 * 
	 * @param position
	 *            - điểm đầu vào(click chuột).
	 * @param side
	 *            - cạnh lưới ô vuông của map đang vẽ.
	 * @param image
	 *            - ảnh hiển thị.
	 */
	public ItemMap(Point position, int side, BufferedImage image) {
		this.position = position;
		this.side = side;
		setImage(image);
		calculateImageDimension();
		isValid = true;
		nearestPoint = new Point(position.x, position.y);
		calculateNearestPoint();
		identifyImageType();
		calculateImageId();
	}

	/**
	 * Tính xem vị trí này có thể đặt ảnh hay không.
	 * 
	 * @param map
	 *            the map
	 */
	public abstract void calculateValidation(MapCreation map);

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
	public abstract boolean contains(Point p);

	/**
	 * Gets the entry point.
	 * 
	 * @return the entry point
	 */
	public Point getEntryPoint() {
		return position;
	}

	/**
	 * Gets the image.
	 * 
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Gets the image id.
	 * 
	 * @return the image id
	 */
	public int getImageId() {
		return imageId;
	}

	/**
	 * Gets the image type.
	 * 
	 * @return the image type
	 */
	public int getImageType() {
		return type;
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.Model.ModelObject#getPosition()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.model.ModelObject#getPosition()
	 */
	@Override
	public Point getPosition() {
		return nearestPoint;
	}

	/**
	 * Gets the validation.
	 * 
	 * @return the validation
	 */
	public boolean getValidation() {
		return isValid;
	}

	/**
	 * phương thức cho việc vẽ ảnh ra màn hình.
	 * 
	 * @param g
	 *            - đối tượng graphics
	 */
	@Override
	public abstract void paint(Graphics g);

	/**
	 * Sets the image.
	 * 
	 * @param image
	 *            the new image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * Sets the validation.
	 * 
	 * @param validation
	 *            the new validation
	 */
	public void setValidation(boolean validation) {
		isValid = validation;
	}

	/**
	 * tính kích thước tương ứng với size của cạnh ô vuông trong lưới ô vuông,
	 * giả sử cạnh chuẩn là 19.
	 */
	private void calculateImageDimension() {
		Double tempHeight = (double) side * image.getHeight(null) / 19;
		Double tempWidth = (double) side * image.getWidth(null) / 19;
		height = tempHeight.intValue();
		width = tempWidth.intValue();
	}

	/**
	 * tính id của ảnh từ ảnh nhập vào.
	 */
	protected void calculateImageId() {
		imageId = ItemImage.getId(image);
	}

	/** tính vị trí ô vuông gần mắt nhất nhìn từ ảnh. */
	protected abstract void calculateNearestPoint();

	/** tính điểm góc trái trên cùng để vẽ ảnh. */
	protected abstract void calculateTopLeft();

	/** xác định loại ảnh cần vẽ : tree, factory... */
	protected abstract void identifyImageType();

}
