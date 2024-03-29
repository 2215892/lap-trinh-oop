package com.oop.model;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.oop.data.XmlReader;

/**
 * The Class ModelMap.
 */
public class ModelMap extends XmlReader {

	/**
	 * Creates the map.
	 * 
	 * @param fileDir
	 *            the file dir
	 * @return the model map
	 */
	public static ModelMap createMap(final String fileDir) {
		ModelMap map = new ModelMap();
		try {
			if (map.loadMap(fileDir)) {
				return map;
			} else
				return null;
		} catch (Exception e) {
			return null;
		}
	}
	private ArrayList<ModelFactory> listFactory;
	private ArrayList<ModelItem> listItem;
	private ArrayList<ModelSwitch> listSwitch;

	private ArrayList<ModelTerminal> listTerminal;

	private int minute, second;

	private ModelMap() {
		this.listFactory = new ArrayList<ModelFactory>();
		this.listTerminal = new ArrayList<ModelTerminal>();
		this.listSwitch = new ArrayList<ModelSwitch>();
		this.listItem = new ArrayList<ModelItem>();

	}

	/**
	 * Gets the list factory.
	 * 
	 * @return the list factory
	 */
	public ArrayList<ModelFactory> getListFactory() {
		return listFactory;
	}

	/**
	 * Gets the list item.
	 * 
	 * @return the list item
	 */
	public ArrayList<ModelItem> getListItem() {
		return listItem;
	}

	/**
	 * Gets the list switch.
	 * 
	 * @return the list switch
	 */
	public ArrayList<ModelSwitch> getListSwitch() {
		return listSwitch;
	}

	/**
	 * Gets the list terminal.
	 * 
	 * @return the list terminal
	 */
	public ArrayList<ModelTerminal> getListTerminal() {
		return listTerminal;
	}

	/**
	 * Gets the minute.
	 * 
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Gets the second.
	 * 
	 * @return the second
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * Sets the list factory.
	 * 
	 * @param listFactory
	 *            the new list factory
	 */
	public void setListFactory(ArrayList<ModelFactory> listFactory) {
		this.listFactory = listFactory;
	}

	/**
	 * Sets the list item.
	 * 
	 * @param listItem
	 *            the new list item
	 */
	public void setListItem(ArrayList<ModelItem> listItem) {
		this.listItem = listItem;
	}

	/**
	 * Sets the list switch.
	 * 
	 * @param listSwitch
	 *            the new list switch
	 */
	public void setListSwitch(ArrayList<ModelSwitch> listSwitch) {
		this.listSwitch = listSwitch;
	}

	/**
	 * Sets the list terminal.
	 * 
	 * @param listTerminal
	 *            the new list terminal
	 */
	public void setListTerminal(ArrayList<ModelTerminal> listTerminal) {
		this.listTerminal = listTerminal;
	}

	/**
	 * Sets the minute.
	 * 
	 * @param minute
	 *            the new minute
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}

	/**
	 * Sets the second.
	 * 
	 * @param second
	 *            the new second
	 */
	public void setSecond(int second) {
		this.second = second;
	}

	private void getFactories(NodeList nlFactory) {
		if (nlFactory != null && nlFactory.getLength() > 0) {
			for (int i = 0; i < nlFactory.getLength(); i++) {

				// get the factory element
				Element el = (Element) nlFactory.item(i);

				// get the ModelFactory object
				ModelFactory e = getFactory(el);

				// add it to list
				listFactory.add(e);
			}
		}
	}

