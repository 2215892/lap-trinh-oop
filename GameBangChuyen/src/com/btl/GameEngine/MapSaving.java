package com.btl.GameEngine;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.btl.GameElements.mapstate.FactoryMap;
import com.btl.GameElements.mapstate.ItemMap;
import com.btl.GameElements.mapstate.MapCreation;
import com.btl.GameElements.mapstate.SwitchMap;
import com.btl.GameElements.mapstate.TerminalMap;
import com.btl.GameElements.playstate.DrawLayer;
import com.btl.Model.AuxiliaryFunction;
import com.btl.Model.ConversionFunction;
import com.btl.Model.Direction;

// TODO: Auto-generated Javadoc
// TODO: Auto - generated Javadoc

/**
 * class này phục vụ cho việc lưu trữ thông tin map đang vẽ, thực hiện các thuật
 * toán rút gọn thông tin lưu trữ.
 * 
 * @author mai tien khai
 */
public class MapSaving {

	/** The đường dẫn file. */
	private String fileName;

	/** The switch layer của map. */
	private DrawLayer switchLayer;

	/** The terminal layer của map. */
	private DrawLayer terminalLayer;

	/** The factory layer của map. */
	private DrawLayer factoryLayer;

	/** The item map layer của map. */
	private DrawLayer itemMapLayer;

	/** Đơn vị (= side/SQUARE_SIDE). */
	private int unit;

	/** đối tượng để chọn đường dẫn cho file map. */
	private JFileChooser chooser;

	/** file để save. */
	private File fileSave;

	/** The terminal trap của map ( khi box vào đây sẽ mất điểm). */
	private ArrayList<TerminalMap> terminalTrap;

	/** Map cần lưu. */
	private MapCreation mapCreation;

