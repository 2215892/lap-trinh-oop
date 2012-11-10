package com.btl.Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.btl.GameElements.mapstate.FactoryMap;
import com.btl.GameElements.mapstate.ItemMap;
import com.btl.GameElements.mapstate.MapCreation;
import com.btl.GameElements.mapstate.SquareMap;
import com.btl.GameElements.mapstate.SwitchMap;
import com.btl.GameElements.mapstate.TerminalIcon;
import com.btl.GameElements.mapstate.TerminalMap;
import com.btl.GameElements.mapstate.TreeMap;
import com.btl.GameElements.playstate.DrawLayer;
import com.btl.GameEngine.Drawable;
import com.btl.GameEngine.Layer;
import com.btl.data.ItemImage;
// TODO: Auto - generated Javadoc

/**
 * class này cung cấp các hàm tĩnh phục vụ cho việc vẽ Map.
 * 
 * @author mai tien khai
 */
public class AuxiliaryFunction {

	/**
	 * Tìm SwitchMap trong một Layer.
	 * 
	 * @param position
	 *            - vị trí cần tìm
	 * @param switchLayer
	 *            - layer chứa switch cần tìm
	 * @return switchMap với vị trí position
	 */
	public static SwitchMap findSwitch(Point position, Layer switchLayer) {

		ArrayList<Drawable> switchList = switchLayer.getListDrawable();
		for (Drawable i : switchList) {
			SwitchMap temp = (SwitchMap) i;
			if (checkPoint(position, temp.getPosition()))
				return temp;
		}
		return null;
	}

	/**
	 * Tìm cac FactoryMap trong một Layer.
	 * 
	 * @param position
	 *            - vị trí cần tìm
	 * @param factoryLayer
	 *            - layer chứa factory cần tìm
	 * @return factoryMap với vị trí position
	 */
	public static FactoryMap findFactory(Point position, Layer factoryLayer) {
		ArrayList<Drawable> factoryList = factoryLayer.getListDrawable();

		for (Drawable i : factoryList) {
			FactoryMap temp = (FactoryMap) i;
			if (temp.getPosition().equals(position))
				return temp;

		}
		return null;
	}

	/**
	 * Tìm cac TerminalMap trong một Layer.
	 * 
	 * @param position
	 *            - vị trí cần tìm
	 * @param terminalLayer
	 *            - layer chứa terminal cần tìm
	 * @return TerminalMap với vị trí position
	 */
	public static TerminalMap findTerminal(Point position, Layer terminalLayer) {
		ArrayList<Drawable> terminalList = terminalLayer.getListDrawable();
		for (Drawable i : terminalList) {
			TerminalMap temp = (TerminalMap) i;
			if (checkPoint(position, temp.getPosition()))
				return temp;
		}
		return null;
	}

	/**
	 * Tìm neighbor phải của một switch.
	 * 
	 * @param sw
	 *            - switch cần tìm neighbor
	 * @param switchLayer
	 *            - layer chứa switch
	 * @return switch cần tìm
	 */
	public static SwitchMap rightNeighbor(SwitchMap sw, Layer switchLayer) {
		return findSwitch(
				new Point(sw.getPosition().x, sw.getPosition().y
						+ sw.getHeight()), switchLayer);
	}

	/**
	 * Kiểm tra xem hai điểm có trùng nhau hay không.
	 * 
	 * @param p1
	 *            - điểm thứ nhất
	 * @param p2
	 *            - điểm thứ hai
	 * @return true nếu hai điểm cùng tọa độ
	 */
	public static boolean checkPoint(Point p1, Point p2) {
		if ((p1.x == p2.x) && (p1.y == p2.y))
			return true;
		return false;
	}

	/**
	 * Tìm neighbor trái của một switch.
	 * 
	 * @param sw
	 *            - switch cần tìm neighbor
	 * @param switchLayer
	 *            - layer chứa switch
	 * @return switch cần tìm
	 */
	public static SwitchMap leftNeighbor(SwitchMap sw, Layer switchLayer) {
		return findSwitch(
				new Point(sw.getPosition().x, sw.getPosition().y
						- sw.getHeight()), switchLayer);
	}

	/**
	 * Tìm neighbor trên của một switch.
	 * 
	 * @param sw
	 *            - switch cần tìm neighbor
	 * @param switchLayer
	 *            - layer chứa switch
	 * @return switch cần tìm
	 */
	public static SwitchMap upNeighbor(SwitchMap sw, Layer switchLayer) {
		return findSwitch(
				new Point(sw.getPosition().x - sw.getWidth(),
						sw.getPosition().y), switchLayer);
	}

