package com.oop.mapcreation;

import java.awt.Point;
import java.util.ArrayList;

import com.oop.data.ButtonImage;
import com.oop.data.ItemImage;
import com.oop.gamepanel.DrawLayer;
import com.oop.gamepanel.GamePanel;
import com.oop.mapcreation.buttons.BackButton;
import com.oop.mapcreation.buttons.ButtonForDraw;
import com.oop.mapcreation.buttons.ButtonForHandle;
import com.oop.mapcreation.buttons.DefaultButton;
import com.oop.mapcreation.buttons.DeleteAllButton;
import com.oop.mapcreation.buttons.DeleteButton;
import com.oop.mapcreation.buttons.EditButton;
import com.oop.mapcreation.buttons.FactoryButton;
import com.oop.mapcreation.buttons.NewButton;
import com.oop.mapcreation.buttons.SaveButton;
import com.oop.mapcreation.buttons.SquareButton;
import com.oop.mapcreation.buttons.TerminalButton;
import com.oop.mapcreation.buttons.TreeButton;

/**
 * class này mục đích cung cấp các hằng số và các đối tượng khởi tạo cho class
 * MapCreation ví dụ như: vị trí của các Button, các mã điều khiển của Button...
 * class này cũng tạo ra cac menu của từng button cho việc vẽ map trong class
 * MapCreation
 * 
 * @author mai tien khai
 * 
 */
public class MapCreationManager {
	// class cung cap cac hang so va khoi tao cac component cho MapCreation

	/** The Constant BACK. */
	public static final int BACK = 11;

	/** cac ma chuc nang cua menu. */
	public static final int DEFAULT = 5;

	/** The Constant DELETE. */
	public static final int DELETE = 6;

	/** The Constant DELETEALL. */
	public static final int DELETEALL = 7;

	/** Mã điều khiển nút Edit File. */
	public static final int EDIT = 9;

	/** The Constant FACTORY. */
	public static final int FACTORY = 1;

	/** The Constant ICON_WIDTH. */
	public static final int ICON_WIDTH = 40;

	/** The Constant MENU_HEIGHT. */
	public static final int MENU_HEIGHT = 50;

	/** Mã điều khiển nút ve một map mới. */
	public static final int NEW = 10;

	/** The Constant SAVE. */
	public static final int SAVE = 8;

	/** The Constant SQUARE. */
	public static final int SQUARE = 3;
	/** The Constant SQUARE_SIDE. */
	public static final int SQUARE_SIDE = 18;

	/** The Constant TERMINAL. */
	public static final int TERMINAL = 2;

	/** The Constant TREE. */
	public static final int TREE = 4;

	/** The default button. */
	private ButtonForHandle defaultButton;

	/** The back button. */
	private ButtonForHandle deleteButton, deleteAllButton, saveButton,
			backButton, editButton, newButton;

	/** The factory button. */
	private FactoryButton factoryButton;

	/** The graphic button list. */
	private ArrayList<ButtonForDraw> graphicButtonList;

	/** The handle button list. */
	private ArrayList<ButtonForHandle> handleButtonList;

	/** The item list layer. */
	private DrawLayer[] itemListLayer;

	/** layer dung de chua tat ca cac itemList. */
	private DrawLayer itemMapLayer;

	/** thoi gian choi. */
	private Time minute, second;

	/** The square button. */
	private SquareButton squareButton;

	/** The terminal button. */
	private TerminalButton terminalButton;

	/** The tree button. */
	private TreeButton treeButton;

	/**
	 * Hàm khởi tạo đối tượng MapCreationManager.
	 * 
	 * @param parent
	 *            the parent
	 */
	public MapCreationManager(GamePanel parent) {
		itemListLayer = new DrawLayer[4];
		for (int i = 0; i < itemListLayer.length; i++)
			itemListLayer[i] = new DrawLayer(parent.width, parent.height);
		/* ini cac layer ve ra man hinh */
		itemMapLayer = new DrawLayer(parent.width, parent.height);
		iniButton();
		iniSquareLayer();
		iniTreeLayer();
		iniTerminalLayer();
		iniFactoryLayer();
		Point minutePosition = new Point((BACK + 3) * ICON_WIDTH, 0);
		minute = new Time(minutePosition, ICON_WIDTH, ICON_WIDTH, " phut");
		minute.setTime(3);
		Point secondPosition = new Point(minutePosition.x + ICON_WIDTH, 0);
		second = new Time(secondPosition, ICON_WIDTH, ICON_WIDTH, " giay");
		second.setTime(30);
	}

	/**
	 * Gets the button for draw.
	 * 
	 * @return the button for draw
	 */
	public ArrayList<ButtonForDraw> getButtonForDraw() {
		return graphicButtonList;
	}

