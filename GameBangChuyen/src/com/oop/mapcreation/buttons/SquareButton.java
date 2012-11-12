package com.oop.mapcreation.buttons;

import java.awt.Point;
import java.awt.image.BufferedImage;
// TODO: Auto-generated Javadoc
// TODO: Auto - generated Javadoc

import com.oop.mapcreation.objects.ItemMap;
import com.oop.mapcreation.objects.SquareMap;

/**
 * class n�? l�?class cho button đ�?vẽ ô vuông nền (c�? nước, đất), đây l�?một
 * concrete class khi ấn v�? button n�? người chơi sẽ tạo ra được một đối tượng
 * ô nên đ�?vẽ vòa nền map.
 * 
 * @author mai tien khai
 */
public class SquareButton extends ButtonForDraw {
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
	public SquareButton(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p, normalImage, activeImage, controlCode);

	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameElement.ButtonForDraw#generateItem(java.awt.Point, int)
	 */
	/* (non-Javadoc)
	 * @see com.btl.GameElements.mapstate.ButtonForDraw#generateItem(java.awt.Point, int)
	 */
	@Override
	public ItemMap generateItem(Point position, int side) {
		SquareMap square = new SquareMap(position, side,
				selectedItem.getImage());
		return square;
	}

}
