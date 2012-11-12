package com.oop.mapcreation.buttons;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.mapcreation.MapCreation;
// TODO: Auto-generated Javadoc
// TODO: Auto - generated Javadoc
/**
 * class n�? l�?các class cho các button điều khiển, ví dụ xóa, quay lai,
 * save...
 * 
 * @author mai tien khai
 * 
 */
public abstract class ButtonForHandle extends DrawingButton {

	/**
	 * H�? khởi tạo của button.
	 * 
	 * @param p
	 *            - tọa đ�?đặt Button
	 * @param normalImage
	 *            - ảnh hiển th�?của Button �?trạng thái bình thường
	 * @param activeImage
	 *            - ảnh hiển th�?của Button �?trạng thái kích hoạt (khi ấn v�?)
	 * @param controlCode
	 *            - mã điều khiển của Button
	 */
	public ButtonForHandle(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p, normalImage, activeImage, controlCode);
	}
	/**
	 * Chức năng của Button
	 * @param map - map đang vẽ
	 */
	public abstract void handle(MapCreation map);
	

}
