package com.oop.mapcreation.objects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.data.ItemImage;
import com.oop.mapcreation.MapCreation;
import com.oop.model.AuxiliaryFunction;
import com.oop.model.Helper;

/**
 * class này là class của các đối tượng hiển thị ảnh của ô vuông nền trên lưới ô
 * vuông vẽ map Thực hiện các tính toán liên quan đến vẽ ảnh và tính toán sự hợp
 * lệ trong việc vẽ ảnh ra lưới ô vuông.
 * 
 * @author mai tien khai
 */
public class SquareMap extends ItemMap {

	/**
	 * Hàm khởi tạo đối tượng.
	 * 
	 * @param position
	 *            - điểm đầu vào(click chuột)
	 * @param side
	 *            - cạnh lưới ô vuông của map đang vẽ
	 * @param image
	 *            - ảnh hiển thị
	 */
	public SquareMap(Point position, int side, BufferedImage image) {
		super(position, side, image);
		/*
		 * rieng doi voi squareMap thi can tinh lai vi khong theo 19 ma theo 15
		 * voi khop
		 */
		Double newHeight = (double) side * image.getHeight(null) / 15;
		Double newWidth = (double) side * image.getWidth(null) / 15;
		height = newHeight.intValue();
		width = newWidth.intValue();
		calculateTopLeft();
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.ItemMap#calculateTopLeft()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.mapcreation.objects.ItemMap#calculateTopLeft()
	 */
	@Override
	protected void calculateTopLeft() {
		Point realPosition = Helper.logicToReal(position);
		Double spacing = (double) 2 * side / (Math.sqrt(5));
		Point temp = new Point(realPosition.x - spacing.intValue() - 2 * side
				/ 15, realPosition.y - 2 * side / 15);

		topLeftPoint = new Point(temp.x - 2, temp.y + 1);

	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.ItemMap#paint(java.awt.Graphics)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.mapcreation.objects.ItemMap#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		if (isValid)
			g.drawImage(image, topLeftPoint.x, topLeftPoint.y, width, height,
					null);
		else {
			g.setColor(INVALID_COLOR);
			g.fillPolygon(Helper.polygon(position, side, side));
		}
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.ItemMap#contains(java.awt.Point)
	 */
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
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.ItemMap#identifyImageType()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.mapcreation.objects.ItemMap#identifyImageType()
	 */
	@Override
	public void identifyImageType() {
		type = ItemImage.PLATFORM_TYPE;
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.ItemMap#calculateNearestPoint()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.mapcreation.objects.ItemMap#calculateNearestPoint()
	 */
	@Override
	protected void calculateNearestPoint() {
		// TODO Auto - generated method stub

	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see
	 * com.btl.GameElement.ItemMap#calculateValidation(com.btl.GameBoard.MapCreation
	 * )
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oop.mapcreation.objects.ItemMap#calculateValidation(com.oop.mapcreation
	 * .MapCreation)
	 */
	@Override
	public void calculateValidation(MapCreation map) {
		if (AuxiliaryFunction.checkIdenticalPostition(map, position))
			setValidation(true);
		else
			setValidation(false);

	}

}