	/**
	 * Tìm neighbor dưới của một switch.
	 * 
	 * @param sw
	 *            - switch cần tìm neighbor
	 * @param switchLayer
	 *            - layer chứa switch
	 * @return switch cần tìm
	 */

	public static SwitchMap downNeighbor(SwitchMap sw, Layer switchLayer) {
		return findSwitch(
				new Point(sw.getPosition().x + sw.getWidth(),
						sw.getPosition().y), switchLayer);
	}

	/**
	 * Hàm tính chỉ số của switch trong một layer.
	 * 
	 * @param sw
	 *            - switch cần tìm chỉ số
	 * @param switchLayer
	 *            - layer cần tìm
	 * @return chỉ số cần tìm, nếu không có switch trả về - 1
	 */
	public static int getIndex(SwitchMap sw, Layer switchLayer) {
		for (int i = 0; i < switchLayer.getListDrawable().size(); i++) {
			SwitchMap temp = (SwitchMap) switchLayer.getListDrawable().get(i);
			if ((temp.getPosition().x == sw.getPosition().x)
					&& (temp.getPosition().y == sw.getPosition().y))
				return i;
		}
		return -1;
	}
	/**
	 * Hàm tạo các SwitchMap từ các ModelSwitch(dùng trong đọc file map).
	 * 
	 * @param switchList
	 *            - list ModelSwitch
	 * @param unit
	 *            - cạnh lưới ô vuông
	 * @return Một list các SwitchMap từ ModelSwitch đầu vào
	 */
	public static ArrayList<SwitchMap> loadSwitch(
			ArrayList<ModelSwitch> switchList, int unit) {
		ArrayList<SwitchMap> result = new ArrayList<SwitchMap>();
		for (ModelSwitch i : switchList) {
			Point tg = new Point(i.getPosition().x * unit, i.getPosition().y
					* unit);
			SwitchMap temp = new SwitchMap(tg, unit, unit);
			for (Direction d : i.getListDirection())
				temp.addDirection(d);
			temp.setCurrentDir(i.getCurrentDir());
			result.add(temp);
		}
		return result;
	}

	/**
	 * Hàm tạo các FactoryMap từ các ModelFactory(dùng trong đọc file map).
	 * 
	 * @param factoryList
	 *            - list các ModelFactory
	 * @param unit
	 *            - cạnh lưới ô vuông
	 * @return Một list các FactoryMap từ ModelFactory đầu vào
	 */
	public static ArrayList<FactoryMap> loadFactory(
			ArrayList<ModelFactory> factoryList, int unit) {
		ArrayList<FactoryMap> result = new ArrayList<FactoryMap>();
		for (ModelFactory i : factoryList) {
			Point tg = new Point(i.getPosition().x * unit, i.getPosition().y
					* unit);
			FactoryMap temp = new FactoryMap(tg, unit, unit);
			temp.setDirection(i.getDirection());
			result.add(temp);
		}
		return result;
	}

	/**
	 * Hàm tạo các TerminalMap từ các ModelTerminal (dùng trong đọc file map).
	 * 
	 * @param terminalList
	 *            list các ModelTerminal
	 * @param unit
	 *            - cạnh lưới ô vuông
	 * @return Một list các SwitchMap từ ModelTerminal đầu vào
	 */
	public static ArrayList<TerminalMap> loadTerminal(
			ArrayList<ModelTerminal> terminalList, int unit) {
		ArrayList<TerminalMap> result = new ArrayList<TerminalMap>();
		for (ModelTerminal i : terminalList) {
			Point tg = new Point(i.getPosition().x * unit, i.getPosition().y
					* unit);
			TerminalMap temp = new TerminalMap(tg, unit);
			temp.setBoxBumber(i.getBoxCount());
			result.add(temp);
		}
		return result;
	}

	/**
	 * Kiểm tra xem hai hướng có ngược chiều hay không.
	 * 
	 * @param d1
	 *            - hướng thứ nhất
	 * @param d2
	 *            - hướng thứ hai
	 * @return true nếu hai hướng ngược chiều, false còn lại
	 */

	public static boolean checkAntiDirection(Direction d1, Direction d2) {

		if ((d1 == Direction.UP) && (d2 == Direction.DOWN))
			return true;
		else if ((d2 == Direction.UP) && (d1 == Direction.DOWN))
			return true;
		else if ((d1 == Direction.LEFT) && (d2 == Direction.RIGHT))
			return true;
		else if ((d2 == Direction.LEFT) && (d1 == Direction.RIGHT))
			return true;
		else
			return false;
	}

