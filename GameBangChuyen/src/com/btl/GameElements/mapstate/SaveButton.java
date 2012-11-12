package com.btl.GameElements.mapstate;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.btl.Model.AuxiliaryFunction;

public class SaveButton extends ButtonForHandle {
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
	
	public SaveButton(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p, normalImage, activeImage, controlCode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(MapCreation map) {
		AuxiliaryFunction.handleMenuSave(map);
		this.normalRender();
		map.setInitialMenuState();
	}

}
