package com.oop.mapcreation.buttons;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.gamepanel.Drawable;
import com.oop.gamepanel.Layer;
import com.oop.mapcreation.MenuItem;
import com.oop.mapcreation.objects.ItemMap;

/**
 * class này mục đich tạo ra các đối tượng button phục vụ cho việc vẽ ra màn
 * hình ví dụ như ấn vào button này để hiện ra các cây rồi chọn câu để vẽ..
 * 
 * @author mai tien khai
 * 
 */
public abstract class ButtonForDraw extends DrawingButton {

	/** Hiển thị của cac item(ví dụ cây, xe tải ...) đi kèm */
	protected Layer itemLayer;

	/** layer để add đối tượng cần vẽ vào layer. */
	protected Layer drawingLayer;

	/** kiểm tra xem trạng thái của các menu ẩn hay hiện. */
	protected boolean itemMenuHidden;

	/** Item được chọn. */
	protected MenuItem selectedItem;

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
	public ButtonForDraw(Point p, BufferedImage normalImage,
			BufferedImage activeImage, int controlCode) {
		super(p, normalImage, activeImage, controlCode);
		selectedItem = null;
		itemLayer = null;
		itemMenuHidden = true;

	}

	/**
	 * Phương thức abstract sinh ra một item tương ứng để vẽ vào map.
	 * 
	 * @param position
	 *            - vi tri cuat item cần sinh ra
	 * @param side
	 *            - cạnh lưới ô vuông của map đang vẽ
	 * @return một item map để vẽ
	 */
	public abstract ItemMap generateItem(Point position, int side);

	/**
	 * Hiển thị menu của Button ra để người dùng chọn.
	 */
	public void showmenu() {
		if (itemLayer != null) {
			for (Drawable i : itemLayer.getListDrawable()) {
				MenuItem temp = (MenuItem) i;
				temp.show();
			}
			itemLayer.render();
		}
		itemMenuHidden = false;

	}

	/**
	 * Ẩn menu đi sau khi người dùng đã chọn xong menu.
	 */
	public void hideMenu() {
		if (itemLayer != null) {
			for (Drawable i : itemLayer.getListDrawable()) {
				MenuItem temp = (MenuItem) i;
				temp.hide();
			}
			itemLayer.render();
		}
		itemMenuHidden = true;
	}

	/**
	 * Gets the selected item.
	 * 
	 * @return the selected item
	 */
	public MenuItem getSelectedItem() {
		return selectedItem;
	}

	/**
	 * Gets the item menu state.
	 * 
	 * @return the item menu state
	 */
	public boolean getItemMenuState() {
		return itemMenuHidden;
	}

	/**
	 * Sets the drawing layer.
	 * 
	 * @param drawingLayer
	 *            the new drawing layer
	 */
	public void setDrawingLayer(Layer drawingLayer) {
		this.drawingLayer = drawingLayer;
	}

	/**
	 * Gets the drawing layer.
	 * 
	 * @return the drawing layer
	 */
	public Layer getDrawingLayer() {
		return this.drawingLayer;
	}

	/**
	 * Sets the item list.
	 * 
	 * @param itemList
	 *            the new item list
	 */
	public void setItemList(Layer itemList) {
		itemLayer = itemList;
	}

	/**
	 * Gets the item layer.
	 * 
	 * @return the item layer
	 */
	public Layer getItemLayer() {
		return itemLayer;
	}
	/**
	 * Select item.
	 * 
	 * @param selectedItem
	 *            the selected item
	 */
	public void selectItem(MenuItem selectedItem) {
		this.selectedItem = selectedItem;
	}

}
