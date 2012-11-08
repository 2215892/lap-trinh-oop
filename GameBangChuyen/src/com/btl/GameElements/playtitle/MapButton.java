package com.btl.GameElements.playtitle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.btl.GameElements.playstate.Button;

public class MapButton extends Button {

	private int id;
	private boolean isLock;

	public MapButton(Point p, int id, boolean isLock) {
		super(p);
		setId(id);
		setLock(isLock);

		BufferedImage img = new BufferedImage(50, 50,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.gray);
		g.fillRect(0, 0, 50, 50);
		g.setColor(Color.red);
		String text = Integer.toString(getId());
		if (isLock)
			text += "Locked";
		g.drawString(text, 0, 15);
		g.dispose();
		this.setImage(img, 50, 50);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isLock() {
		return isLock;
	}

	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}

}