	/**
	 * Đếm số các đối tượng bên cạnh switch(chỉ tính terminalMap, FactoryMap,
	 * SwitchMap).
	 * 
	 * @param sw
	 *            - switch cần đếm
	 * @param map
	 *            - map đang vẽ
	 * @return số neighbor
	 */
	public static int countNeighbor(SwitchMap sw, MapCreation map) {
		Layer switchLayer = map.getSwitchLayer();
		Layer factoryLayer = map.getFactorylayer();
		Layer terminalLayer = map.getTerminallayer();

		int count = 0;
		int height = sw.getHeight();
		int width = sw.getWidth();
		Point up = new Point(sw.getPosition().x - width, sw.getPosition().y);
		Point down = new Point(sw.getPosition().x + width, sw.getPosition().y);
		Point left = new Point(sw.getPosition().x, sw.getPosition().y - height);
		Point right = new Point(sw.getPosition().x, sw.getPosition().y + height);
		if ((findSwitch(right, switchLayer) != null)
				|| (findFactory(right, factoryLayer) != null)
				|| (findTerminal(right, terminalLayer) != null))
			count++;
		if ((findSwitch(left, switchLayer) != null)
				|| (findFactory(left, factoryLayer) != null)
				|| (findTerminal(left, terminalLayer) != null))
			count++;
		if ((findSwitch(up, switchLayer) != null)
				|| (findFactory(up, factoryLayer) != null)
				|| (findTerminal(up, terminalLayer) != null))
			count++;
		if ((findSwitch(down, switchLayer) != null)
				|| (findFactory(down, factoryLayer) != null)
				|| (findTerminal(down, terminalLayer) != null))
			count++;
		return count;
	}

	/**
	 * Kiểm tra một ô đã có factory, terminal hay switch hay không.
	 * 
	 * @param p
	 *            - vị trí cần xét
	 * @param switchLayer
	 *            - layer chứa switch
	 * @param factoryLayer
	 *            - layer chứa factory
	 * @param terminalLayer
	 *            - layer chứa terminal
	 * @return true nếu chưa có các đối tượng này
	 */
	public static boolean isEmptySquare(Point p, Layer switchLayer,
			Layer factoryLayer, Layer terminalLayer) {

		if ((findSwitch(p, switchLayer) != null)
				|| (findFactory(p, factoryLayer) != null)
				|| (findTerminal(p, terminalLayer) != null))
			return false;
		return true;
	}

	/**
	 * Tính chiều cáo của ảnh khi biết chiều rộng mà đảm bảo đúng tỉ lệ.
	 * 
	 * @param width
	 *            - chiều rộng của ảnh
	 * @param img
	 *            - ảnh cần tính
	 * @return chiều cao ứng với chiều rộng
	 */
	public static int getImageHeight(int width, Image img) {

		Double h = (double) width * img.getHeight(null) / img.getWidth(null);
		return h.intValue();
	}

	/**
	 * Tính vertical flip của một Image.
	 * 
	 * @param img
	 *            - ảnh cần flip
	 * @return vertivalflip của ảnh
	 */
	public static Image verticalFlipImage(Image img) {
		int h = img.getHeight(null);
		int w = img.getWidth(null);
		BufferedImage result = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) result.getGraphics();
		g.setBackground(new Color(255, 255, 255, 0));
		g.clearRect(0, 0, w, h);
		g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
		g.dispose();

