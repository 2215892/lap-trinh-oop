package com.btl.GameBoard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.btl.GameElements.mapstate.ButtonForDraw;
import com.btl.GameElements.mapstate.ButtonForHandle;
import com.btl.GameElements.mapstate.DrawingButton;
import com.btl.GameElements.mapstate.FactoryIcon;
import com.btl.GameElements.mapstate.FactoryMap;
import com.btl.GameElements.mapstate.ItemMap;
import com.btl.GameElements.mapstate.MapCreationManager;
import com.btl.GameElements.mapstate.MenuItem;
import com.btl.GameElements.mapstate.MouseOverSquare;
import com.btl.GameElements.mapstate.SquareMap;
import com.btl.GameElements.mapstate.SwitchMap;
import com.btl.GameElements.mapstate.TerminalIcon;
import com.btl.GameElements.mapstate.TerminalMap;
import com.btl.GameElements.mapstate.Time;
import com.btl.GameElements.playstate.DrawLayer;
import com.btl.GameEngine.Drawable;
import com.btl.GameEngine.Layer;
import com.btl.GameEngine.MapDeleting;
import com.btl.GameEngine.MapSaving;
import com.btl.Model.AuxiliaryFunction;
import com.btl.Model.ConversionFunction;
import com.btl.Model.Direction;
import com.btl.Model.MapRecovery;
import com.btl.Model.ModelFactory;
import com.btl.Model.ModelItem;
import com.btl.Model.ModelMap;
import com.btl.Model.ModelSwitch;
import com.btl.Model.ModelTerminal;
import com.btl.data.ItemImage;
import com.btl.data.OtherImage;
// TODO: Auto - generated Javadoc
/**
 * đây là class dùng để vẽ map cho game, người chơi dùng chuột để chọn những thứ
 * cần vẽ, tất cả những thứ cần vẽ đều nằm trong class này.
 * 
 * @author mai tien khai
 * 
 */
public class MapCreation extends GameState implements MouseMotionListener {

	/** điểm trên cùng tọa độ dương của trục logic Oy'. */
	private int logicHeightP;

	/** điểm dưới cùng tọa độ dương của trục logic Oy'. */
	private int logicHeightN;

	/** điểm lớn nhất trên trục Ox'( điểm thấp nhất là 0 ). */
	private int logicWidth;

	/** hướng drag chuột. */
	private Direction dragDir;

	/** các Button dùng để vẽ, ví dụ: cây, xe tải, máy bay, thảm cỏ... */
	private ArrayList<ButtonForDraw> graphicButtonList;

	/** các Button dùng để xử lý như : xóa, lưu, quay lại menu trước... */
	private ArrayList<ButtonForHandle> handleButtonList;
	/** layer ảnh của các vật được vẽ vào như : máy bay, xe tải, cây, cỏ ... */
	private DrawLayer itemMapLayer;

	/** những ô vuông trong lưới ô vuông bị phủ bởi factory và terminal. */
	private ArrayList<Point> squareCovedList;

	/**
	 * layer vẽ các menu Item cho các GraphicButton, ví dụ ấn vào button cây
	 * hiện ra các cây để chọn.
	 */
	private DrawLayer[] itemListLayer;

	/** layer để vẽ ô vuông factory. */
	private DrawLayer factoryLayer;

	/**
	 * layer để vẽ cho ô vuông switch (là các ô băng chuyền được vẽ hướng ở
	 * trên).
	 */
	private DrawLayer switchLayer;

	/** layer vẽ ô vuông terinal. */
	private DrawLayer terminalLayer;

	/** layer vẽ các button. */
	private Layer menuLayer;

	/** layer vẽ thời gian hiển thị, giây phút. */
	private Layer timeLayer;

	/** layer để hiển thị xem một ô có thể vẽ switch được không. */
	private Layer mouseMoveLayer;

	/**
	 * layer dùng để chứa các tất cả đối tượng để vẽ (gộp từ một số layer) để
	 * tính thứ tự vẽ.
	 */
	private DrawLayer layerCluster;

	/** ảnh đệm để vẽ vào trước khi vẽ ra Panel. */
	private Image bufferImage;

	/** ảnh nền (lưới ô vuông). */
	private Image bgImage;

	/** tên file map lưu. */
	private String fileName;

	/** kiểm tra xem hoàn thành việc khởi động vẽ hay chưa. */
	private boolean loaded = false;

	/** màu cho dải menu ngăn cách vơi lưới ô vuông vẽ. */
	private Color bgMenuColor = Color.green;

	/** thời gian chơi úng với map (số phút). */
	private Time minute;

	/** thời gian chơi ứng vơi map (sô giây). */
	private Time second;

	/**
	 * Hàm khởi tạo để bắt đầu vẽ một map mới.
	 * 
	 * @param parent
	 *            - the parent
	 */
	public MapCreation(GamePanel parent) {
		super(parent);
		ini();
		parent.repaint();
		loaded = true;
	}

