package com.btl.data;

import java.awt.Point;
import java.awt.image.BufferedImage;

import com.btl.Model.ConversionFunction;

public abstract class ItemImage {

	public final static String RES_DIR = "E:\\Working project\\OOP\\res\\";

	public final static BufferedImage AIRPLANE = ConversionFunction
			.loadImage(RES_DIR + "AIRPLANE.png");

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

	public static BufferedImage getItemImage(int id, int type) {
		switch (type) {
			case 1 : /* TRUCK, AIRPLANE, SHIP */
				switch (id) {
					case 1 : /* TRUCK_LEFT */
						return TRUCK;
					case 2 : /* TRUCK DOWN */
						return ConversionFunction.flipHorizontally(TRUCK);
					case 3 : /* PLANE_UP */
						return AIRPLANE;
					case 4 :/* PLANE_RIGHT */
						return ConversionFunction.flipHorizontally(AIRPLANE);
					case 5 :/* SHIP_UP */
						return SHIP;
					case 6 :/* SHIP_RIGHT */
						return ConversionFunction.flipHorizontally(SHIP);
				}
				break;
			case 3 : /* TREE */
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
			case 2 : /* PLATFORM */
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
			case 1 : /* TRUCK, AIRPLANE, SHIP */
				switch (id) {
					case 1 : /* TRUCK_LEFT */
						return new Point(6, 50);
					case 2 : /* TRUCK DOWN */
						return new Point(36, 50);
					case 3 : /* PLANE_UP */
						return new Point(32, 38);
					case 4 :/* PLANE_RIGHT */
						return new Point(6, 38);
					case 5 :/* SHIP_UP */
						return new Point(36, 77);
					case 6 :/* SHIP_RIGHT */
						return new Point(2, 77);
				}
				break;
			case 3 : /* TREE */
				return new Point(1, 48);
			case 2 : /* PLATFORM */
				return new Point(1, 9);
		}

		return null;

	}

}
