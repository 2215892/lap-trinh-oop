package com.oop.mapcreation.buttons;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.mapcreation.MapCreation;
import com.oop.model.AuxiliaryFunction;

// TODO: Auto-generated Javadoc
/**
 * The Class DeleteButton.
 */
public class DeleteButton extends ButtonForHandle {
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
