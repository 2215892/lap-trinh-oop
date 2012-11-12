package com.btl.Model;

import java.awt.Point;

import com.btl.GameElements.mapstate.FactoryMap;
import com.btl.GameElements.mapstate.MapCreation;
import com.btl.GameElements.mapstate.SwitchMap;
import com.btl.GameElements.playstate.DrawLayer;
import com.btl.GameEngine.Drawable;

// TODO: Auto-generated Javadoc
// TODO: Auto - generated Javadoc

/**
 * class này thực hiện cho việc khôi phục lại map đầy đủ từ map đã save phục vụ
 * cho việc sửa Map đã lưu vì khi lưu lại Map đã bị bỏ đi các switch trung gian.
 * 
 * @author mai tien khai
 */
public class MapRecovery {

	/** The switch layer của map. */
	private DrawLayer switchLayer;

	/** The factory layer của map. */
	private DrawLayer factoryLayer;

	/** The terminal layer của map. */
	private DrawLayer terminalLayer;

	/** chiều cao logic của switch cần khôi phục. */
	private int height;

	/** chiều rộng của switch cần khôi phục. */
	private int width;

	/** The map. */
	MapCreation map;

	/**
	 * Hàm khởi tạo đối tượng.
	 * 
	 * @param switchLayer
	 *            - switchLayer load được từ map muốn edit
	 * @param factoryLayer
	 *            - factoryLayer load được từ map muốn edit
	 * @param terminalLayer
	 *            - terminallayer load được từ map muốn edit
	 * @param map
	 *            - map để vẽ
	 */
	private MapRecovery(DrawLayer switchLayer, DrawLayer factoryLayer,
			DrawLayer terminalLayer, MapCreation map) {
		this.factoryLayer = factoryLayer;
		this.switchLayer = switchLayer;
		this.terminalLayer = terminalLayer;
		this.height = map.getSide();
		this.width = map.getSide();
		this.map = map;
		recoverFullMap();
		System.out.println("completed!");
	}

	/**
	 * Hàm khôi phục lại map.
	 */
	private void recoverFullMap() {
		/* khoi tao cac bien check */
		checkRecover = new boolean[switchLayer.getListDrawable().size()];
		for (int i = 0; i < checkRecover.length; i++)
			checkRecover[i] = false; // chua duoc duyet
		/* bat dau duyet tu cac nha may */
		for (Drawable i : factoryLayer.getListDrawable()) {
			FactoryMap f = (FactoryMap) i;
			recoverFromFactory(f);
		}
		/*
		 * sau khi thuc hien duyet xong tu cac nha may can phai duyet cac switch
		 * ma chuwa dc duyet
		 */
	}

	/**
	 * Thực hiện khôi phục từ nhà máy.
	 * 
	 * @param f
	 *            - nhà máy xuất phát
	 */
	private void recoverFromFactory(FactoryMap f) {
		/** temp la o ke voi factory theo huong factory */
		SwitchMap temp = createNeighborSwitch(f.getPosition(), f.getDirection());
		/*
		 * kiem tra xem o tiep theo co la cac terminal,factory,switch khac hay
		 * khong
		 */
		while ((AuxiliaryFunction.findSwitch(temp.getPosition(),
				this.switchLayer) == null)
				&& (AuxiliaryFunction.findTerminal(temp.getPosition(),
						this.terminalLayer) == null)
				&& (AuxiliaryFunction.findFactory(temp.getPosition(),
						this.factoryLayer) == null)) {
			/* khong vi pham trong qua trinh duyet thi them vao */
			switchLayer.addDrawable(temp);

			temp = createNeighborSwitch(temp.getPosition(), f.getDirection());
		}
		/*
		 * khi thoat ra khoi vong lap tuc la vi pham cac dieu kien trong while
		 * truong hop khi o vi pham la mot switch da co san trong switchlayer
		 */
		SwitchMap tg = AuxiliaryFunction.findSwitch(temp.getPosition(),
				this.switchLayer);
		if (tg != null) {
			/* neu chua duyet tuc la checkRocover = false */
			if (!checkRecover[AuxiliaryFunction.getIndex(tg, this.switchLayer)])
				recoverFromSwitch(tg);
		}

	}

	/** Biến kiểm tra xem switch được duyệt hay chưa. */
	private boolean[] checkRecover;

	/**
	 * Duyệt từ switch.
	 * 
	 * @param sw
	 *            - switch xuất phát
	 */
	private void recoverFromSwitch(SwitchMap sw) {
		/* duyet theo tat cac cac huong cua sw */
		checkRecover[AuxiliaryFunction.getIndex(sw, this.switchLayer)] = true;

		for (Direction i : sw.getListDirection()) {
			SwitchMap temp = createNeighborSwitch(sw.getPosition(), i);
			while ((AuxiliaryFunction.findSwitch(temp.getPosition(),
					this.switchLayer) == null)
					&& (AuxiliaryFunction.findTerminal(temp.getPosition(),
							this.terminalLayer) == null)
					&& (AuxiliaryFunction.findFactory(temp.getPosition(),
							this.factoryLayer) == null)) {
				switchLayer.addDrawable(temp);
				/* tiep tuc duyet den lan can cua no */
				temp = createNeighborSwitch(temp.getPosition(), i);
			}
			SwitchMap tg = AuxiliaryFunction.findSwitch(temp.getPosition(),
					this.switchLayer);
			if (tg != null) {
				if (!checkRecover[AuxiliaryFunction.getIndex(tg,
						this.switchLayer)])
					recoverFromSwitch(tg);
			}
		}
	}

	/**
	 * Tạo một neighbor switch ứng với hướng xác định.
	 * 
	 * @param p
	 *            - vị trí của ô cần tạo neighbor
	 * @param d
	 *            - hướng tạo neighbor
	 * @return switch cần tạo
	 */
	private SwitchMap createNeighborSwitch(Point p, Direction d) {
		SwitchMap temp;
		if (d == Direction.UP)
			temp = new SwitchMap(new Point(p.x - width, p.y), width, height);
		else if (d == Direction.DOWN)
			temp = new SwitchMap(new Point(p.x + width, p.y), width, height);
		else if (d == Direction.LEFT)
			temp = new SwitchMap(new Point(p.x, p.y - height), width, height);
		else
			temp = new SwitchMap(new Point(p.x, p.y + height), width, height);
		temp.addDirection(d);
		return temp;
	}

	/**
	 * Hàm khởi tạo tĩnh cho đổi tượng này.
	 * 
	 * @param switchLayer
	 *            - truyền vào hàm khởi tạo
	 * @param factoryLayer
	 *            - truyền vào hàm khởi tạo
	 * @param terminalLayer
	 *            - truyền vào hàm khởi tạo
	 * @param map
	 *            the map - truyền vào hàm khởi tạo
	 * @return map đối tượng khởi tạo
	 */
	public static MapRecovery createMapRecovery(DrawLayer switchLayer,
			DrawLayer factoryLayer, DrawLayer terminalLayer, MapCreation map) {
		MapRecovery temp = new MapRecovery(switchLayer, factoryLayer,
				terminalLayer, map);
		return temp;
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
	 * Gets the factory layer.
	 * 
	 * @return the factory layer
	 */
	public DrawLayer getFactoryLayer() {
		return factoryLayer;
	}

	/**
	 * Gets the terminal layer.
	 * 
	 * @return the terminal layer
	 */
	public DrawLayer getTerminalLayer() {
		return terminalLayer;
	}

}
