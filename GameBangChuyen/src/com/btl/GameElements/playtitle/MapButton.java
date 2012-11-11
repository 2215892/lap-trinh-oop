package com.btl.GameElements.playtitle;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.btl.GameElements.playstate.Button;
import com.btl.data.ButtonImage;
import com.btl.data.SaveFile;

public class MapButton extends Button {

	private int id;
	private boolean isLock;

	public MapButton(Point p, int id, boolean isLock) {
		super(p);
		setId(id);
		setLock(isLock);

		update();
	}

	public void update() {
		this.setLock(SaveFile.create().getLock(getId()));
		BufferedImage temp;
		if (isLock()) {
			temp = ButtonImage.BTN_LEVEL_LOCK;
		} else {
			temp = ButtonImage.BTN_LEVEL;
		}

		BufferedImage img = new BufferedImage(temp.getWidth(),
				temp.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.drawImage(temp, 0, 0, null);
		g.setColor(Color.GRAY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		String text = Integer.toString(getId());

		Font font = new Font("TimesRoman", Font.BOLD, 18);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		int keyWidth = fm.stringWidth(text);
		int keyHeight = fm.getHeight();
		int x = img.getWidth() / 2;
		int y = img.getHeight() / 2;
		g.drawString(text, x - (keyWidth / 2), y + (keyHeight / 4));

		g.dispose();
		this.setImage(img);
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
