package com.btl.GameElements.mapstate;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.btl.GameEngine.Drawable;
import com.btl.Model.AuxiliaryFunction;
import com.btl.Model.ConversionFunction;
import com.btl.data.ItemImage;
// TODO: Auto - generated Javadoc

/**
 * class này là class của các đối tượng hiển thị ảnh của terminal trên lưới ô
 * vuông vẽ map Thực hiện các tính toán liên quan đến vẽ ảnh và tính toán sự hợp
 * lệ trong việc vẽ ảnh ra lưới ô vuông.
 * 
 * @author mai tien khai
 */
public class TerminalIcon extends ItemMap {

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

	public TerminalIcon(Point position, int side, BufferedImage image) {
		super(position, side, image);
		calculateTopLeft();
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.ItemMap#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		if (isValid)
			g.drawImage(image, topLeftPoint.x, topLeftPoint.y, width, height,
					null);

		else {
			g.setColor(INVALID_COLOR);
			g.fillPolygon(ConversionFunction.polygon(position, side, side));
			ArrayList<Point> pointCovered = ItemImage.getSquareCovered(image,
					position, side);
			for (Point i : pointCovered) {
				g.fillPolygon(ConversionFunction.polygon(i, side, side));
			}
			g.drawImage(image, topLeftPoint.x, topLeftPoint.y, width, height,
					null);
		}

	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.ItemMap#calculateTopLeft()
	 */
	@Override
	protected void calculateTopLeft() {
		Point temp = null;
		if (this.image == ItemImage.TRUCK)
			temp = new Point(position.x - 3 * side, position.y - 2 * side);
		else if (this.image == ItemImage.TRUCK_DOWN)
			temp = new Point(position.x, position.y + side);
		else if (this.image == ItemImage.AIRPLANE)
			temp = new Point(position.x - 4 * side, position.y);
		else if (this.image == ItemImage.AIRPLANE_RIGHT)
			temp = new Point(position.x - 2 * side, position.y + 2 * side);
		else if (this.image == ItemImage.SHIP)
			temp = new Point(position.x - 6 * side, position.y + 2 * side);
		else if (this.image == ItemImage.SHIP_RIGHT)
			temp = new Point(position.x - 4 * side, position.y + 4 * side);
		topLeftPoint = ConversionFunction.logicToReal(temp);
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.ItemMap#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point p) {
		// TODO Auto - generated method stub
		return false;
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.ItemMap#calculateNearestPoint()
	 */
	@Override
	protected void calculateNearestPoint() {
		Point temp = null;
		if (this.image == ItemImage.TRUCK)
			temp = new Point(position.x, position.y - 4 * side);
		else if (this.image == ItemImage.TRUCK_DOWN)
			temp = new Point(position.x + 4 * side, position.y);
		else if (this.image == ItemImage.AIRPLANE)
			temp = new Point(position.x - side, position.y);
		else if (this.image == ItemImage.AIRPLANE_RIGHT)
			temp = new Point(position.x, position.y + side);
		else if (this.image == ItemImage.SHIP)
			temp = new Point(position.x - side, position.y);
		else if (this.image == ItemImage.SHIP_RIGHT)
			temp = new Point(position.x, position.y + side);
		nearestPoint = temp;
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.ItemMap#identifyImageType()
	 */
	@Override
	public void identifyImageType() {
		type = ItemImage.VEHICLE_TYPE;
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see
	 * com.btl.GameElement.ItemMap#calculateValidation(com.btl.GameBoard.MapCreation
	 * )
	 */
	@Override
	public void calculateValidation(MapCreation map) {
		/*
		 * dieu kien de ve hop le cua terminal bao gom + khong trung voi cac
		 * element cua map dang ve + khong phu nen mot so element
		 */
		boolean check = false; // xem co o nao phu nen switch hay khong
		SwitchMap sw = AuxiliaryFunction.findSwitch(position,
				map.getSwitchLayer());
		if ((sw == null) || (sw.getDirection() != null))
			this.setValidation(false);
		else {
			ArrayList<Point> pointCovered = ItemImage.getSquareCovered(image,
					position, side);
			/* kiem tra tron switchList */
			for (Drawable i : map.getSwitchLayer().getListDrawable()) {
				SwitchMap s = (SwitchMap) i;
				if (pointInList(s.getPosition(), pointCovered)) {
					check = true;
					break;
				}

			}
			if (!check)
				/* kiem tra trong terminal */
				for (Drawable i : map.getTerminallayer().getListDrawable()) {
					TerminalMap s = (TerminalMap) i;
					if (pointInList(s.getPosition(), pointCovered)) {
						check = true;
						break;
					}

				}
			if (!check)
				/* kiem tra trong factory */
				for (Drawable i : map.getFactorylayer().getListDrawable()) {
					FactoryMap s = (FactoryMap) i;
					if (pointInList(s.getPosition(), pointCovered)) {
						check = true;
						break;
					}

				}
			if (!check)
				for (Point i : map.getSquareCovedList())
					if (pointInList(i, pointCovered)) {
						check = true;
						break;
					}
			if (check)
				this.setValidation(false);
		}

	}
	/**
	 * kiem tra mot diem trong mot list hay khong.
	 * 
	 * @param p
	 *            diem can kiem tra
	 * @param list
	 *            ca kiem tra
	 * @return true neu diem p nam trong list
	 */
	private boolean pointInList(Point p, ArrayList<Point> list) {
		for (Point i : list)
			if (AuxiliaryFunction.checkPoint(i, p))
				return true;
		return false;
	}

}
