package com.oop.mapcreation.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.data.ItemImage;
import com.oop.gamepanel.Drawable;
import com.oop.mapcreation.MapCreation;
import com.oop.model.ModelObject;
// TODO: Auto-generated Javadoc
// TODO: Auto - generated Javadoc
/**
 * class n?�? l?�?abtract class cha của tất cả các class của các đối tượng hình
 * ảnh hiển th?�?ra m?�? hình class n?�? định nghĩa tất cả các phương thức
 * (abstract method) m?�?các đối tượng vẽ cấn thực thi đ�?có th?�?hiển ra m?�? hình
 * vẽ tất cả các class con như TreeMap(đ�?vẽ cây ),TerminalIcon (đ�?vẽ
 * terminal)... đều phải extends class n?�?
 * 
 * @author mai tien khai
 * 
 */
public abstract class ItemMap implements Drawable, ModelObject {

	/** điểm gần nhất xo với mắt nhìn v?�? lưới ô vuông. */
	protected Point nearestPoint;

	/** điểm đầu v?�? cho đối tượng vẽ. */
	protected Point position;

	/**
	 * v?�?trí góc trái trên cùng đ�?đặt ảnh v?�? của đối tượng hiển th?�?trên m?�?
	 * hình.
	 */
	protected Point topLeftPoint;

	/** anh cần vẽ ra m?�? hình. */
	protected BufferedImage image;

	/** chiều cao của ảnh cần vẽ. */
	protected int height;

	/** chiều rộng của ảnh cần vẽ ra m?�? hình. */
	protected int width;

	/** cạnh lưới ô vuông của bản đ�?cần vẽ. */
	protected int side;

	/** biến kiểm tra xem có th?�?vẽ với v?�?trí đầu v?�? position hay không. */
	protected boolean isValid;
	/** loại của ảnh vẽ ra, ví dụ : cây, ô vuông, terminal ... */
	protected int type;

	/** id của ảnh vẽ ra. */
	protected int imageId;

	/** M?�? hiển th?�?v?�?trí n?�? không được phép vẽ ảnh. */
	protected final Color INVALID_COLOR = new Color(255, 0, 0, 150);

	/**
	 * H?�? khởi tạo đối tượng.
	 * 
	 * @param position
	 *            - điểm đầu v?�?(click chuột).
	 * @param side
	 *            - cạnh lưới ô vuông của map đang vẽ.
	 * @param image
	 *            - ảnh hiển th?�?
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
	 * tính kích thước tương ứng với size của cạnh ô vuông trong lưới ô vuông,
	 * giả sử cạnh chuẩn l?�?19.
	 */
	private void calculateImageDimension() {
		Double tempHeight = (double) side * image.getHeight(null) / 19;
		Double tempWidth = (double) side * image.getWidth(null) / 19;
		height = tempHeight.intValue();
		width = tempWidth.intValue();
	}

	/**
	 * tính điểm góc trái trên cùng đ�?vẽ ảnh.
	 */
	protected abstract void calculateTopLeft();

	/**
	 * tính v?�?trí ô vuông gần mắt nhất nhìn từ ảnh.
	 */
	protected abstract void calculateNearestPoint();

	/** xác định loại ảnh cần vẽ : tree, factory... */
	protected abstract void identifyImageType();

	/**
	 * tính id của ảnh.
	 * 
	 * @param map
	 *            - map đang vẽ
	 */
	public abstract void calculateValidation(MapCreation map);

	/**
	 * phương thức cho việc vẽ ảnh ra m?�? hình.
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

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	/* (non-Javadoc)
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	@Override
	public abstract boolean contains(Point p);

	/**
	 * tính id của ảnh từ ảnh nhập v?�?.
	 */
	protected void calculateImageId() {
		imageId = ItemImage.getId(image);
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.Model.ModelObject#getPosition()
	 */
	/* (non-Javadoc)
	 * @see com.btl.Model.ModelObject#getPosition()
	 */
	@Override
	public Point getPosition() {
		return nearestPoint;
	}

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
	 * Sets the validation.
	 * 
	 * @param validation
	 *            the new validation
	 */
	public void setValidation(boolean validation) {
		isValid = validation;
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
	 * Gets the image type.
	 * 
	 * @return the image type
	 */
	public int getImageType() {
		return type;
	}

	/**
	 * Gets the image id.
	 * 
	 * @return the image id
	 */
	public int getImageId() {
		return imageId;
	}

}
