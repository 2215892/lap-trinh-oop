package com.oop.mapcreation.objects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.data.ItemImage;
import com.oop.mapcreation.MapCreation;
import com.oop.model.AuxiliaryFunction;
import com.oop.model.Helper;

/**
 * class này là class của các đối tượng hiển thị ảnh cây trên lưới ô vuông vẽ
 * map Thực hiện các tính toán liên quan đến vẽ ảnh và tính toán sự hợp lệ trong
 * việc vẽ ảnh ra lưới ô vuông.
 * 
 * @author mai tien khai
 */
public class TreeMap extends ItemMap {

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
	public TreeMap(Point position, int side, BufferedImage image) {
		super(position, side, image);
		/* tinh chieu rong cua anh theo chieu cao */

		calculateTopLeft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.mapcreation.objects.ItemMap#calculateTopLeft()
	 */
	@Override
	protected void calculateTopLeft() {
		// TODO Auto - generated method stub
		Point temp = new Point(position.x - 5 * side / 2, position.y + 3 * side
				/ 2);
		Point temp2 = Helper.logicToReal(temp);
		topLeftPoint = new Point(temp2.x, temp2.y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.mapcreation.objects.ItemMap#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		g.drawImage(image, topLeftPoint.x, topLeftPoint.y, width, height, null);
		if (!isValid) {
			g.setColor(INVALID_COLOR);
			g.fillPolygon(Helper.polygon(position, side, side));
			g.drawImage(image, topLeftPoint.x, topLeftPoint.y, width, height,
					null);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.mapcreation.objects.ItemMap#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point p) {
		// TODO Auto - generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.mapcreation.objects.ItemMap#calculateNearestPoint()
	 */
	@Override
	protected void calculateNearestPoint() {
		// TODO tinh diem gan nhat
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.mapcreation.objects.ItemMap#identifyImageType()
	 */
	@Override
	public void identifyImageType() {
		type = ItemImage.TREE_TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oop.mapcreation.objects.ItemMap#calculateValidation(com.oop.mapcreation
	 * .MapCreation)
	 */
	@Override
	public void calculateValidation(MapCreation map) {
		/*
		 * dieu kien de hinh ve Tree hop le la: + entryPoint khong trung voi bat
		 * cu element nao trong map dang ve
		 */
		if (AuxiliaryFunction.checkIdenticalPostition(map, position))
			setValidation(true);
		else
			setValidation(false);
	}

}
