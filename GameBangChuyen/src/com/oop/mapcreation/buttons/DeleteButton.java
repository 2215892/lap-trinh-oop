package com.oop.mapcreation.buttons;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.mapcreation.MapCreation;
import com.oop.model.AuxiliaryFunction;

/**
 * The Class DeleteButton.
 */
public class DeleteButton extends ButtonForHandle {
	/**
	 * Hàm khởi tạo của button.
	 * 
	 * @param p
	 *            - tọa độ đặt Button
	 * @param normalImage
	 *            - ảnh hiển thị của Button ở trạng thái bình thường
	 * @param activeImage
	 *            - ảnh hiển thị của Button ở trạng thái kích hoạt (khi ấn vào)
	 * @param controlCode
	 *            - mã điều khiển của Button
	 */
	public DeleteButton(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p, normalImage, activeImage, controlCode);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oop.mapcreation.buttons.ButtonForHandle#handle(com.oop.mapcreation
	 * .MapCreation)
	 */
	@Override
	public void handle(MapCreation map) {
		AuxiliaryFunction.handleMenuDelete(map);
	}

}
