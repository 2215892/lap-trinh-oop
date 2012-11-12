package com.oop.gamepanel;

import java.awt.Point;
import java.util.ArrayList;

import com.oop.data.ItemImage;
import com.oop.mapcreation.MapCreation;
import com.oop.mapcreation.objects.FactoryMap;
import com.oop.mapcreation.objects.ItemMap;
import com.oop.mapcreation.objects.SquareMap;
import com.oop.mapcreation.objects.SwitchMap;
import com.oop.mapcreation.objects.TerminalMap;
import com.oop.mapcreation.objects.TreeMap;
import com.oop.model.AuxiliaryFunction;
import com.oop.model.Direction;

// TODO: Auto-generated Javadoc
// TODO: Auto - generated Javadoc

/**
 * class n?�? phục vụ cho việc thực thi thuật toán xóa trên map đang vẽ, duyệt đ�?
 * th?�?
 * 
 * @author mai tien khai
 */
public class MapDeleting {

	/** SwitchLayer của map. */
	private Layer switchLayer;

	/** The factoryLayer của map. */
	private Layer factoryLayer;

	/** The terminal layer của map. */
	private DrawLayer terminalLayer;

	/** The tree layer của map. */
	private Layer treeLayer;

	/** The square layer của map. */
	private Layer squareLayer;

	/** Chiều cao logic. */
	private int height;

	/** Chiều rộng logic. */
	private int width;

	/** Map đang vẽ. */
	private MapCreation map;

	/** The item map layer của map. */
	private DrawLayer itemMapLayer;

	/**
	 * H?�? khởi tạo đối tượng.
	 * 
	 * @param mapCreation
	 *            - map đang vẽ
	 */
	public MapDeleting(MapCreation mapCreation) {
		map = mapCreation;
		this.factoryLayer = map.getFactorylayer();
		this.switchLayer = map.getSwitchLayer();
		this.terminalLayer = mapCreation.getTerminallayer();
		this.itemMapLayer = mapCreation.getItemMapLayer();
		this.width = mapCreation.getSide();
		this.height = mapCreation.getSide();
	}

	/**
	 * xóa ô vuông nền.
	 * 
	 * @param squareMap
	 *            the square map
	 */
	public void deleteSquare(SquareMap squareMap) {
		squareLayer.removeDrawable(squareMap);
		squareLayer.render();
	}

	/**
	 * xóa cây.
	 * 
	 * @param treeMap
	 *            the tree map
	 */
	public void deleteTree(TreeMap treeMap) {

		treeLayer.removeDrawable(treeMap);
		treeLayer.render();
	}

	/**
	 * xử lý khi xoá một switch(thực hiện duyệt đ�?th?�?.
	 * 
	 * @param sw
	 *            the sw
	 */
	public void deleteSwitch(SwitchMap sw) {
		/* xoa di sw nay */
		switchLayer.removeDrawable(sw);
		/* xoa cac switch lien quan */
		traverseAndDelete();
		switchLayer.render();
	}

	/** dãy các biến cần đ�?kiểm tra duyệt. */
	private boolean[] deleteSwitch;

	/**
	 * Duyệt đ�?th?�?v?�?xóa những switch cô lập.
	 */
	private void traverseAndDelete() {

		/* danh dau cho cac switch, ban dau cho gia tri true */
		/* sau khi duyet song true la bi xoa, false la giu lai */
		deleteSwitch = new boolean[switchLayer.getListDrawable().size()];
		for (int i = 0; i < deleteSwitch.length; i++)
			deleteSwitch[i] = true;
		/* qua trinh duyet tu cac factory */
		for (Drawable i : factoryLayer.getListDrawable()) {
			traverseFactory((FactoryMap) i);
		}
		/* danh sach cac switch can xoa */
		ArrayList<Point> deletedList = new ArrayList<Point>();

		for (int i = 0; i < deleteSwitch.length; i++)
			if (deleteSwitch[i] == true) {
				/* neu dc xoa thi cho toa do luu tru vao deletedList */
				SwitchMap temp = (SwitchMap) (switchLayer.getListDrawable()
						.get(i));
				deletedList.add(new Point(temp.getPosition()));
			}

		// xoa cac switch thua ra khoi
		for (Point i : deletedList) {
			deleteElementSwitch(i);
		}
		switchLayer.render();

		updateFactory();
		updateTerminal();
	}

	/**
	 * Xóa các switch trong list.
	 * 
	 * @param p
	 *            : tọa đ�?switch cần xóa
	 */
	private void deleteElementSwitch(Point p) {
		for (Drawable sw : switchLayer.getListDrawable()) {

			if ((((SwitchMap) sw).getPosition().x == p.x)
					&& (((SwitchMap) sw).getPosition().y == p.y)) {
				switchLayer.removeDrawable(sw);
				break;
			}
		}
	}

