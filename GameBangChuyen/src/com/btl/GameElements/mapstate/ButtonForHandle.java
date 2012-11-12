package com.btl.GameElements.mapstate;

import java.awt.Point;
import java.awt.image.BufferedImage;
// TODO: Auto-generated Javadoc
// TODO: Auto - generated Javadoc
/**
 * class này là các class cho các button điều khiển, ví dụ xóa, quay lai,
 * save...
 * 
 * @author mai tien khai
 * 
 */
public class ButtonForHandle extends DrawingButton {

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
	public ButtonForHandle(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p, normalImage, activeImage, controlCode);
	}

}