	/**
	 * hàm khởi tạo cho mục đích Edit một map có sẵn.
	 * 
	 * @param parent
	 *            - panel để vẽ vào
	 * @param fileName
	 *            - địa chỉ map cần edit
	 */
	public MapCreation(GamePanel parent, String fileName) {
		super(parent);
		this.fileName = fileName;

		ini();
		loadElementFromFile(fileName);

		MapRecovery maprecovery = MapRecovery.createMapRecovery(switchLayer,
				factoryLayer, terminalLayer, MapCreationManager.SQUARE_SIDE,
				MapCreationManager.SQUARE_SIDE, this);
		switchLayer = maprecovery.getSwitchLayer();
		factoryLayer = maprecovery.getFactoryLayer();
		terminalLayer = maprecovery.getTerminalLayer();
		switchLayer.render();
		AuxiliaryFunction.showWrongFactory(this);
		AuxiliaryFunction.showWrongSwitch(this);
		parent.repaint();
		loaded = true;
	}

	/**
	 * khởi tạo các Layer, Button, Menu... để bắt đầu vẽ
	 */
	void ini() {
		/* khoi tao load cac image cho cac soil,waa */
		parent.addMouseMotionListener(this);

		bufferImage = new BufferedImage(parent.height, parent.width,
				BufferedImage.TYPE_INT_ARGB);
		bgImage = new BufferedImage(parent.height, parent.width,
				BufferedImage.TYPE_INT_ARGB);

		logicHeightN = -parent.height;
		logicHeightP = (int) (parent.width / (Math.sqrt(3)));
		logicWidth = logicHeightP - logicHeightN;

		menuLayer = new Layer(parent.width, parent.height);
		factoryLayer = new DrawLayer(parent.width, parent.height);
		switchLayer = new DrawLayer(parent.width, parent.height);

		timeLayer = new Layer(parent.width, parent.height);
		mouseMoveLayer = new Layer(parent.width, parent.height);

		terminalLayer = new DrawLayer(parent.width, parent.height);
		layerCluster = new DrawLayer(parent.width, parent.height);
		squareCovedList = new ArrayList<Point>();

		MapCreationManager map = new MapCreationManager(parent);
		/* add cac menu vao layer */
		graphicButtonList = map.getButtonForDraw();
		handleButtonList = map.getButtonForHandle();
		itemListLayer = map.getItemListLayer();

		/* dat menu vao trang thai ban dau */
		resetMenu();
		/* tao luoi BgImage */
		iniBgImage();
		/* add cac menu dieu khien vao menuLayer */
		fillMenu();
		/* hien thi cac menu */
		itemMapLayer = map.getItemMapLayer();
		itemListLayer = map.getItemListLayer();

		minute = map.getMinute();
		second = map.getSecond();
		timeLayer.addDrawable(minute);
		timeLayer.addDrawable(second);
		timeLayer.render();

		control = MapCreationManager.DEFAULT;
		handleButtonList.get(0).activeRender();
		menuLayer.render();
		parent.repaint();
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameBoard.GameState#update()
	 */
	@Override
	public void update() {
		// TODO st
	}

	/**
	 * add các layer vào layer cluster để trộn tính thứ tự vẽ.
	 * 
	 * @param list
	 *            - các đối tượng thực thi Drawable
	 */
	private void addListToLayerCluster(ArrayList<Drawable> list) {
		for (Drawable i : list)
			layerCluster.addDrawable(i);
	}

	/**
	 * xử lí khi người chơi ấn chuột vào cấc Button.
	 */
	private void handleMenuLayer() {
		Drawable clickedObj;
		clickedObj = menuLayer.getClickedObj(pointClicked);
		DrawingButton temp = (DrawingButton) clickedObj;
		if (temp.getState()) {
			instantButton = temp;
			resetMenu();
			if (temp instanceof ButtonForDraw) {

				((ButtonForDraw) temp).showmenu();
			}
			control = temp.getControlCode();

			temp.activeRender();
		} else {
			/* voi nhung menu khong co itemList */
			if (!(temp instanceof ButtonForDraw)) {
				control = MapCreationManager.DEFAULT;
				resetMenu();
				handleButtonList.get(0).activeRender();
			} else {
				((ButtonForDraw) temp).showmenu();
			}

		}
		menuLayer.render();
		parent.repaint();
		if (temp.getControlCode() == MapCreationManager.DELETEALL) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			handleMenuDeleteAll();
			temp.normalRender();
			menuLayer.render();
			parent.repaint();
		} else if (temp.getControlCode() == MapCreationManager.SAVE) {
			handleMenuSave();

			instantButton.normalRender();
			handleButtonList.get(0).activeRender();
			menuLayer.render();
			parent.repaint();
			control = MapCreationManager.DEFAULT;
		} else if (temp.getControlCode() == MapCreationManager.EDIT) {
			handleEditMap();

			instantButton.normalRender();
			handleButtonList.get(0).activeRender();
			menuLayer.render();
			parent.repaint();
			control = MapCreationManager.DEFAULT;
		}

		else if (temp.getControlCode() == MapCreationManager.BACK) {
			// TODO xu li khi ma nguoi choi an nut BACK
		}

	}

	/** tọa độ của chuột ở tọa độ thực. */
	private Point pointClicked;

	/*
	 * (non - Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		mX = e.getX();
		mY = e.getY();
		pointClicked = new Point(mX, mY);
	}

	/**
	 * Hiện messageBox để nhập thời gian chơi.
	 * 
	 * @param time
	 *            - đối tượng thời gian cần chỉnh(phut, giay)
	 */
	private void handleTimeSetting(Time time) {

		String message = "Nhap vao so" + time.getUnit();
		int number = -1;
		while ((number > 60) || (number < 0)) {
			number = AuxiliaryFunction.getInputNumber(message, time.getTime(),
					parent);
		}
		time.setTime(number);
		timeLayer.render();
		parent.repaint();
	}

