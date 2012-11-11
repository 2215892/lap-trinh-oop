package com.btl.data;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.btl.Model.ConversionFunction;
import com.btl.Model.Direction;

public abstract class ItemImage {

	public static final int PLATFORM_TYPE = 2;

	public static final int TREE_TYPE = 3;

	public static final int SHIP_RIGHT_ID = 6;

	public static final int SHIP_UP_ID = 5;

	public static final int PLANE_RIGHT_ID = 4;

	public static final int PLANE_UP_ID = 3;

	public static final int TRUCK_DOWN_ID = 2;

	public static final int TRUCK_LEFT_ID = 1;

	public static final int VEHICLE_TYPE = 1;

	public final static String RES_DIR = ConversionFunction
			.getCurrentDirectory() + "res\\item\\";

	public final static BufferedImage AIRPLANE = ConversionFunction
			.loadImage(RES_DIR + "AIRPLANE.png");

	public static final BufferedImage AIRPLANE_RIGHT = ConversionFunction
			.flipHorizontally(AIRPLANE);

	public final static BufferedImage PLATFORM_1 = ConversionFunction
			.loadImage(RES_DIR + "PLATFORM_1.png");
	public final static BufferedImage PLATFORM_2 = ConversionFunction
			.loadImage(RES_DIR + "PLATFORM_2.png");
	public final static BufferedImage PLATFORM_3 = ConversionFunction
			.loadImage(RES_DIR + "PLATFORM_3.png");

	public final static BufferedImage PLATFORM_4 = ConversionFunction
			.loadImage(RES_DIR + "PLATFORM_4.png");
	public final static BufferedImage SHIP = ConversionFunction
			.loadImage(RES_DIR + "SHIP.png");

	public static final BufferedImage SHIP_RIGHT = ConversionFunction
			.flipHorizontally(SHIP);
	public final static BufferedImage TREE_1 = ConversionFunction
			.loadImage(RES_DIR + "TREE_1.png");
	public final static BufferedImage TREE_2 = ConversionFunction
			.loadImage(RES_DIR + "TREE_2.png");
	public final static BufferedImage TREE_3 = ConversionFunction
			.loadImage(RES_DIR + "TREE_3.png");

	public final static BufferedImage TREE_4 = ConversionFunction
			.loadImage(RES_DIR + "TREE_4.png");
	public final static BufferedImage TREE_5 = ConversionFunction
			.loadImage(RES_DIR + "TREE_5.png");
	public final static BufferedImage TREE_6 = ConversionFunction
			.loadImage(RES_DIR + "TREE_6.png");
	public final static BufferedImage TRUCK = ConversionFunction
			.loadImage(RES_DIR + "TRUCK.png");

	public static final BufferedImage TRUCK_DOWN = ConversionFunction
			.flipHorizontally(TRUCK);

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
	 * xac dinh huong cho cac anh, dung cho cac anh thuoc lop terminal, factory.
	 * 
	 * @param image
	 *            anh can xac dinh huong
	 * @return huong cua anh
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
	 * lay type cua anh.
	 * 
	 * @param image
	 *            anh can xem loai
	 * @return loai cua anh
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
	 * Gets the id.
	 * 
	 * @param image
	 *            the image
	 * @return the id
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
	 * ham tinh cac o vuong bi phu khi chen anh terminal vao map.
	 * 
	 * @param terminalImage
	 *            anh terminal can chen
	 * @param position
	 *            vi tri cua terminal
	 * @param side
	 *            canh o vuong ve
	 * @return list cac o vuong bi phu
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
