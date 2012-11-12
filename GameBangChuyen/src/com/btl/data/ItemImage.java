package com.btl.data;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.btl.Model.ConversionFunction;
import com.btl.Model.Direction;

/**
 * Lớp ItemImage. Cung cấp hình ảnh về các item
 */
public abstract class ItemImage {

	/** The Constant PLATFORM_TYPE. */
	public static final int PLATFORM_TYPE = 2;

	/** The Constant TREE_TYPE. */
	public static final int TREE_TYPE = 3;

	/** The Constant SHIP_RIGHT_ID. */
	public static final int SHIP_RIGHT_ID = 6;

	/** The Constant SHIP_UP_ID. */
	public static final int SHIP_UP_ID = 5;

	/** The Constant PLANE_RIGHT_ID. */
	public static final int PLANE_RIGHT_ID = 4;

	/** The Constant PLANE_UP_ID. */
	public static final int PLANE_UP_ID = 3;

	/** The Constant TRUCK_DOWN_ID. */
	public static final int TRUCK_DOWN_ID = 2;

	/** The Constant TRUCK_LEFT_ID. */
	public static final int TRUCK_LEFT_ID = 1;

	/** The Constant VEHICLE_TYPE. */
	public static final int VEHICLE_TYPE = 1;

	/** The Constant RES_DIR. */
	public final static String RES_DIR = ConversionFunction
			.getCurrentDirectory() + "res\\item\\";

	/** The Constant AIRPLANE. */
	public final static BufferedImage AIRPLANE = ConversionFunction
			.loadImage(RES_DIR + "AIRPLANE.png");

	/** The Constant AIRPLANE_RIGHT. */
	public static final BufferedImage AIRPLANE_RIGHT = ConversionFunction
			.flipHorizontally(AIRPLANE);

	/** The Constant PLATFORM_1. */
	public final static BufferedImage PLATFORM_1 = ConversionFunction
			.loadImage(RES_DIR + "PLATFORM_1.png");

	/** The Constant PLATFORM_2. */
	public final static BufferedImage PLATFORM_2 = ConversionFunction
			.loadImage(RES_DIR + "PLATFORM_2.png");

	/** The Constant PLATFORM_3. */
	public final static BufferedImage PLATFORM_3 = ConversionFunction
			.loadImage(RES_DIR + "PLATFORM_3.png");

	/** The Constant PLATFORM_4. */
	public final static BufferedImage PLATFORM_4 = ConversionFunction
			.loadImage(RES_DIR + "PLATFORM_4.png");

	/** The Constant SHIP. */
	public final static BufferedImage SHIP = ConversionFunction
			.loadImage(RES_DIR + "SHIP.png");

	/** The Constant SHIP_RIGHT. */
	public static final BufferedImage SHIP_RIGHT = ConversionFunction
			.flipHorizontally(SHIP);

	/** The Constant TREE_1. */
	public final static BufferedImage TREE_1 = ConversionFunction
			.loadImage(RES_DIR + "TREE_1.png");

	/** The Constant TREE_2. */
	public final static BufferedImage TREE_2 = ConversionFunction
			.loadImage(RES_DIR + "TREE_2.png");

	/** The Constant TREE_3. */
	public final static BufferedImage TREE_3 = ConversionFunction
			.loadImage(RES_DIR + "TREE_3.png");

	/** The Constant TREE_4. */
	public final static BufferedImage TREE_4 = ConversionFunction
			.loadImage(RES_DIR + "TREE_4.png");

	/** The Constant TREE_5. */
	public final static BufferedImage TREE_5 = ConversionFunction
			.loadImage(RES_DIR + "TREE_5.png");

	/** The Constant TREE_6. */
	public final static BufferedImage TREE_6 = ConversionFunction
			.loadImage(RES_DIR + "TREE_6.png");

