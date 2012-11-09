package com.btl.GameElements.mapstate;

import java.awt.Point;
import java.awt.image.BufferedImage;
// TODO: Auto - generated Javadoc

/**
 * class này là class cho button để vẽ terminal, đây là một concrete class khi
 * ấn vào button này người chơi sẽ tạo ra được một đối tượng terminal để vẽ.
 * 
 * @author mai tien khai
 */
public class TerminalButton extends ButtonForDraw {

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
	public TerminalButton(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p, normalImage, activeImage, controlCode);
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.ButtonForDraw#generateItem(java.awt.Point, int)
	 */
	@Override
	public ItemMap generateItem(Point position, int side) {
		TerminalIcon terminal = new TerminalIcon(position, side,
				selectedItem.getImage());
		return terminal;
	}

}