	/**
	 * hiện message box để điền số box tối đa terminal có thể chứa.
	 * 
	 * @param clickedTer
	 *            - Terminal cần thông tin số box
	 */
	private void handleBoxNumberInput(TerminalMap clickedTer) {
		String message = "Nhap vao so box toi da chay vao : ";
		int number = AuxiliaryFunction.getInputNumber(message,
				clickedTer.getBoxNumber(), parent);
		clickedTer.setBoxBumber(number);
	}

	/**
	 * đổi hướng hiện tại của switch(đối với switch có nhiều hơn hai hướng).
	 */
	private void changeCurrentDir() {
		Point temp = getSquare();
		/* neu ma la mot Switch co nhieu hon hai huong thi changeCurrent */
		if (temp != null) {
			SwitchMap sw = AuxiliaryFunction.findSwitch(temp, this.switchLayer);
			if ((sw != null) && (sw.getListDirection().size() > 1)) {
				sw.changeDirection();
				/* doi huong xong thi lai them vao */
				switchLayer.render();
				parent.repaint();
			}
		}

	}
	/*
	 * (non - Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto - generated method stub

	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto - generated method stub

	}

	/**
	 * mã thực hiện của Button, ví dụ khi thực hiện vẽ cây thì control bằng mã
	 * của button tree.
	 */
	private int control = 0;

	/**
	 * điểm góc trái nhất của ô vuông chứa chuột trước khi rời chuột sang ô
	 * active.
	 */
	private Point last;

	/** điểm góc trái nhất của ô vuông chứa chuột tại thời điểm hiện tại. */
	private Point active;

	/*
	 * (non - Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		mX = e.getX();
		mY = e.getY();
		pointClicked = new Point(mX, mY);
		Point tg = getSquare();
		if (e.getButton() == MouseEvent.BUTTON3)
			changeCurrentDir();// doi huong cua switch
		else if ((e.getButton() == MouseEvent.BUTTON1)) {
			/* thuc hien kiem tra khi an chuot vao cac menu dieu khien */
			MenuItem itemClicked = getItemClicked(pointClicked);
			/* truong hop click chuot vao menu dieu khien */
			if ((menuLayer.getClickedObj(pointClicked) != null))
				handleMenuLayer();