	/** The Constant TRUCK. */
	public final static BufferedImage TRUCK = ConversionFunction
			.loadImage(RES_DIR + "TRUCK.png");

	/** The Constant TRUCK_DOWN. */
	public static final BufferedImage TRUCK_DOWN = ConversionFunction
			.flipHorizontally(TRUCK);

	/**
	 * Lấy ảnh tương ứng id và type.
	 * 
	 * @param id
	 *            id của item
	 * @param type
	 *            giá trị type của item
	 * @return ảnh của item tương ứng
	 */
	public static BufferedImage getItemImage(int id, int type) {
		switch (type) {
			case VEHICLE_TYPE : /* TRUCK, AIRPLANE, SHIP */
				switch (id) {
					case TRUCK_LEFT_ID : /* TRUCK_LEFT */
						return TRUCK;
					case TRUCK_DOWN_ID : /* TRUCK DOWN */
						return TRUCK_DOWN;
					case PLANE_UP_ID : /* PLANE_UP */
						return AIRPLANE;
					case PLANE_RIGHT_ID :/* PLANE_RIGHT */
						return AIRPLANE_RIGHT;
					case SHIP_UP_ID :/* SHIP_UP */
						return SHIP;
					case SHIP_RIGHT_ID :/* SHIP_RIGHT */
						return SHIP_RIGHT;
				}
				break;
			case TREE_TYPE : /* TREE */
				switch (id) {
					case 1 :
						return TREE_1;
					case 2 :
						return TREE_2;
					case 3 :
						return TREE_3;
					case 4 :
						return TREE_4;
					case 5 :
						return TREE_5;
					case 6 :
						return TREE_6;
				}
				break;
			case PLATFORM_TYPE : /* PLATFORM */
				switch (id) {
					case 1 :
						return PLATFORM_1;
					case 2 :
						return PLATFORM_2;
					case 3 :
						return PLATFORM_3;
					case 4 :
						return PLATFORM_4;
				}
				break;
		}

		return null;
	}

	/**
	 * Lấy khoảng đệm để vẽ item.
	 * 
	 * @param id
	 *            id của item
	 * @param type
	 *            type của item
	 * @return Point(x, y) với x, y tương ứng là đệm trục x, y
	 */
	public static Point getItemPad(int id, int type) {
		switch (type) {
			case VEHICLE_TYPE : /* TRUCK, AIRPLANE, SHIP */
				switch (id) {
					case TRUCK_LEFT_ID : /* TRUCK_LEFT */
						return new Point(6, 50);
					case TRUCK_DOWN_ID : /* TRUCK DOWN */
						return new Point(36, 50);
					case PLANE_UP_ID : /* PLANE_UP */
						return new Point(32, 38);
					case PLANE_RIGHT_ID :/* PLANE_RIGHT */
						return new Point(6, 38);
					case SHIP_UP_ID :/* SHIP_UP */
						return new Point(36, 77);
					case SHIP_RIGHT_ID :/* SHIP_RIGHT */
						return new Point(2, 77);
				}
				break;
			case TREE_TYPE : /* TREE */
				return new Point(1, 48);
			case PLATFORM_TYPE : /* PLATFORM */
				return new Point(1, 9);
		}

		return null;

	}

	/**
	 * Xác định hướng cho các ảnh, dùng cho các ảnh thuộc họ terminal, factory.
	 * 
	 * @param image
	 *            ảnh cần xác định hướng
	 * @return hướng của ảnh
	 */
	public static Direction getDirection(BufferedImage image) {
		if (image == TRUCK)
			return Direction.LEFT;
		else if (image == TRUCK_DOWN)
			return Direction.DOWN;
		else if (image == AIRPLANE)
			return Direction.UP;
		else if (image == AIRPLANE_RIGHT)
			return Direction.RIGHT;
		else if (image == SHIP)
			return Direction.UP;
		else if (image == SHIP_RIGHT)
			return Direction.RIGHT;

		return null;
	}

