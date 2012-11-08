package com.btl.GameElements.playstate;

import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;

import com.btl.GameEngine.Drawable;
import com.btl.GameEngine.Layer;
import com.btl.Model.ModelObject;

public class DrawLayer extends Layer {

	private class ObjComparator implements Comparator<Drawable> {

		@Override
		public int compare(Drawable o1, Drawable o2) {
			Point p1 = ((ModelObject) o1).getPosition();
			Point p2 = ((ModelObject) o2).getPosition();
			/*
			 * int xComparision = p1.x - p2.x;
			 * 
			 * int yComparision = p2.y - p1.y;
			 * 
			 * if (yComparision == 0) {
			 * 
			 * return xComparision;
			 * 
			 * } else return yComparision;
			 */
			return (p1.x - p2.x + p2.y - p1.y);
		}
	}

	public DrawLayer(int width, int height) {
		super(width, height);
	}

	public void sort() {
		Collections.sort(this.listDrawable, new ObjComparator());
	}

}