			else if (itemClicked != null) {
				/* khi click vao menuitem thi thiet lap cho instantitem */
				instantItem = itemClicked;
				instantButton = itemClicked.getButton();
				/* khi click chuot vao cac item thi thiet lap chon cho button */
				itemClicked.choose();
				/* chon xong thi bien mat */
				itemClicked.hideButtonMenu();
				parent.repaint();
			}
			/*
			 * truong hop click chuot vao cac menuTime hoac cac terminal de
			 * chinh so box trong terminal
			 */
			else {
				Drawable clickedOb = timeLayer.getClickedObj(pointClicked);
				if (clickedOb != null) {
					Time time = (Time) clickedOb;
					handleTimeSetting(time);
				}
			}
			if (tg != null) {
				/* kiem tra xem terminal co dc an vao hay khong */
				TerminalMap clickedTer = AuxiliaryFunction.findTerminal(tg,
						terminalLayer);
				ItemMap ter = AuxiliaryFunction.findItem(tg, itemMapLayer);
				if ((clickedTer != null) && (ter != null)
						&& (control == MapCreationManager.DEFAULT))
					handleBoxNumberInput(clickedTer);
				last = active;
				active = tg; // gan gia tri cua active cho o vua kich
				if (control == MapCreationManager.DELETE)
					handleMenuDelete();
				else if (checkMenuHidden()) {
					if ((control != MapCreationManager.DEFAULT)) {
						if (instantButton instanceof ButtonForDraw) {
							addItem((ButtonForDraw) instantButton, tg);
							parent.repaint();
						}

					}
				}

			}

		}

	}

	/**
	 * thêm các item vào menu Layer ví dụ thêm một cây vào map khi người chơi vẽ
	 * thêm một cây. Trong trường hợp đối tượng vẽ là biểu tượng của factory hay
	 * terminal(máy bay, xe tải...) thì cần thêm các đối tượng ô vuông của chúng
	 * nữa (FactoryMap, TerminalMap)
	 * 
	 * @param choosenButton
	 *            - button ứng với item vẽ (ví dụ vẽ cây thì là button Tree)
	 * @param position
	 *            - vị trí ô vuông vẽ vào
	 */
	private void addItem(ButtonForDraw choosenButton, Point position) {
		ItemMap item = choosenButton.generateItem(position,
				MapCreationManager.SQUARE_SIDE);
		item.calculateValidation(this);
		if (item.getValidation()) {
			itemMapLayer.addDrawable(item);
			if (item instanceof TerminalIcon) {
				/* chen mot terminalmap tuong ung vao terminalLayer */
				TerminalMap newOne = new TerminalMap(position,
						MapCreationManager.SQUARE_SIDE);
				terminalLayer.addDrawable(newOne);
				/* cap nhat list cac o vuong bi phu boi terminalIcon */
				ArrayList<Point> pointCoved = ItemImage.getSquareCovered(
						item.getImage(), position,
						MapCreationManager.SQUARE_SIDE);
				for (Point i : pointCoved)
					squareCovedList.add(i);

				SwitchMap sw = AuxiliaryFunction.findSwitch(position,
						switchLayer);
				switchLayer.removeDrawable(sw);
				handleBoxNumberInput(newOne);
			} else if (item instanceof FactoryIcon) {
				FactoryMap newOne = new FactoryMap(position,
						MapCreationManager.SQUARE_SIDE,
						MapCreationManager.SQUARE_SIDE);
				factoryLayer.addDrawable(newOne);
				ArrayList<Point> pointCoved = ItemImage.getSquareCovered(
						item.getImage(), position,
						MapCreationManager.SQUARE_SIDE);
				for (Point i : pointCoved)
					squareCovedList.add(i);
				AuxiliaryFunction.showWrongFactory(this);
			}
		}

	}

	/**
	 * xử lí khi mà ấn vào button DELETE.
	 */
	private void handleMenuDelete() {
		Point temp = getSquare();
		FactoryMap f = AuxiliaryFunction.findFactory(temp, factoryLayer);
		SwitchMap sw = AuxiliaryFunction.findSwitch(temp, this.switchLayer);
		TerminalMap t = AuxiliaryFunction.findTerminal(temp, terminalLayer);
		/* goi manager de thuc thi quan ly viec MapCreationManager.DELETE */
		MapDeleting deleteManager = new MapDeleting(this);

		if (f != null) {
			// thuc hien xoa cac switch di qua nha may va nha may
			deleteManager.deleteFactory(f);
		} else if (t != null) {
			// thuc hien xoa cac MapCreationManager.TERMINAL
			deleteManager.deleteTerminal(t);
		} else if (sw != null) {
			deleteManager.deleteSwitch(sw);
		} else {

			ItemMap item = AuxiliaryFunction.findItem(temp, itemMapLayer);
			if (item != null)
				deleteManager.deleteItemMap(item);
		}
		AuxiliaryFunction.showWrongSwitch(this);
		switchLayer.render();
		parent.repaint();
	}

	/**
	 * xử lý xóa toàn bộ thông tin về map đang vẽ để vẽ lại từ đầu(khi ấn vào
	 * button DELETEALL).
	 */
	private void handleMenuDeleteAll() {
		/* hien thong bao ve viec xoa het */
		String message = "neu an nut nay ban se phai ve lai tu dau ";
		int option = JOptionPane.showConfirmDialog(parent, message);
		switch (option) {
			case JOptionPane.OK_OPTION :
				deleteAll();
				break;
			default :
				break;
		}

	}

	/**
	 * làm rỗng các Layer vẽ.
	 */
	private void deleteAll() {
		itemMapLayer.emptyLayer();
		terminalLayer.emptyLayer();
		factoryLayer.emptyLayer();
		switchLayer.emptyLayer();
		squareCovedList.clear();
		parent.repaint();
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO something

	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoveLayer.emptyLayer();
		mouseMoveLayer.render();
		parent.repaint();
		if (control == MapCreationManager.DEFAULT) {
			mX = e.getX();
			mY = e.getY();
			pointClicked = new Point(mX, mY);
			/* neu da drag qua roi thi moi thay active */
			Point temp = getSquare();
			/* kiem tra xem co an vao menu ko */
			if (temp != null) {
				if ((!((active.x == temp.x) && (active.y == temp.y)))
						&& (active != null)) {
					last = active;
					active = temp;
					handleDragged();
				}
			}
		}
	}

	/**
	 * xử lý drag chuột.
	 */
	private void handleDragged() {
		/* kiem tra xem co drag dung khong */
		if ((trueDrag())
				&& (AuxiliaryFunction.findFactory(active, factoryLayer) == null)) {
			dragDir = calDragDir();// xac dinh huong cua drag
			if (AuxiliaryFunction.findFactory(last, factoryLayer) != null) {
				handleFactoryDrag();
			}

			else if (AuxiliaryFunction.findSwitch(last, this.switchLayer) != null)
				handleSwitchDrag();
		}
	}

	/**
	 * xử lý khi last là một switch.
	 */
	private void handleSwitchDrag() {
		/* neu last chua co huong nay thi them vao, neu last chua co huong nao */
		/* thi dat lam currentDir */
		TerminalMap ter = AuxiliaryFunction.findTerminal(active, terminalLayer);
		SwitchMap lastSwitch = AuxiliaryFunction.findSwitch(last,
				this.switchLayer);
		if (ter != null)
			lastSwitch.addDirection(dragDir);
		else {
			if (lastSwitch.getListDirection().size() == 0) {
				/* neu ma huong di nguoc lai huong cua */
				SwitchMap sw = AuxiliaryFunction.findSwitch(active,
						this.switchLayer);
				if ((sw != null) && (sw.getListDirection().size() > 0)) {
					// truong hop active chua co huong
					// check dieu kien nguoc huong voi tat ca huong trong sw
					boolean check = true;
					for (Direction i : sw.getListDirection())
						if (AuxiliaryFunction.checkAntiDirection(i, dragDir)) {
							check = false;
							break;
						}
					if (check) {
						lastSwitch.setCurrentDir(0);
						lastSwitch.addDirection(dragDir);
					}
				} else {
					lastSwitch.setCurrentDir(0);
					lastSwitch.addDirection(dragDir);
				}

			} else if (!lastSwitch.containsDirection(dragDir))
				if (AuxiliaryFunction.findSwitch(active, this.switchLayer) != null) {
					SwitchMap sw = AuxiliaryFunction.findSwitch(active,
							this.switchLayer);

					boolean check = true;
					for (Direction i : sw.getListDirection())
						if (AuxiliaryFunction.checkAntiDirection(i, dragDir)) {
							check = false;
							break;
						}
					if (check) {
						lastSwitch.setCurrentDir(0);
						lastSwitch.addDirection(dragDir);
					}
					switchLayer.render();

				} else {
					lastSwitch.addDirection(dragDir);
				}
			/* neu ma o active dang o chua co switch thi them vao */
			if (AuxiliaryFunction.findSwitch(active, this.switchLayer) == null) {
				SwitchMap sw = new SwitchMap(new Point(active.x, active.y),
						MapCreationManager.SQUARE_SIDE,
						MapCreationManager.SQUARE_SIDE);

				switchLayer.addDrawable(sw);
			}
			/* khi active TERMINAL thi van dat huong cho last */
			if ((AuxiliaryFunction.findTerminal(active, terminalLayer) != null)
					&& (!lastSwitch.containsDirection(dragDir))) {
				lastSwitch.addDirection(dragDir);
			}
		}

		switchLayer.render();
		AuxiliaryFunction.showWrongSwitch(this);
		parent.repaint();
	}
	/**
	 * xử lý khi last là ô vuông chứa Factory.
	 */
	private void handleFactoryDrag() {

		/* kiem tra xem nha may co huong chua neu chua co thi dat huong. */
		FactoryMap f = AuxiliaryFunction.findFactory(last, factoryLayer);
		ItemMap ter = AuxiliaryFunction.findItem(last, itemMapLayer);
		Direction imageDirection = null;
		imageDirection = ItemImage.getDirection(ter.getImage());
		/* kiem tra xem huong cua anh va huong Drag the nao */
		if (imageDirection != dragDir) {
			if (f.getDirection() == null) {
				f.setDirection(dragDir);
				/* neu active la o trong thi them switch vao */
				if ((AuxiliaryFunction.isEmptySquare(active, switchLayer,
						factoryLayer, terminalLayer))) {
					// truong hop chua co doi tuong nao thi them switch vao
					SwitchMap sw = new SwitchMap(new Point(active.x, active.y),
							MapCreationManager.SQUARE_SIDE,
							MapCreationManager.SQUARE_SIDE);
					switchLayer.addDrawable(sw);
				}

				switchLayer.render();
			}

			AuxiliaryFunction.showWrongFactory(this);
			AuxiliaryFunction.showWrongSwitch(this);
			parent.repaint();

		}
	}

	/**
	 * kiểm tra xem drag chuột có hợp lệ không drag hợp lệ khi ô active là các ô
	 * kề cạnh với last.
	 * 
	 * @return true nếu drag đúng
	 */
	private boolean trueDrag() {
		Point upPoint = new Point(last.x - MapCreationManager.SQUARE_SIDE,
				last.y);
		Point downPoint = new Point(last.x + MapCreationManager.SQUARE_SIDE,
				last.y);
		Point rightPoint = new Point(last.x, last.y
				+ MapCreationManager.SQUARE_SIDE);
		Point leftPoint = new Point(last.x, last.y
				- MapCreationManager.SQUARE_SIDE);
		if (AuxiliaryFunction.checkPoint(upPoint, active))
			return true;
		else if (AuxiliaryFunction.checkPoint(downPoint, active))
			return true;
		else if (AuxiliaryFunction.checkPoint(rightPoint, active))
			return true;
		else if (AuxiliaryFunction.checkPoint(leftPoint, active))
			return true;
		return false;
	}

	/**
	 * Tính hướng di chuột.
	 * 
	 * @return hướng di chuột
	 */
	private Direction calDragDir() {
		Direction d = null;
		if (active.x == last.x) {
			if (active.y > last.y)

				d = Direction.RIGHT;
			else
				d = Direction.LEFT;
		} else if (active.y == last.y) {
			if (active.x > last.x)
				d = Direction.DOWN;
			else
				d = Direction.UP;
		}
		return d;
	}

	/** item hiện tại. */
	private MenuItem instantItem;

	/** button hiện tại. */
	private DrawingButton instantButton;

	/*
	 * (non - Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		/* khi load xong map move moi co hieu luc */
		if (loaded) {
			mX = e.getX();
			mY = e.getY();
			pointClicked = new Point(mX, mY);

			Point temp = getSquare();
			/* khi khong co menu nao show ra thi hover chuot moi co hieu luc */

			for (DrawLayer i : itemListLayer) {
				MenuItem item = (MenuItem) i.getClickedObj(pointClicked);
				if (item != null) {
					resetMenuItemHover();
					item.setHoverState(true);
					i.render();
					parent.repaint();
					break;
				}

			}

			if (temp == null) {
				mouseMoveLayer.emptyLayer();
				mouseMoveLayer.render();
				parent.repaint();
				/* khi hover qua cac button */
				DrawingButton b = (DrawingButton) menuLayer
						.getClickedObj(pointClicked);
				if (b != null) {
					resetButtonHover();
					b.setHoverState(true);
					menuLayer.render();
				} else {
					resetButtonHover();
					menuLayer.render();
					parent.repaint();
				}

			} else {
				resetButtonHover();
				menuLayer.render();
				if (control == MapCreationManager.DEFAULT) {
					// xu li di chuyen khi control = MapCreationManager.DEFAULT
					last = active;
					active = temp;
					handleMoseMoveSwitch(temp);
				} else if ((control != MapCreationManager.DEFAULT)
						&& (instantItem != null)) {
					// xu li ve cac item chon
					last = active;
					active = temp;
					if ((mouseMoveLayer.getListDrawable().size() == 0)
							&& (instantButton instanceof ButtonForDraw)) {
						int side = MapCreationManager.SQUARE_SIDE;
						ItemMap item = ((ButtonForDraw) instantButton)
								.generateItem(temp, side);
						item.calculateValidation(this);
						mouseMoveLayer.addDrawable(item);
						mouseMoveLayer.render();
					} else {
						/* kiem tra xem da sang o moi chua */
						if ((!AuxiliaryFunction.checkPoint(active, last))
								&& ((instantButton instanceof ButtonForDraw))) {
							mouseMoveLayer.emptyLayer();
							int side = MapCreationManager.SQUARE_SIDE;
							ItemMap result = ((ButtonForDraw) instantButton)
									.generateItem(active, side);
							result.calculateValidation(this);
							mouseMoveLayer.addDrawable(result);
							mouseMoveLayer.render();
						}
					}
				}
			}
			menuLayer.render();
			parent.repaint();
		}

	}

	/**
	 * xử lý hiển thị trạng thái của ô vuông có thể vẽ drag chuột từ ô vuông này
	 * hay không.
	 * 
	 * @param - position vị trí ô vuông chuột đi vào
	 */
	private void handleMoseMoveSwitch(Point position) {
		MouseOverSquare temp = new MouseOverSquare(position,
				MapCreationManager.SQUARE_SIDE, MapCreationManager.SQUARE_SIDE);
		SwitchMap sw = AuxiliaryFunction.findSwitch(position, switchLayer);
		FactoryMap f = AuxiliaryFunction.findFactory(position, factoryLayer);
		boolean factoryCondition = false;
		if ((f != null) && (f.getDirection() == null))
			factoryCondition = true;
		if (((sw != null) && (AuxiliaryFunction.countNeighbor(sw, this) < 4))
				|| (factoryCondition))
			temp.setValidation(true);
		if (mouseMoveLayer.getListDrawable().size() == 0)
			mouseMoveLayer.addDrawable(temp);
		else if (!AuxiliaryFunction.checkPoint(last, position)) {
			mouseMoveLayer.emptyLayer();
			mouseMoveLayer.addDrawable(temp);
		}

		mouseMoveLayer.render();
	}

	/*
	 * (non - Javadoc)
	 * 
	 * @see com.btl.GameBoard.GameState#gameRender(java.awt.Graphics)
	 */
	@Override
	public void gameRender(Graphics g) {

		layerCluster.emptyLayer();

		addListToLayerCluster(terminalLayer.getListDrawable());
		addListToLayerCluster(switchLayer.getListDrawable());
		addListToLayerCluster(factoryLayer.getListDrawable());
		/* add nhung item khong phai square */
		for (Drawable i : itemMapLayer.getListDrawable())
			if (!(i instanceof SquareMap))
				layerCluster.addDrawable(i);
		/* thuc hien tron de ve theo luat xa gan */
		layerCluster.sort();
		layerCluster.render();

		/* ve cac thanh phan vao bufferImage trc khi ve vao panel */
		Graphics g1 = bufferImage.getGraphics();
		/* ve bgImage bao gom luoi o vuong va mau nen Bgmenu */
		g1.drawImage(bgImage, 0, 0, null);
		/* ve cac layer vao */

		DrawLayer squareLayer = new DrawLayer(parent.width, parent.height);
		for (Drawable i : itemMapLayer.getListDrawable()) {
			if (i instanceof SquareMap)
				squareLayer.addDrawable(i);
		}
		squareLayer.render();
		g1.drawImage(squareLayer.getLayer(), 0, 0, null);
		/* ve munu squareLayer de ve menu water, grass, soil */
		g1.drawImage(layerCluster.getLayer(), 0, 0, null);
		/* dau tien la cac square lop trong cung */
		g1.drawImage(menuLayer.getLayer(), 0, 0, null);
		g1.drawImage(timeLayer.getLayer(), 0, 0, null);
		g1.drawImage(mouseMoveLayer.getLayer(), 0, 0, null);

		/* hien thi cac menu item */
		for (Layer i : itemListLayer)
			g1.drawImage(i.getLayer(), 0, 0, null);

		g1.dispose();

		g.drawImage(bufferImage, 0, 0, null);

	}

	/**
	 * Chèn các button vào giao diện.
	 */
	public void fillMenu() {
		/* add cac doi tuong cua menu vao layer */
		for (ButtonForDraw i : graphicButtonList)
			menuLayer.addDrawable(i);
		for (ButtonForHandle i : handleButtonList)
			menuLayer.addDrawable(i);
		menuLayer.render();
	}

	/**
	 * Để cac button về trạng thái chưa được ấn.
	 */
	private void resetMenu() {
		for (ButtonForDraw i : graphicButtonList) {
			i.normalRender();
			i.hideMenu();
		}
		for (ButtonForHandle i : handleButtonList) {
			i.normalRender();
		}
	}

	/**
	 * Cho các button về trạng thái chưa có chuột đi vào.
	 */
	private void resetButtonHover() {
		for (ButtonForDraw i : graphicButtonList)
			i.setHoverState(false);

		for (ButtonForHandle i : handleButtonList)
			i.setHoverState(false);
	}

	/**
	 * chuyển trạng thái hover cho các itemMenu như chưa được hover chuột vào.
	 */
	private void resetMenuItemHover() {
		for (DrawLayer i : itemListLayer) {
			for (Drawable item : i.getListDrawable()) {
				MenuItem temp = (MenuItem) item;
				temp.setHoverState(false);
			}
		}
	}

	/**
	 * vẽ lưới ô vuông vào ảnh nền.
	 */
	private void iniBgImage() {

		Graphics g1;
		g1 = bgImage.getGraphics();

		BufferedImage img = OtherImage.BG;
		g1.drawImage(img, 0, 0, parent.width, parent.height, null);
		g1.setColor(Color.black);
		/* ve luoi o vuong */
		for (int i = logicHeightN / MapCreationManager.SQUARE_SIDE - 1; i <= logicHeightP
				/ MapCreationManager.SQUARE_SIDE + 1; i++) {
			/* doi tu toa do logic sang toa do thuc */
			Point temp1 = ConversionFunction.logicToReal(new Point(0,
					MapCreationManager.SQUARE_SIDE * i));
			Point temp2 = ConversionFunction.logicToReal(new Point(logicWidth,
					MapCreationManager.SQUARE_SIDE * i));
			g1.drawLine(temp1.x, temp1.y, temp2.x, temp2.y);
		}

		for (int i = 0; i <= logicWidth / MapCreationManager.SQUARE_SIDE + 1; i++) {

			Point temp1 = ConversionFunction.logicToReal(new Point(
					MapCreationManager.SQUARE_SIDE * i, logicHeightP));
			Point temp2 = ConversionFunction.logicToReal(new Point(
					MapCreationManager.SQUARE_SIDE * i, logicHeightN));
			g1.drawLine(temp1.x, temp1.y, temp2.x, temp2.y);
		}
		/* ve mau xanh nen cho cac menu */
		g1.setColor(bgMenuColor);
		g1.fillRect(0, 0, parent.width, MapCreationManager.ICON_WIDTH);

		g1.dispose();
	}

	/** tọa độ chuột theo trục x. */
	private int mX;

	/** tọa độc chuột theo trục y. */
	private int mY;

	/**
	 * Tìm ô vuông chứa chuột.
	 * 
	 * @return điểm góc trái trên cùng của ô vuông trong lưới ô vuông ấn vào
	 */
	private Point getSquare() {
		/* can phai luu y khi ma kich chuot vao phan menu thi return null */
		if ((mX >= 0) && (mX <= parent.width) && (mY >= 0)
				&& (mY <= 2 * MapCreationManager.MENU_HEIGHT)
				|| (!checkMenuHidden())) {
			return null;
		}
		/* khi chua kich chuot vao thi bat dau xac dinh */
		Point temp = ConversionFunction.realToLogic(new Point(mX, mY));
		int ix = 0;
		int iy = 0;
		for (int i = 0; i <= logicWidth / MapCreationManager.SQUARE_SIDE + 1; i++) {
			if ((MapCreationManager.SQUARE_SIDE * i <= temp.x)
					&& (MapCreationManager.SQUARE_SIDE * i
							+ MapCreationManager.SQUARE_SIDE > temp.x)) {
				ix = i;
				break;
			}
		}
		for (int i = logicHeightN / MapCreationManager.SQUARE_SIDE - 1; i < logicHeightP
				/ MapCreationManager.SQUARE_SIDE + 1; i++) {
			if ((MapCreationManager.SQUARE_SIDE * i <= temp.y)
					&& (MapCreationManager.SQUARE_SIDE * i
							+ MapCreationManager.SQUARE_SIDE > temp.y)) {
				iy = i + 1;
				break;
			}
		}

		return new Point(ix * MapCreationManager.SQUARE_SIDE, iy
				* MapCreationManager.SQUARE_SIDE);
	}

	/**
	 * load dữ liệu từ file map muốn Edit.
	 * 
	 * @param fileName
	 *            - đường dẫn tới file map
	 */
	private void loadElementFromFile(String fileName) {
		ModelMap model = ModelMap.createMap(fileName);
		ArrayList<ModelSwitch> switchFromFile = model.getListSwitch();
		ArrayList<SwitchMap> temp = AuxiliaryFunction.loadSwitch(
				switchFromFile, MapCreationManager.SQUARE_SIDE);
		/* load switchMap vao switchLayer */
		for (SwitchMap i : temp)
			switchLayer.addDrawable(i);

		ArrayList<ModelFactory> factoryFromFile = model.getListFactory();
		ArrayList<FactoryMap> temp2 = AuxiliaryFunction.loadFactory(
				factoryFromFile, MapCreationManager.SQUARE_SIDE);
		/* load factoryMap vao factorylayer */
		for (FactoryMap i : temp2)
			factoryLayer.addDrawable(i);

		ArrayList<ModelTerminal> terminalFromFile = model.getListTerminal();
		ArrayList<TerminalMap> temp3 = AuxiliaryFunction.loadTerminal(
				terminalFromFile, MapCreationManager.SQUARE_SIDE);
		/* load terminalMap cao termianlLayer */
		for (TerminalMap i : temp3)
			terminalLayer.addDrawable(i);
		ArrayList<ModelItem> itemList = model.getListItem();
		/* lay ra cac terminalIcon tu listItem */
		AuxiliaryFunction.loadDrawingLayer(itemMapLayer, itemList,
				MapCreationManager.SQUARE_SIDE);
		/* luu nhung o vuong bi phu lai */
		for (Drawable i : factoryLayer.getListDrawable()) {
			FactoryMap f = (FactoryMap) i;
			ItemMap item = AuxiliaryFunction.findItem(f.getPosition(),
					itemMapLayer);
			if (item != null) {
				for (Point j : ItemImage.getSquareCovered(item.getImage(),
						f.getPosition(), MapCreationManager.SQUARE_SIDE))
					squareCovedList.add(j);
			}

		}
		for (Drawable i : terminalLayer.getListDrawable()) {
			TerminalMap f = (TerminalMap) i;
			ItemMap item = AuxiliaryFunction.findItem(f.getPosition(),
					itemMapLayer);
			if (item != null) {
				for (Point j : ItemImage.getSquareCovered(item.getImage(),
						f.getPosition(), MapCreationManager.SQUARE_SIDE))
					squareCovedList.add(j);
			}

		}

	}

	/**
	 * tìm Item mà ấn chuột vào (ví dụ cây cối, cỏ, xe tải ...)
	 * 
	 * @param p
	 *            - vị trí cần tìm
	 * @return MenuItem của Button mà tại vị trí chuột
	 */
	private MenuItem getItemClicked(Point p) {
		for (int i = 0; i < itemListLayer.length; i++) {
			Drawable temp = itemListLayer[i].getClickedObj(p);
			if (temp != null)
				return (MenuItem) temp;
		}
		return null;
	}

	/**
	 * Kiểm tra xem có Button nào có Menu tương ứng của nó đươc show ra hay
	 * không.
	 * 
	 * @return true nếu tất các menu của các Button đêu ẩn
	 */
	private boolean checkMenuHidden() {
		for (ButtonForDraw i : graphicButtonList) {
			if (!i.getItemMenuState())
				return false;
		}
		return true;
	}

	/**
	 * Hàm xử lý file
	 * 
	 * @return true nếu map không bị lỗi
	 */
	// /////////// them doan nay vao
	private boolean handleMenuSave() {
		/* kiem tra xem co switch nao bi loi hay khong */
		ArrayList<SwitchMap> wrongSwitch = AuxiliaryFunction.falseSwitch(this);
		ArrayList<FactoryMap> wrongFactory = AuxiliaryFunction
				.isolatedFactory(this);
		if ((wrongSwitch.size() == 0) && (wrongFactory.size() == 0)) {
			try {
				/* tao ao giac an cho nguoi dung */
				Thread.sleep(100);
				System.out.println("da thuc hien sleep xong");
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			MapSaving factoryEngine = new MapSaving(this, fileName);
			fileName = factoryEngine.getFileName();
			return true;
		} else { // khi ma co switch bi loi, hien thi no nen
			String message = "Loi khong the luu vi map co "
					+ "nhung factory khong co cua ra hoac switch khong co diem den";
			JOptionPane.showMessageDialog(parent, message, "action failed", 1);
			return false;
		}
	}

	/**
	 * ham xu li Edit mot file
	 */
	// ///////////// them doan nay vao,import them vao jchooser va File
	private void handleEditMap() {
		/* hien thong bao luu file cu */
		String message = " Ban co muon luu map dang ve ? ";
		boolean check = true;
		/* neu map khong giong */
		if (!AuxiliaryFunction.isEmpty(this)) {
			int result = JOptionPane.showConfirmDialog(parent, message);
			if (result == JOptionPane.OK_OPTION)
				check = handleMenuSave();
		}
		if (check) {
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(parent);
			File selectedFile = chooser.getSelectedFile();
			if (selectedFile != null) {
				fileName = selectedFile.getPath();
				/* thuc hien doc file */
				deleteAll();
				loadElementFromFile(fileName);

				MapRecovery maprecovery = MapRecovery.createMapRecovery(
						switchLayer, factoryLayer, terminalLayer,
						MapCreationManager.SQUARE_SIDE,
						MapCreationManager.SQUARE_SIDE, this);
				switchLayer = maprecovery.getSwitchLayer();
				factoryLayer = maprecovery.getFactoryLayer();
				terminalLayer = maprecovery.getTerminalLayer();
				switchLayer.render();
				AuxiliaryFunction.showWrongFactory(this);
				AuxiliaryFunction.showWrongSwitch(this);
				parent.repaint();
			}
		}
	}

	/**
	 * Gets the switch layer.
	 * 
	 * @return the switch layer
	 */
	public DrawLayer getSwitchLayer() {
		return switchLayer;
	}

	/**
	 * Gets the side.
	 * 
	 * @return the side
	 */
	public int getSide() {
		return MapCreationManager.SQUARE_SIDE;
	}

	/**
	 * Gets the terminallayer.
	 * 
	 * @return the terminallayer
	 */
	public DrawLayer getTerminallayer() {
		return terminalLayer;
	}

	/**
	 * Gets the factorylayer.
	 * 
	 * @return the factorylayer
	 */
	public DrawLayer getFactorylayer() {
		return factoryLayer;
	}

	/**
	 * Gets the minute.
	 * 
	 * @return the minute
	 */
	public int getMinute() {
		return minute.getTime();
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
	 * Gets the second.
	 * 
	 * @return the second
	 */
	public int getSecond() {
		return second.getTime();
	}

	/**
	 * Gets the square coved list.
	 * 
	 * @return the square coved list
	 */
	public ArrayList<Point> getSquareCovedList() {
		return squareCovedList;
	}
}