	/**
	 * Khơi tạo đối tượng thực hiên công việc save.
	 * 
	 * @param map
	 *            the map
	 * @param fileName
	 *            the file name
	 */
	public MapSaving(MapCreation map, String fileName) {

		this.switchLayer = map.getSwitchLayer();
		this.terminalLayer = map.getTerminallayer();
		this.factoryLayer = map.getFactorylayer();
		this.itemMapLayer = map.getItemMapLayer();
		this.fileName = fileName;
		this.unit = map.getSide();
		this.mapCreation = map;
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(ConversionFunction
				.getCurrentDirectory() + "custom map//"));
		terminalTrap = new ArrayList<TerminalMap>();
		/* thuc hien duyet cac switch khong co huong thanh cac trap */
		handleTrap();
		try {
			handle();
		} catch (TransformerException e) {

			e.printStackTrace();
		}
	}

	/**
	 * thực hiện lưu các trap(là các switch không có hướng).
	 */
	private void handleTrap() {
		for (Drawable i : switchLayer.getListDrawable()) {
			SwitchMap temp = (SwitchMap) i;
			if (temp.getListDirection().size() == 0) {
				TerminalMap t = new TerminalMap(temp.getPosition(),
						mapCreation.getSide());
				t.setType(0);
				terminalTrap.add(t);
			}
		}

	}

	/**
	 * thực hiện việc lưu vào file.
	 * 
	 * @throws TransformerException
	 *             the transformer exception
	 */
	private void handle() throws TransformerException {
		if (fileName == null) {// neu chua dc save lan nao thi hien dialog de
			// save
			chooser.showSaveDialog(null);
			fileSave = chooser.getSelectedFile();
			if (fileSave != null)
				fileName = fileSave.getPath();
		}
		if (fileName != null) {

			// thuc hien luu file o day

			try {

				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder;
				docBuilder = docFactory.newDocumentBuilder();
				// root elements
				/* thêm thẻ SaveFile vào */

				Document doc = docBuilder.newDocument();
				Element map = doc.createElement("map");
				doc.appendChild(map);
				/* chen cac doi tuong factory vao trong the factory */
				for (Drawable i : factoryLayer.getListDrawable()) {
					FactoryMap temp = (FactoryMap) i;
					if (temp.getDirection() != null) {
						Element factory = doc.createElement("factory");
						map.appendChild(factory);
						/* the <x> va <y> */
						Element x = doc.createElement("x");
						Element y = doc.createElement("y");
						x.appendChild(doc.createTextNode(""
								+ temp.getPosition().x / unit));
						y.appendChild(doc.createTextNode(""
								+ temp.getPosition().y / unit));
						factory.appendChild(x);
						factory.appendChild(y);
						/** add the direction */
						Element direction = doc.createElement("direction");
						direction.appendChild(doc.createTextNode(""
								+ temp.getDirection()));
						factory.appendChild(direction);
					}
				}
				/* chen cac terminal */

				for (TerminalMap i : terminalTrap) {
					Element terminal = doc.createElement("terminal");
					map.appendChild(terminal);
					/* toa do cho cac terminal */
					Element x = doc.createElement("x");
					Element y = doc.createElement("y");
					x.appendChild(doc.createTextNode("" + i.getPosition().x
							/ unit));
					y.appendChild(doc.createTextNode("" + i.getPosition().y
							/ unit));
					terminal.appendChild(x);
					terminal.appendChild(y);
					Element type = doc.createElement("type");
					type.appendChild(doc.createTextNode("" + i.getType()));
					terminal.appendChild(type);

					Element boxNumber = doc.createElement("boxnumber");
					boxNumber.appendChild(doc.createTextNode(""
							+ i.getBoxNumber()));
					terminal.appendChild(boxNumber);
				}

				for (Drawable i : terminalLayer.getListDrawable()) {
					TerminalMap temp = (TerminalMap) i;
					Element terminal = doc.createElement("terminal");
					map.appendChild(terminal);
					/* toa do cho cac terminal */
					Element x = doc.createElement("x");
					Element y = doc.createElement("y");
					x.appendChild(doc.createTextNode("" + temp.getPosition().x
							/ unit));
					y.appendChild(doc.createTextNode("" + temp.getPosition().y
							/ unit));
					terminal.appendChild(x);
					terminal.appendChild(y);

					Element type = doc.createElement("type");
					type.appendChild(doc.createTextNode("" + temp.getType()));
					terminal.appendChild(type);

					Element boxNumber = doc.createElement("boxnumber");
					boxNumber.appendChild(doc.createTextNode(""
							+ temp.getBoxNumber()));
					terminal.appendChild(boxNumber);
				}
				/* chen chen cac switch */
				for (Drawable i : switchLayer.getListDrawable()) {
					SwitchMap temp = (SwitchMap) i;
					if ((temp.getListDirection().size() != 0)
							&& ((temp.getListDirection().size() != 1) || (checkValid(temp)))) {
						Element sw = doc.createElement("switch");
						map.appendChild(sw);
						/* chen toa do cho switch */
						Element x = doc.createElement("x");
						Element y = doc.createElement("y");
						x.appendChild(doc.createTextNode(""
								+ temp.getPosition().x / unit));
						y.appendChild(doc.createTextNode(""
								+ temp.getPosition().y / unit));
						sw.appendChild(x);
						sw.appendChild(y);
						/* chen currentDireciton */
						Element currentDirection = doc
								.createElement("currentdirection");
						currentDirection.appendChild(doc.createTextNode(""
								+ temp.getCurrentDir()));
						sw.appendChild(currentDirection);
						/* chen cac direction */
						for (Direction j : temp.getListDirection()) {
							Element direction = doc.createElement("direction");
							direction.appendChild(doc.createTextNode("" + j));
							sw.appendChild(direction);
						}
					}
				}

				for (Drawable i : itemMapLayer.getListDrawable()) {
					ItemMap temp = (ItemMap) i;
					map.appendChild(insertImageLayer("itemImage", temp, doc));
				}

				/* chen the thoi gian */
				Element time = doc.createElement("time");
				map.appendChild(time);
				Element minute = doc.createElement("minute");
				minute.appendChild(doc.createTextNode(""
						+ mapCreation.getMinute()));
				Element second = doc.createElement("second");
				second.appendChild(doc.createTextNode(""
						+ mapCreation.getSecond()));
				time.appendChild(minute);
				time.appendChild(second);

				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(fileName));
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.transform(source, result);

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * tạo thẻ tag cho đối tượng itemMap.
	 * 
	 * @param tagName
	 *            the tag name
	 * @param temp
	 *            the temp
	 * @param doc
	 *            the doc
	 * @return một - ELement trong XML
	 */
	private Element insertImageLayer(String tagName, ItemMap temp, Document doc) {
		Element tree = doc.createElement(tagName);
		Element x = doc.createElement("x");
		Element y = doc.createElement("y");
		x.appendChild(doc.createTextNode("" + temp.getPosition().x / unit));
		y.appendChild(doc.createTextNode("" + temp.getPosition().y / unit));
		tree.appendChild(x);
		tree.appendChild(y);
		/* loai anh vi du cay, square ... */
		Element type = doc.createElement("type");
		type.appendChild(doc.createTextNode("" + temp.getImageType()));
		tree.appendChild(type);
		/* id cua anh can load */
		Element id = doc.createElement("id");
		id.appendChild(doc.createTextNode("" + temp.getImageId()));
		tree.appendChild(id);
		return tree;
	}

	/**
	 * kiem tra xem mot switch co duoc luu hay khong.
	 * 
	 * @param sw
	 *            the sw
	 * @return true neu ma dc save
	 */
	private boolean checkValid(SwitchMap sw) {
		SwitchMap tg;
		FactoryMap f;
		// lay currentDir cua sw
		int cL = sw.getCurrentDir();
		// tim switch phia tren no
		tg = AuxiliaryFunction.upNeighbor(sw, this.switchLayer);
		f = AuxiliaryFunction.findFactory(new Point(sw.getPosition().x - unit,
				sw.getPosition().y), factoryLayer);
		if ((tg != null) && (tg.getListDirection().size() > 0)) {

			/* so sanh huong cua hai cai */
			for (Direction i : tg.getListDirection()) {
				if ((i != sw.getListDirection().get(cL))
						&& (i == Direction.DOWN))
					return true;
			}

		}
		if (f != null) {
			if (f.getDirection() != sw.getListDirection().get(cL))
				return true;
		}
		/* tim switch phia duoi no' */
		tg = AuxiliaryFunction.downNeighbor(sw, this.switchLayer);
		f = AuxiliaryFunction.findFactory(new Point(sw.getPosition().x + unit,
				sw.getPosition().y), factoryLayer);
		if ((tg != null) && (tg.getListDirection().size() > 0)) {

			for (Direction i : tg.getListDirection()) {
				if ((i != sw.getListDirection().get(cL)) && (i == Direction.UP))
					return true;
			}
		}
		if (f != null) {
			if (f.getDirection() != sw.getListDirection().get(cL))
				return true;
		}
		/* tim swicth ben phai no */
		tg = AuxiliaryFunction.rightNeighbor(sw, this.switchLayer);
		f = AuxiliaryFunction.findFactory(
				new Point(sw.getPosition().x, sw.getPosition().y + unit),
				factoryLayer);
		if ((tg != null) && (tg.getListDirection().size() > 0)) {
			// khi ma neighbor trc no co huong(tuc la khong phai la trap)

			for (Direction i : tg.getListDirection()) {
				if ((i != sw.getListDirection().get(cL))
						&& (i == Direction.LEFT))
					return true;
			}

		}
		if (f != null) {
			if (f.getDirection() != sw.getListDirection().get(cL))
				return true;
		}
		/* tim switch ben trai no */
		tg = AuxiliaryFunction.leftNeighbor(sw, this.switchLayer);
		f = AuxiliaryFunction.findFactory(
				new Point(sw.getPosition().x, sw.getPosition().y - unit),
				factoryLayer);
		if ((tg != null) && (tg.getListDirection().size() > 0)) {

			for (Direction i : tg.getListDirection()) {
				if ((i != sw.getListDirection().get(cL))
						&& (i == Direction.RIGHT))
					return true;
			}

		}
		if (f != null) {
			if (f.getDirection() != sw.getListDirection().get(cL))
				return true;
		}
		return false;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

}
