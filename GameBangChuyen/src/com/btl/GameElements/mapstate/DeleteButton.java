package com.btl.GameElements.mapstate;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.btl.Model.AuxiliaryFunction;

// TODO: Auto-generated Javadoc
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

	/* (non-Javadoc)
	 * @see com.btl.GameElements.mapstate.ButtonForHandle#handle(com.btl.GameElements.mapstate.MapCreation)
	 */
	@Override
	public void handle(MapCreation map) {
		AuxiliaryFunction.handleMenuDelete(map);
	}

}