		return result;

	}

	/**
	 * Tính horizontoal flip của một Image.
	 * 
	 * @param img
	 *            - ảnh cần flip
	 * @return horizontal flip của ảnh
	 */
	public static BufferedImage horizontalFlipImage(Image img) {
		int h = img.getHeight(null);
		int w = img.getWidth(null);
		BufferedImage result = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) result.getGraphics();
		g.setBackground(new Color(255, 255, 255, 0));
		g.clearRect(0, 0, w, h);
		g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
		g.dispose();
		return result;

	}

	/**
	 * Kiểm tra xem vị trí có thuộc layer nào không (trong một map).
	 * 
	 * @param mapCreation
	 *            - map đang vẽ
	 * @param position
	 *            - vị trí cần kiểm tra
	 * @return true nếu không thấy vị trí này
	 */
	public static boolean checkIdenticalPostition(MapCreation mapCreation,
			Point position) {
		Layer drawingLayer = mapCreation.getItemMapLayer();
		if ((findFactory(position, mapCreation.getFactorylayer()) == null)
				&& (findTerminal(position, mapCreation.getTerminallayer()) == null)
				&& (findItem(position, drawingLayer) == null)
				&& (findSwitch(position, mapCreation.getSwitchLayer()) == null))
			return true;
		return false;
	}

	/**
	 * kiem tra xem xem một switch có neighbor nào hay không.
	 * 
	 * @param sw
	 *            - switch cần kiểm tra
	 * @param switchLayer
	 *            - layer chứa switch
	 * @param direction
	 *            - hướng tìm neighbor
	 * @return true nếu có neighbor
	 */
	public static boolean neighborOfSwitch(SwitchMap sw, Layer switchLayer,
			Direction direction) {
		ArrayList<SwitchMap> result = new ArrayList<SwitchMap>();
		SwitchMap leftNeighbor = leftNeighbor(sw, switchLayer);
		SwitchMap rightNeighbor = rightNeighbor(sw, switchLayer);
		SwitchMap upNeighbor = upNeighbor(sw, switchLayer);
		SwitchMap downNeighbor = downNeighbor(sw, switchLayer);
		if (leftNeighbor != null)
			result.add(leftNeighbor);
		if (rightNeighbor != null)
			result.add(rightNeighbor);
		if (upNeighbor != null)
			result.add(upNeighbor);
		if (downNeighbor != null)
			result.add(downNeighbor);
		return true;
	}

	/**
	 * Hàm chuyển đổi giữa entryPoint(trong ItemMap) và nearestPoint (trong
	 * ItemMap)
	 * 
	 * @param nearestPoint
	 *            the nearest point
	 * @param image
	 *            ảnh xem xét
	 * @param side
	 *            - cạnh lưới ô vuông
	 * @return diem - entryPoint tương ứng
	 */
	public static Point nearestPointToEntryPoint(Point nearestPoint,
			Image image, int side) {
		Point temp = null;
		if (image == ItemImage.TRUCK)
			temp = new Point(nearestPoint.x, nearestPoint.y + 4 * side);
		else if (image == ItemImage.TRUCK_DOWN)
			temp = new Point(nearestPoint.x - 4 * side, nearestPoint.y);
		else if (image == ItemImage.AIRPLANE)
			temp = new Point(nearestPoint.x + side, nearestPoint.y);
		else if (image == ItemImage.AIRPLANE_RIGHT)
			temp = new Point(nearestPoint.x, nearestPoint.y - side);
		else if (image == ItemImage.SHIP)
			temp = new Point(nearestPoint.x + side, nearestPoint.y);
		else if (image == ItemImage.SHIP_RIGHT)
			temp = new Point(nearestPoint.x, nearestPoint.y - side);
		return temp;
	}

	/**
	 * load itemlist (đọc từ file map) vào drawingLayer.
	 * 
	 * @param itemMapLayer
	 *            - layer load vào
	 * @param itemList
	 *            - các item đọc được từ file
	 * @param side
	 *            - cạnh lưới ô vuông
	 */
	public static void loadDrawingLayer(DrawLayer itemMapLayer,
			ArrayList<ModelItem> itemList, int side) {
		/* duyet va sap xep cac itemList vao drawLayer */

		for (ModelItem i : itemList) {
			BufferedImage image = ItemImage
					.getItemImage(i.getId(), i.getType());
			Point position = new Point(i.getPosition().x * side,
					i.getPosition().y * side);
			ItemMap item = null;
			if (i.getType() == ItemImage.VEHICLE_TYPE) {
				Point temp = nearestPointToEntryPoint(position, image, side);
				item = new TerminalIcon(temp, side, image);
			} else if (i.getType() == ItemImage.PLATFORM_TYPE)
				item = new SquareMap(position, side, image);
			else if (i.getType() == ItemImage.TREE_TYPE)
				item = new TreeMap(position, side, image);
			itemMapLayer.addDrawable(item);
		}
	}

	/**
	 * Tìm những switch mà sẽ làm cho box chạy đến vô hạn.
	 * 
	 * @param map
	 *            - map cần load
	 * @return arrayList của các switch bị lỗi
	 */
	public static ArrayList<SwitchMap> falseSwitch(MapCreation map) {
		ArrayList<SwitchMap> result = new ArrayList<SwitchMap>();
		DrawLayer switchLayer = map.getSwitchLayer();
		DrawLayer terminalLayer = map.getTerminallayer();
		int side = map.getSide();
		for (Drawable i : switchLayer.getListDrawable()) {
			SwitchMap sw = (SwitchMap) i;
			/* xet den cac switch co it nhat mot huong */
			if (sw.getListDirection().size() != 0) {
				/* set cac object lan can theo cac huong */
				SwitchMap switchNeighbor = null;
				TerminalMap terminalNeighbor = null;
				Point neighborPosition = null;
				boolean check = true;
				for (Direction j : sw.getListDirection()) {
					if (j == Direction.DOWN) {
						neighborPosition = new Point(sw.getPosition().x + side,
								sw.getPosition().y);
					} else if (j == Direction.UP) {
						neighborPosition = new Point(sw.getPosition().x - side,
								sw.getPosition().y);
					} else if (j == Direction.RIGHT) {
						neighborPosition = new Point(sw.getPosition().x,
								sw.getPosition().y + side);
					} else if (j == Direction.LEFT) {
						neighborPosition = new Point(sw.getPosition().x,
								sw.getPosition().y - side);
					}
					switchNeighbor = findSwitch(neighborPosition, switchLayer);
					terminalNeighbor = findTerminal(neighborPosition,
							terminalLayer);
					if ((terminalNeighbor == null) && (switchNeighbor == null)) {
						check = false;
						break;
					}
				}
				if (!check)
					result.add(sw);
			}
		}

		return result;
	}

	/**
	 * Hiển thị các switch mà khi box đi tới sẽ chạy đến vô hạn.
	 * 
	 * @param map
	 *            - map đang vẽ
	 */
	public static void showWrongSwitch(MapCreation map) {
		for (Drawable i : map.getSwitchLayer().getListDrawable()) {
			SwitchMap sw = (SwitchMap) i;
			sw.setInfinityState(false);
		}
		ArrayList<SwitchMap> result = falseSwitch(map);
		for (SwitchMap i : result) {
			i.setInfinityState(true);
		}
	}

	/**
	 * Tìm các factory không có switch đi ra.
	 * 
	 * @param map
	 *            - map đang vẽ
	 * @return list các Factory cần tìm
	 */
	public static ArrayList<FactoryMap> isolatedFactory(MapCreation map) {
		ArrayList<FactoryMap> result = new ArrayList<FactoryMap>();
		for (Drawable i : map.getFactorylayer().getListDrawable()) {
			FactoryMap f = (FactoryMap) i;
			if (f.getDirection() == null)
				result.add(f);
		}
		return result;
	}

	/**
	 * Hiển thị các Factory không có switch đi ra.
	 * 
	 * @param map
	 *            - map đang vẽ
	 */
	public static void showWrongFactory(MapCreation map) {
		ArrayList<FactoryMap> result = isolatedFactory(map);
		for (Drawable i : map.getFactorylayer().getListDrawable()) {
			FactoryMap f = (FactoryMap) i;
			f.setOutletState(true);
		}
		for (FactoryMap i : result)
			i.setOutletState(false);
	}

	/**
	 * Tìm item trong một layer.
	 * 
	 * @param position
	 *            - vị trí cần tìm
	 * @param itemMapLayer
	 *            - layer chứa item cần tìm
	 * @return item tìm thấy
	 */
	public static ItemMap findItem(Point position, Layer itemMapLayer) {
		for (Drawable i : itemMapLayer.getListDrawable()) {
			ItemMap item = (ItemMap) i;
			if (checkPoint(item.getEntryPoint(), position))
				return item;
		}
		return null;
	}

	/**
	 * Xóa(remove) một điểm trong một list.
	 * 
	 * @param list
	 *            - list chứa các điểm
	 * @param point
	 *            - điểm cần remove
	 */
	public static void removePointFromList(ArrayList<Point> list, Point point) {
		for (Point i : list) {
			if (checkPoint(i, point)) {
				list.remove(i);
				break;
			}

		}
	}

	/**
	 * Lấy một số nhập vào từ messageBox.
	 * 
	 * @param message
	 *            - thông tin hiển thị trong messagebox
	 * @param initialValue
	 *            - giá trị đặt mặ định
	 * @param parent
	 *            - component cho đối tượng cha
	 * @return số đọc được do người dùng nhập vào
	 */
	public static int getInputNumber(String message, int initialValue,
			Component parent) {
		String result = null;
		int number = 0;
		String message1 = "";
		boolean check = true;
		boolean first = true;
		do {
			try {
				if (!first) {
					message1 = "ban nhap sai, vui long nhap lai: ";
				} else
					message1 = message;
				result = JOptionPane.showInputDialog(parent, message1,
						initialValue);
				number = Integer.parseInt(result);
				check = false;
			} catch (Exception e) {
				check = true;
			}
			first = false;

		} while (check);
		return number;
	}

	/**
	 * Hàm kiểm tra xem Map đã được vẽ gì hay chưa
	 * 
	 * @param map
	 *            - map đang vẽ
	 * @return true nếu map chưa được vẽ gì
	 */
	public static boolean isEmpty(MapCreation map) {

		if (map.getFactorylayer().getListDrawable().size() == 0)
			return true;
		return false;
	}
}