	/**
	 * Lấy type của ảnh.
	 * 
	 * @param image
	 *            ảnh cần xét
	 * @return giá trị type của ảnh
	 */
	public static int getType(BufferedImage image) {
		if ((image == TREE_1) || (image == TREE_2) || (image == TREE_3)
				|| (image == TREE_4) || (image == TREE_5) || (image == TREE_6))
			return TREE_TYPE;
		else if ((image == PLATFORM_1) || (image == PLATFORM_2)
				|| (image == PLATFORM_3))
			return PLATFORM_TYPE;
		else if ((image == TRUCK_DOWN) || (image == TRUCK)
				|| (image == SHIP_RIGHT) || (image == SHIP)
				|| (image == AIRPLANE_RIGHT) || (image == AIRPLANE))
			return VEHICLE_TYPE;

		return -1;
	}

	/**
	 * Lấy id của ảnh.
	 * 
	 * @param image
	 *            ảnh cần xét
	 * @return id của item tương ứng với ảnh
	 */
	public static int getId(BufferedImage image) {
		if (image == TREE_1)
			return 1;
		else if (image == TREE_2)
			return 2;
		else if (image == TREE_3)
			return 3;
		else if (image == TREE_4)
			return 4;
		else if (image == TREE_5)
			return 5;
		else if (image == TREE_6)
			return 6;

		else if (image == TRUCK_DOWN)
			return TRUCK_DOWN_ID;
		else if (image == TRUCK)
			return TRUCK_LEFT_ID;
		else if (image == SHIP_RIGHT)
			return SHIP_RIGHT_ID;
		else if (image == SHIP)
			return SHIP_UP_ID;
		else if (image == AIRPLANE_RIGHT)
			return PLANE_RIGHT_ID;
		else if (image == AIRPLANE)
			return PLANE_UP_ID;

		else if (image == PLATFORM_1)
			return 1;
		else if (image == PLATFORM_2)
			return 2;
		else if (image == PLATFORM_3)
			return 3;
		return -1;
	}

	/**
	 * Tính các ô vuông bị phủ khi chèn item vào map
	 * 
	 * @param terminalImage
	 *            ảnh terminal cần chèn
	 * @param position
	 *            vị trí terminal
	 * @param side
	 *            cạnh của ô vuông vẽ
	 * @return danh sách ô vuông bị phủ
	 */
	public static ArrayList<Point> getSquareCovered(
			BufferedImage terminalImage, Point position, int side) {
		ArrayList<Point> list = new ArrayList<Point>();
		if (terminalImage == TRUCK) {
			for (int i = 1; i < 5; i++)
				list.add(new Point(position.x, position.y - i * side));
		} else if (terminalImage == TRUCK_DOWN) {
			for (int i = 1; i < 5; i++)
				list.add(new Point(position.x + i * side, position.y));
		} else if (terminalImage == AIRPLANE_RIGHT) {
			for (int i = 1; i < 4; i++) {
				list.add(new Point(position.x, position.y + i * side));
				list.add(new Point(position.x - side, position.y + i * side));
				list.add(new Point(position.x + side, position.y + i * side));
			}

		} else if (terminalImage == AIRPLANE) {
			for (int i = 1; i < 4; i++) {
				list.add(new Point(position.x - i * side, position.y));
				list.add(new Point(position.x - i * side, position.y - side));
				list.add(new Point(position.x - i * side, position.y + side));
			}

		} else if (terminalImage == SHIP_RIGHT) {
			for (int i = 1; i < 5; i++) {
				list.add(new Point(position.x, position.y + i * side));
				list.add(new Point(position.x - side, position.y + i * side));
			}
		} else if (terminalImage == SHIP) {
			for (int i = 1; i < 5; i++) {
				list.add(new Point(position.x - i * side, position.y));
				list.add(new Point(position.x - i * side, position.y + side));
			}
		}

		return list;
	}

}
