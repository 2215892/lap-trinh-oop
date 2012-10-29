package com.btl.Model;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ModelMap {

	private ArrayList<ModelFactory> listFactory;
	private ArrayList<ModelTerminal> listTerminal;
	private ArrayList<ModelSwitch> listSwitch;
	private ArrayList<ModelItem> listItem;

	public void setListFactory(ArrayList<ModelFactory> listFactory) {
		this.listFactory = listFactory;
	}

	public void setListTerminal(ArrayList<ModelTerminal> listTerminal) {
		this.listTerminal = listTerminal;
	}

	public void setListSwitch(ArrayList<ModelSwitch> listSwitch) {
		this.listSwitch = listSwitch;
	}

	public void setListItem(ArrayList<ModelItem> listItem) {
		this.listItem = listItem;
	}

	private int minute, second;

	private ModelMap() {
		this.listFactory = new ArrayList<ModelFactory>();
		this.listTerminal = new ArrayList<ModelTerminal>();
		this.listSwitch = new ArrayList<ModelSwitch>();
		this.listItem = new ArrayList<ModelItem>();

	}

	public ArrayList<ModelFactory> getListFactory() {
		return listFactory;
	}

	public ArrayList<ModelTerminal> getListTerminal() {
		return listTerminal;
	}

	public static ModelMap createMap(final String fileDir) {
		ModelMap map = new ModelMap();
		if (map.loadMap(fileDir)) {
			return map;
		} else
			return null;
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

	private ModelItem getItem(Element ele) {

		int x = getIntValue(ele, "x");
		int y = getIntValue(ele, "y");

		ModelItem item = new ModelItem();
		item.setPosition(new Point(x, y));
		item.setId(getIntValue(ele, "id"));
		item.setType(getIntValue(ele, "type"));

		return item;
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

	private ModelTerminal getTerminal(Element ele) {
		int x = getIntValue(ele, "x");
		int y = getIntValue(ele, "y");
		ModelTerminal terminal = new ModelTerminal(new Point(x, y));
		terminal.setType(getIntValue(ele, "type"));

		return terminal;
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
	/**
	 * I take a xml element and the tag name, look for the tag and get the text
	 * content i.e for <employee><name>John</name></employee> xml snippet if the
	 * Element points to employee node and tagName is 'name' I will return John
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	/**
	 * Calls getTextValue and returns a int value
	 */
	private int getIntValue(Element ele, String tagName) {
		// in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele, tagName));
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

	public ArrayList<ModelSwitch> getListSwitch() {
		return listSwitch;
	}

	public ArrayList<ModelItem> getListItem() {
		return listItem;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

}