	/**
	 * Gets the button for handle.
	 * 
	 * @return the button for handle
	 */
	public ArrayList<ButtonForHandle> getButtonForHandle() {
		return handleButtonList;
	}

	/**
	 * Gets the item list layer.
	 * 
	 * @return the item list layer
	 */
	public DrawLayer[] getItemListLayer() {
		return itemListLayer;
	}

	/**
	 * Gets the item map layer.
	 * 
	 * @return the item map layer
	 */
	public DrawLayer getItemMapLayer() {
		return itemMapLayer;
	}

	/**
	 * Gets the minute.
	 * 
	 * @return the minute
	 */
	public Time getMinute() {
		return minute;
	}

	/**
	 * Gets the second.
	 * 
	 * @return the second
	 */
	public Time getSecond() {
		return second;
	}

	/**
	 * Khởi tạo các Button.
	 */

	public void iniButton() {

		int factoryX = (FACTORY - 1) * ICON_WIDTH;
		int factoryY = 0;

		int terminalX = (TERMINAL - 1) * ICON_WIDTH;
		int terminalY = 0;

		int deleteX = (DELETE - 1) * ICON_WIDTH;
		int deleteY = 0;

		int deleteAllX = (DELETEALL - 1) * ICON_WIDTH;
		int deleteAllY = 0;

		int squareX = (SQUARE - 1) * ICON_WIDTH;
		int squareY = 0;

		int treeX = (TREE - 1) * ICON_WIDTH;
		int treeY = 0;

		int saveX = (SAVE - 1) * ICON_WIDTH;
		int saveY = 0;

		int backX = (BACK - 1) * ICON_WIDTH;
		int backY = 0;

		int defaultX = (DEFAULT - 1) * ICON_WIDTH;
		int defaultY = 0;

		int editFileX = (EDIT - 1) * ICON_WIDTH;
		int editFileY = 0;

		int newButtonX = (NEW - 1) * ICON_WIDTH;
		int newButtonY = 0;

		graphicButtonList = new ArrayList<ButtonForDraw>();
		handleButtonList = new ArrayList<ButtonForHandle>();

		factoryButton = new FactoryButton(new Point(factoryX, factoryY),
				ButtonImage.FACTORY_BUTTON_IMAGE,
				ButtonImage.ACTIVE_FACTORY_BUTTON_IMAGE, FACTORY);
		factoryButton.setDimension(ICON_WIDTH, ICON_WIDTH);
		factoryButton.setName("factory");
		graphicButtonList.add(factoryButton);

		terminalButton = new TerminalButton(new Point(terminalX, terminalY),
				ButtonImage.TERMINAL_BUTTON_IMAGE,
				ButtonImage.ACTIVE_TERMINAL_BUTTON_IMAGE, TERMINAL);
		terminalButton.setDimension(ICON_WIDTH, ICON_WIDTH);
		terminalButton.setName("terminal");
		graphicButtonList.add(terminalButton);

		defaultButton = new DefaultButton(new Point(defaultX, defaultY),
				ButtonImage.DEFAULT_BUTTON_IMAGE,
				ButtonImage.ACTIVE_DEFAULT_BUTTON_IMAGE, DEFAULT);
		defaultButton.setDimension(ICON_WIDTH, ICON_WIDTH);
		defaultButton.setName("DEFAULT");
		handleButtonList.add(defaultButton);

		deleteButton = new DeleteButton(new Point(deleteX, deleteY),
				ButtonImage.DELETE_BUTTON_IMAGE,
				ButtonImage.ACTIVE_DELETE_BUTTON_IMAGE, DELETE);
		deleteButton.setDimension(ICON_WIDTH, ICON_WIDTH);
		deleteButton.setName("DELETE");
		handleButtonList.add(deleteButton);

		deleteAllButton = new DeleteAllButton(
				new Point(deleteAllX, deleteAllY),
				ButtonImage.DELETEALL_BUTTON_IMAGE,
				ButtonImage.ACTIVE_DELETEALL_BUTTON_IMAGE, DELETEALL);
		deleteAllButton.setDimension(ICON_WIDTH, ICON_WIDTH);
		deleteAllButton.setName("DELETE all");
		handleButtonList.add(deleteAllButton);

		squareButton = new SquareButton(new Point(squareX, squareY),
				ButtonImage.SQUARE_BUTTON_IMAGE,
				ButtonImage.ACTIVE_SQUARE_BUTTON_IMAGE, SQUARE);
		squareButton.setDimension(ICON_WIDTH, ICON_WIDTH);
		squareButton.setName("square");
		graphicButtonList.add(squareButton);

		treeButton = new TreeButton(new Point(treeX, treeY),
				ButtonImage.TREE_BUTTON_IMAGE,
				ButtonImage.ACTIVE_TREE_BUTTON_IMAGE, TREE);
		treeButton.setDimension(ICON_WIDTH, ICON_WIDTH);
		treeButton.setName("tree");
		graphicButtonList.add(treeButton);

		saveButton = new SaveButton(new Point(saveX, saveY),
				ButtonImage.SAVE_BUTTON_IMAGE,
				ButtonImage.ACTIVE_SAVE_BUTTON_IMAGE, SAVE);
		saveButton.setDimension(ICON_WIDTH, ICON_WIDTH);
		saveButton.setName("SAVE");
		handleButtonList.add(saveButton);

		backButton = new BackButton(new Point(backX, backY),
				ButtonImage.BACK_BUTTON_IMAGE,
				ButtonImage.ACTIVE_BACK_BUTTON_IMAGE, BACK);
		backButton.setDimension(ICON_WIDTH, ICON_WIDTH);
		backButton.setName("BACK");
		handleButtonList.add(backButton);

		editButton = new EditButton(new Point(editFileX, editFileY),
				ButtonImage.EDIT_BUTTON_IMAGE,
				ButtonImage.ACTIVE_EDIT_BUTTON_IMAGE, EDIT);
		editButton.setDimension(ICON_WIDTH, ICON_WIDTH);
		editButton.setName("Edit a map");
		handleButtonList.add(editButton);

		newButton = new NewButton(new Point(newButtonX, newButtonY),
				ButtonImage.NEW_BUTTON_IMAGE, ButtonImage.NEW_BUTTON_IMAGE, NEW);
		newButton.setDimension(ICON_WIDTH, ICON_WIDTH);
		newButton.setName("Create new canvas to draw");
		handleButtonList.add(newButton);
	}

