package com.btl.GameElements.playstate;

import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;

import com.btl.GameEngine.Drawable;
import com.btl.GameEngine.Layer;
import com.btl.Model.ModelObject;

public class DrawLayer extends Layer {

	public DrawLayer(int width, int height) {
		super(width, height);
	}

	public void sort() {
		Collections.sort(this.listDrawable, new ObjComparator());
	}

	private class ObjComparator implements Comparator<Drawable> {

		@Override
		public int compare(Drawable o1, Drawable o2) {
			Point p1 = ((ModelObject) o1).getPosition();
			Point p2 = ((ModelObject) o2).getPosition();

			int xComparision = -1;
			if (p1.x > p2.x)
				xComparision = 1;
			else if (p1.x == p2.x)
				xComparision = 0;

			if (xComparision == 0) {
				int yComparision = -1;
				if (p1.y < p2.y)
					yComparision = 1;
				else if (p1.y == p2.y)
					yComparision = 0;
				return yComparision;

			} else
				return xComparision;
		}

	}

}
