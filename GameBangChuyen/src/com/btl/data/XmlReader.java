package com.btl.data;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The Class XmlReader.
 */
public abstract class XmlReader {

	/**
	 * Lấy giá trị dạng text.
	 * 
	 * @param ele
	 *            đối tượng Element
	 * @param tagName
	 *            the tag name
	 * @return giá trị
	 */
	protected String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	/**
	 * Lấy giá trị dạng int.
	 * 
	 * @param ele
	 *            đối tượng Element
	 * @param tagName
	 *            the tag name
	 * @return giá trị tương ứng
	 */
	protected int getIntValue(Element ele, String tagName) {
		// in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele, tagName));
	}

	/**
	 * Lấy giá trị dạng boolean.
	 * 
	 * @param ele
	 *            đối tượng Element
	 * @param tagName
	 *            the tag name
	 * @return giá trị tương ứng
	 */
	protected boolean getBooleanValue(Element ele, String tagName) {
		return Boolean.parseBoolean(getTextValue(ele, tagName));
	}

}