	/**
	 * Khởi tạo các menu chọn cho Button Square.
	 */
	public void iniSquareLayer() {
		// FACTORYItemList
		int baseHeight = squareButton.getHeight();
		int baseWidth = squareButton.getHeight();
		Point waterPosition = new Point(squareButton.getPosition().x,
				squareButton.getPosition().y + baseHeight);
		Point soilPosition = new Point(waterPosition.x, waterPosition.y
				+ baseHeight / 2);
		Point grassPosition = new Point(soilPosition.x, soilPosition.y
				+ baseHeight / 2);

		MenuItem waterItem = new MenuItem(ItemImage.PLATFORM_2, waterPosition,
				baseWidth, baseHeight / 2, squareButton);
		MenuItem soilItem = new MenuItem(ItemImage.PLATFORM_3, soilPosition,
				baseWidth, baseHeight / 2, squareButton);
		MenuItem grassItem = new MenuItem(ItemImage.PLATFORM_1, grassPosition,
				baseWidth, baseHeight / 2, squareButton);

		itemListLayer[SQUARE - 1].addDrawable(waterItem);
		itemListLayer[SQUARE - 1].addDrawable(grassItem);
		itemListLayer[SQUARE - 1].addDrawable(soilItem);

		squareButton.setItemList(itemListLayer[SQUARE - 1]);
	}

	/**
	 * Khởi tạo các Menu cho Button Terminal.
	 */
	public void iniTerminalLayer() {
		int baseHeight = terminalButton.getHeight();
		int baseWidth = terminalButton.getHeight();
		Point temp = terminalButton.getPosition();
		Point truckLeftPosition = new Point(temp.x, temp.y + baseHeight);
		int h = baseHeight;
		int w = 2 * baseWidth;
		Point truckDownPosition = new Point(truckLeftPosition.x,
				truckLeftPosition.y + h);
		Point planeUpPosition = new Point(truckDownPosition.x,
				truckDownPosition.y + h);
		Point planeRightPosition = new Point(planeUpPosition.x,
				planeUpPosition.y + h);
		Point shipUpPosition = new Point(planeRightPosition.x,
				planeRightPosition.y + h);
		Point shipRightPosition = new Point(shipUpPosition.x, shipUpPosition.y
				+ h);

		MenuItem truckLeft = new MenuItem(ItemImage.TRUCK, truckLeftPosition,
				w, h, terminalButton);
		MenuItem truckDown = new MenuItem(ItemImage.TRUCK_DOWN,
				truckDownPosition, w, h, terminalButton);
		MenuItem shipUp = new MenuItem(ItemImage.SHIP, shipUpPosition, w, h,
				terminalButton);
		MenuItem shipRight = new MenuItem(ItemImage.SHIP_RIGHT,
				shipRightPosition, w, h, terminalButton);
		MenuItem planeUp = new MenuItem(ItemImage.AIRPLANE, planeUpPosition, w,
				h, terminalButton);
		MenuItem planeRight = new MenuItem(ItemImage.AIRPLANE_RIGHT,
				planeRightPosition, w, h, terminalButton);

		itemListLayer[TERMINAL - 1].addDrawable(truckLeft);
		itemListLayer[TERMINAL - 1].addDrawable(truckDown);
		itemListLayer[TERMINAL - 1].addDrawable(shipUp);
		itemListLayer[TERMINAL - 1].addDrawable(shipRight);
		itemListLayer[TERMINAL - 1].addDrawable(planeUp);
		itemListLayer[TERMINAL - 1].addDrawable(planeRight);
		terminalButton.setItemList(itemListLayer[TERMINAL - 1]);

	}

