package com.btl.data;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.btl.Model.ConversionFunction;

public class SaveFile extends XmlReader {

	private ArrayList<Map> maps = new ArrayList<Map>();

	private class Map {
		int id, highscore;
		boolean isLock = true;
	}

	public final static String fileDir = ConversionFunction
			.getCurrentDirectory() + "save//save.xml";

	private static SaveFile instance;
	static {
		instance = new SaveFile();
		try {
			if (!instance.load(fileDir))
				instance = new SaveFile();
		}

		catch (Exception e) {
			instance = new SaveFile();
		}

		instance.setLock(1, false);
	}

	public static SaveFile create() {

		return instance;
	}

	private SaveFile() {

	}

	public void save() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder;

			docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			/* root element */
			Element saveF = doc.createElement("SaveFile");
			doc.appendChild(saveF);
			for (Map i : maps) {
				Element m = doc.createElement("map");
				saveF.appendChild(m);

				Element id = doc.createElement("id");
				id.appendChild(doc.createTextNode("" + i.id));

				Element highestScore = doc.createElement("highscore");
				highestScore.appendChild(doc.createTextNode("" + i.highscore));

				Element isL = doc.createElement("isLock");
				isL.appendChild(doc.createTextNode("" + i.isLock));

				m.appendChild(id);
				m.appendChild(highestScore);
				m.appendChild(isL);
			}
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = null;

			transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(fileDir));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean load(String fileDir) {
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

		NodeList nlMap = docEle.getElementsByTagName("map");
		getMaps(nlMap);

		return true;
	}

	private void getMaps(NodeList nlMap) {
		if (nlMap != null && nlMap.getLength() > 0) {
			for (int i = 0; i < nlMap.getLength(); i++) {

				// get the factory element
				Element el = (Element) nlMap.item(i);

				// get the Map object
				Map e = getMap(el);

				// add it to list
				maps.add(e);
			}
		}

	}

	private Map getMap(Element ele) {
		Map map = new Map();
		map.id = getIntValue(ele, "id");
		map.highscore = getIntValue(ele, "highscore");
		map.isLock = getBooleanValue(ele, "isLock");

		return map;
	}

	public void setHighscore(int id, int score) {
		for (Map map : maps) {
			if (map.id == id) {
				map.highscore = score;
				return;
			}
		}

		Map map = new Map();
		map.id = id;
		map.highscore = score;
		maps.add(map);
	}

	public int getHighscore(int id) {
		for (Map map : maps) {
			if (map.id == id)
				return map.highscore;

		}
		return 0;
	}

	public boolean getLock(int id) {
		for (Map map : maps) {
			if (map.id == id)
				return map.isLock;

		}
		return true;
	}

	public void setLock(int id, boolean isLock) {
		for (Map map : maps) {
			if (map.id == id) {
				map.isLock = isLock;

				return;
			}
		}

		Map map = new Map();
		map.id = id;
		map.isLock = isLock;
		maps.add(map);
	}

}