	/**
	 * duyệt đ�?thì từ một nh?�?máy.
	 * 
	 * @param f
	 *            - factory bắt đầu duyệt
	 */
	private void traverseFactory(FactoryMap f) {
		SwitchMap temp = new SwitchMap(f.getPosition(), width, height);
		/* neu factory co huog tuc la da co switch noi vao no thi moi duyet */
		SwitchMap tgUp = AuxiliaryFunction.upNeighbor(temp, this.switchLayer);
		SwitchMap tgDown = AuxiliaryFunction.downNeighbor(temp,
				this.switchLayer);
		SwitchMap tgLeft = AuxiliaryFunction.leftNeighbor(temp,
				this.switchLayer);
		SwitchMap tgRight = AuxiliaryFunction.rightNeighbor(temp,
				this.switchLayer);
		if (f.getDirection() != null) {
			// tim theo huong UP
			if ((f.getDirection() == Direction.UP) && (tgUp != null)
					&& (deleteSwitch[getIndex(tgUp)] == true))
				traverseSwitch(tgUp);
			else if ((f.getDirection() == Direction.DOWN) && (tgDown != null)
					&& (deleteSwitch[getIndex(tgDown)] == true)) {
				traverseSwitch(AuxiliaryFunction.downNeighbor(temp,
						this.switchLayer));

			} else if ((f.getDirection() == Direction.LEFT) && (tgLeft != null)
					&& (deleteSwitch[getIndex(tgLeft)] == true))
				traverseSwitch(tgLeft);
			else if ((f.getDirection() == Direction.RIGHT) && (tgRight != null)
					&& (deleteSwitch[getIndex(tgRight)] == true))
				traverseSwitch(tgRight);
		}

	}

	/**
	 * ta cần update lại danh sách các factory sau khi xóa các switch.
	 */
	private void updateFactory() {
		for (Drawable i : factoryLayer.getListDrawable()) {
			FactoryMap temp = (FactoryMap) i;
			SwitchMap sw = new SwitchMap(temp.getPosition(), width, height);

			if (temp.getDirection() != null) {
				if ((temp.getDirection() == Direction.UP)
						&& (AuxiliaryFunction.upNeighbor(sw, this.switchLayer) == null))
					temp.setDirection(null);
				else if ((temp.getDirection() == Direction.DOWN)
						&& (AuxiliaryFunction
								.downNeighbor(sw, this.switchLayer) == null))
					temp.setDirection(null);
				else if ((temp.getDirection() == Direction.RIGHT)
						&& (AuxiliaryFunction.rightNeighbor(sw,
								this.switchLayer) == null))
					temp.setDirection(null);
				else if ((temp.getDirection() == Direction.LEFT)
						&& (AuxiliaryFunction
								.leftNeighbor(sw, this.switchLayer) == null))
					temp.setDirection(null);
			}
		}
	}

	/**
	 * update lại các terminal sau khi xóa các switch.
	 */
	private void updateTerminal() {

		ArrayList<Point> deletedList = new ArrayList<Point>();
		for (Drawable i : terminalLayer.getListDrawable())
			if (removeTerminal((TerminalMap) i))
				deletedList.add(((TerminalMap) i).getPosition());
		for (Point i : deletedList) {
			deleteElementT(i);
		}

		terminalLayer.render();
		// update();
	}

	/**
	 * kiểm tra xem có phải xóa một terminal hay không.
	 * 
	 * @param t
	 *            the t
	 * @return true nếu phải xóa
	 */
	private boolean removeTerminal(TerminalMap t) {
		SwitchMap sw = AuxiliaryFunction.findSwitch(new Point(t.getPosition().x
				- width, t.getPosition().y), this.switchLayer);
		if ((sw != null) && (sw.containsDirection(Direction.DOWN))) {
			return false;
		}
		sw = AuxiliaryFunction.findSwitch(new Point(t.getPosition().x + width,
				t.getPosition().y), this.switchLayer);
		if ((sw != null) && (sw.containsDirection(Direction.UP))) {
			return false;
		}

		sw = AuxiliaryFunction.findSwitch(
				new Point(t.getPosition().x, t.getPosition().y + height),
				this.switchLayer);
		if ((sw != null) && (sw.containsDirection(Direction.LEFT))) {
			return false;
		}

		sw = AuxiliaryFunction.findSwitch(
				new Point(t.getPosition().x, t.getPosition().y - height),
				this.switchLayer);
		if ((sw != null) && (sw.containsDirection(Direction.RIGHT))) {
			return false;
		}
		return true;

	}

