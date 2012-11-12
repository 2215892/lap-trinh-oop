package com.oop.play.objects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.oop.data.ItemImage;
import com.oop.gamepanel.Drawable;
import com.oop.model.Helper;
import com.oop.model.ModelItem;

/**
 * The Class PlayItem.
 */
public class PlayItem extends ModelItem implements Drawable {

	private BufferedImage buffer;
	private Point pad;

	/**
	 * Instantiates a new play item.
	 * 
	 * @param item
	 *            the item
	 */
	public PlayItem(ModelItem item) {
		super(item);
		initPicture();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.gamepanel.Drawable#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oop.gamepanel.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		Point coordinate = Helper.positionToLocation(getPosition(),
				PlaySquare.SIZE);
		if (buffer != null)
			g.drawImage(buffer, coordinate.x - pad.x, coordinate.y - pad.y,
					null);

	}
	private void initPicture() {
		buffer = ItemImage.getItemImage(getId(), getType());
		pad = ItemImage.getItemPad(getId(), getType());

	}

}
