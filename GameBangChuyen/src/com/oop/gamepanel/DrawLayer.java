package com.oop.gamepanel;

import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;

import com.oop.model.ModelObject;

/**
 * The Class DrawLayer.
 */
public class DrawLayer extends Layer {

	private class ObjComparator implements Comparator<Drawable> {

		@Override
		public int compare(Drawable o1, Drawable o2) {
			Point p1 = ((ModelObject) o1).getPosition();
			Point p2 = ((ModelObject) o2).getPosition();

			int xComparision = p1.x - p2.x;

			int yComparision = p2.y - p1.y;

			if (yComparision == 0) {

				return xComparision;

			} else
				return yComparision;
		}
	}

	/**
	 * Instantiates a new draw layer.
	 * 
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public DrawLayer(int width, int height) {
		super(width, height);
	}

	/**
	 * Sort.
	 */
	public void sort() {
		Collections.sort(this.listDrawable, new ObjComparator());
	}

}