	/**
	 * xóa một terminal.
	 * 
	 * @param p
	 *            - v?�?trí cần xóa
	 */
	private void deleteElementT(Point p) {
		/* xoa trong terminalLayer */
		for (Drawable t : terminalLayer.getListDrawable()) {
			TerminalMap temp = (TerminalMap) t;
			if ((temp.getPosition().x == p.x) && (temp.getPosition().y == p.y)) {
				terminalLayer.removeDrawable(t);
				break;
			}
		}
		/* xóa trong terminalMapImagae */
		ItemMap temp = AuxiliaryFunction.findItem(p, itemMapLayer);
		if (temp != null)
			itemMapLayer.removeDrawable(temp);
	}

	/**
	 * H?�? tính ch?�?s?�?của switch trong swichLayer.
	 * 
	 * @param sw
	 *            - switch cần lấy ch?�?s?�?
	 * @return ch?�?s?�?của switch trong list nếu tìm thấy, không thấy trả v?�?- 1
	 */
	private int getIndex(SwitchMap sw) {
		for (int i = 0; i < switchLayer.getListDrawable().size(); i++) {
			SwitchMap temp = (SwitchMap) switchLayer.getListDrawable().get(i);
			if ((temp.getPosition().x == sw.getPosition().x)
					&& (temp.getPosition().y == sw.getPosition().y))
				return i;
		}
		return -1;
	}

	/**
	 * Duyệt đ�?th?�?từ switch.
	 * 
	 * @param sw
	 *            - switch bắt đầu
	 */
	private void traverseSwitch(SwitchMap sw) {
		// duyet cai switch nay

		deleteSwitch[AuxiliaryFunction.getIndex(sw, this.switchLayer)] = false;

		if (sw.getListDirection().size() > 0) {
			for (Direction j : sw.getListDirection()) {
				// tim theo huong UP

				SwitchMap tgUp = AuxiliaryFunction.upNeighbor(sw,
						this.switchLayer);
				SwitchMap tgDown = AuxiliaryFunction.downNeighbor(sw,
						this.switchLayer);
				SwitchMap tgLeft = AuxiliaryFunction.leftNeighbor(sw,
						this.switchLayer);
				SwitchMap tgRight = AuxiliaryFunction.rightNeighbor(sw,
						this.switchLayer);

				if ((j == Direction.UP)
						&& (tgUp != null)
						&& (deleteSwitch[AuxiliaryFunction.getIndex(tgUp,
								this.switchLayer)]))
					traverseSwitch(tgUp);
				else if ((j == Direction.DOWN)
						&& (tgDown != null)
						&& (deleteSwitch[AuxiliaryFunction.getIndex(tgDown,
								this.switchLayer)]))
					traverseSwitch(tgDown);
				else if ((j == Direction.LEFT)
						&& (tgLeft != null)
						&& (deleteSwitch[AuxiliaryFunction.getIndex(tgLeft,
								this.switchLayer)]))
					traverseSwitch(tgLeft);
				else if ((j == Direction.RIGHT)
						&& (tgRight != null)
						&& (deleteSwitch[AuxiliaryFunction.getIndex(tgRight,
								this.switchLayer)]))
					traverseSwitch(tgRight);
			}

		}
	}

	/**
	 * xử lý xóa terminal đã chọn.
	 * 
	 * @param t
	 *            - terminal cân xóa
	 */

	public void deleteTerminal(TerminalMap t) {

		/* remove t di */
		terminalLayer.removeDrawable(t);
		terminalLayer.render();
		/* remove bieu tuong cua t di */

		ItemMap ter = AuxiliaryFunction.findItem(t.getPosition(), itemMapLayer);
		if (ter != null) {
			ArrayList<Point> list = ItemImage.getSquareCovered(ter.getImage(),
					ter.getEntryPoint(), map.getSide());
			for (Point i : list)
				AuxiliaryFunction.removePointFromList(map.getSquareCovedList(),
						i);
			itemMapLayer.removeDrawable(ter);
		}

	}

	/**
	 * Xóa nh?�?máy chọn.
	 * 
	 * @param f
	 *            - factory cần xóa
	 */
	public void deleteFactory(FactoryMap f) {
		/* xoa nha may vua chon */
		factoryLayer.removeDrawable(f);
		ItemMap ter = AuxiliaryFunction.findItem(f.getPosition(), itemMapLayer);
		itemMapLayer.removeDrawable(ter);
		factoryLayer.render();
		/* cap nhat lai cac diem do factory va terminal phu */
		// FactoryIcon fc = (FactoryIcon) ter;
		ArrayList<Point> list = ItemImage.getSquareCovered(ter.getImage(),
				ter.getEntryPoint(), map.getSide());
		for (Point i : list)
			AuxiliaryFunction.removePointFromList(map.getSquareCovedList(), i);

		traverseAndDelete();
		factoryLayer.render();
	}

	/**
	 * xóa itemMap.
	 * 
	 * @param item
	 *            - item cần xóa
	 */
	public void deleteItemMap(ItemMap item) {
		itemMapLayer.removeDrawable(item);
	}
}