	/**
	 * Khởi tạo các menu chọn cho Button Tree.
	 */
	public void iniTreeLayer() {
		int baseHeight = treeButton.getHeight();
		int baseWidth = treeButton.getHeight();
		Double height = (double) baseWidth * 58 / 36;
		int h = height.intValue();
		Point tree1Position = new Point(treeButton.getPosition().x,
				treeButton.getPosition().y + baseHeight);
		Point tree2Position = new Point(tree1Position.x, tree1Position.y + h);
		Point tree3Position = new Point(tree2Position.x, tree2Position.y + h);
		Point tree4Position = new Point(tree3Position.x, tree3Position.y + h);
		Point tree5Position = new Point(tree4Position.x, tree4Position.y + h);
		Point tree6Position = new Point(tree5Position.x, tree5Position.y + h);

		MenuItem tree1Item = new MenuItem(ItemImage.TREE_1, tree1Position,
				baseWidth, h, treeButton);
		MenuItem tree2Item = new MenuItem(ItemImage.TREE_2, tree2Position,
				baseWidth, h, treeButton);
		MenuItem tree3Item = new MenuItem(ItemImage.TREE_3, tree3Position,
				baseWidth, h, treeButton);
		MenuItem tree4Item = new MenuItem(ItemImage.TREE_4, tree4Position,
				baseWidth, h, treeButton);
		MenuItem tree5Item = new MenuItem(ItemImage.TREE_5, tree5Position,
				baseWidth, h, treeButton);
		MenuItem tree6Item = new MenuItem(ItemImage.TREE_6, tree6Position,
				baseWidth, h, treeButton);

		itemListLayer[TREE - 1].addDrawable(tree6Item);
		itemListLayer[TREE - 1].addDrawable(tree5Item);
		itemListLayer[TREE - 1].addDrawable(tree4Item);
		itemListLayer[TREE - 1].addDrawable(tree3Item);
		itemListLayer[TREE - 1].addDrawable(tree2Item);
		itemListLayer[TREE - 1].addDrawable(tree1Item);

		treeButton.setItemList(itemListLayer[TREE - 1]);

	}

	/**
	 * khởi tạo các menu cho Button Factory.
	 */
	private void iniFactoryLayer() {
		int baseHeight = factoryButton.getHeight();
		int baseWidth = factoryButton.getHeight();
		Point temp = factoryButton.getPosition();
		Point truckLeftPosition = new Point(temp.x, temp.y + baseHeight);
		int h = baseHeight;
		int w = 2 * baseWidth;
		Point truckDownPosition = new Point(truckLeftPosition.x,
				truckLeftPosition.y + h);
		Point planeUpPosition = new Point(truckDownPosition.x,
				truckDownPosition.y + h);
		Point planeRightPosition = new Point(planeUpPosition.x,
				planeUpPosition.y + h);
		Point shipUpPosition = new Point(planeRightPosition.x,
				planeRightPosition.y + h);
		Point shipRightPosition = new Point(shipUpPosition.x, shipUpPosition.y
				+ h);

		MenuItem truckLeft = new MenuItem(ItemImage.TRUCK, truckLeftPosition,
				w, h, factoryButton);
		MenuItem truckDown = new MenuItem(ItemImage.TRUCK_DOWN,
				truckDownPosition, w, h, factoryButton);
		MenuItem shipUp = new MenuItem(ItemImage.SHIP, shipUpPosition, w, h,
				factoryButton);
		MenuItem shipRight = new MenuItem(ItemImage.SHIP_RIGHT,
				shipRightPosition, w, h, factoryButton);
		MenuItem planeUp = new MenuItem(ItemImage.AIRPLANE, planeUpPosition, w,
				h, factoryButton);
		MenuItem planeRight = new MenuItem(ItemImage.AIRPLANE_RIGHT,
				planeRightPosition, w, h, factoryButton);

		itemListLayer[FACTORY - 1].addDrawable(truckLeft);
		itemListLayer[FACTORY - 1].addDrawable(truckDown);
		itemListLayer[FACTORY - 1].addDrawable(shipUp);
		itemListLayer[FACTORY - 1].addDrawable(shipRight);
		itemListLayer[FACTORY - 1].addDrawable(planeUp);
		itemListLayer[FACTORY - 1].addDrawable(planeRight);
		factoryButton.setItemList(itemListLayer[FACTORY - 1]);
	}

}