	private ModelFactory getFactory(Element e) {
		int x = getIntValue(e, "x");
		int y = getIntValue(e, "y");
		Direction d = string2Direction(getTextValue(e, "direction"));

		return new ModelFactory(new Point(x, y), d);
	}
	private ModelItem getItem(Element ele) {

		int x = getIntValue(ele, "x");
		int y = getIntValue(ele, "y");

		ModelItem item = new ModelItem();
		item.setPosition(new Point(x, y));
		item.setId(getIntValue(ele, "id"));
		item.setType(getIntValue(ele, "type"));

		return item;
	}
	private void getItems(NodeList nlItem) {
		if (nlItem != null && nlItem.getLength() > 0) {
			for (int i = 0; i < nlItem.getLength(); i++) {

				// get the factory element
				Element el = (Element) nlItem.item(i);

				// get the ModelTerminal object
				ModelItem e = getItem(el);

				// add it to list
				listItem.add(e);
			}
		}
	}

	private ModelSwitch getSwitch(Element ele) {
		int x = getIntValue(ele, "x");
		int y = getIntValue(ele, "y");

		ModelSwitch mSwitch = new ModelSwitch(new Point(x, y));

		mSwitch.setCurrentDir(getIntValue(ele, "currentdirection"));

		NodeList nl = ele.getElementsByTagName("direction");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); ++i) {
				Element el = (Element) nl.item(i);
				mSwitch.addDirection(string2Direction(el.getFirstChild()
						.getNodeValue()));
			}
		}

		return mSwitch;
	}

	private void getSwitchs(NodeList nlSwitch) {
		if (nlSwitch != null && nlSwitch.getLength() > 0) {
			for (int i = 0; i < nlSwitch.getLength(); i++) {

				// get the factory element
				Element el = (Element) nlSwitch.item(i);

				// get the ModelTerminal object
				ModelSwitch e = getSwitch(el);

				// add it to list
				listSwitch.add(e);
			}
		}
	}

	private ModelTerminal getTerminal(Element ele) {
		int x = getIntValue(ele, "x");
		int y = getIntValue(ele, "y");
		ModelTerminal terminal = new ModelTerminal(new Point(x, y));
		terminal.setType(getIntValue(ele, "type"));
		terminal.setBoxCount(getIntValue(ele, "boxnumber"));

		return terminal;
	}

	private void getTerminals(NodeList nlTerminal) {
		if (nlTerminal != null && nlTerminal.getLength() > 0) {
			for (int i = 0; i < nlTerminal.getLength(); i++) {

				// get the factory element
				Element el = (Element) nlTerminal.item(i);

				// get the ModelTerminal object
				ModelTerminal e = getTerminal(el);

				// add it to list
				listTerminal.add(e);
			}
		}
	}

	private void getTime(NodeList nlTime) {
		Element ele = null;
		if (nlTime != null && nlTime.getLength() > 0) {
			ele = (Element) nlTime.item(0);
		}

		if (ele != null) {
			this.setMinute(getIntValue(ele, "minute"));
			setSecond(getIntValue(ele, "second"));
		}
	}

	private boolean loadMap(final String fileDir) {

		File f = new File(fileDir);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		Document dom;

		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			dom = db.parse(f);

		} catch (Exception e) {
			return false;
		}

		// get the root element
		Element docEle = dom.getDocumentElement();

		// Factory
		NodeList nlFactory = docEle.getElementsByTagName("factory");
		getFactories(nlFactory);

		// Terminal
		NodeList nlTerminal = docEle.getElementsByTagName("terminal");
		getTerminals(nlTerminal);

		// Switch
		NodeList nlSwitch = docEle.getElementsByTagName("switch");
		getSwitchs(nlSwitch);

		// item
		NodeList nlItem = docEle.getElementsByTagName("itemImage");
		getItems(nlItem);

		// time
		NodeList nlTime = docEle.getElementsByTagName("time");
		getTime(nlTime);

		return true;
	}

	private Direction string2Direction(final String str) {

		if (str.equals("UP"))
			return Direction.UP;
		if (str.equals("DOWN"))
			return Direction.DOWN;
		if (str.equals("RIGHT"))
			return Direction.RIGHT;
		if (str.equals("LEFT"))
			return Direction.LEFT;
		return null;

	}

}
