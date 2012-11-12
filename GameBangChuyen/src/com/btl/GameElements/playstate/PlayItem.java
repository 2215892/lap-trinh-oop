package com.btl.GameElements.playstate;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;
import com.btl.Model.ModelItem;
import com.btl.data.ItemImage;

// TODO: Auto-generated Javadoc
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

	/* (non-Javadoc)
	 * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
	 */
	@Override
	public boolean contains(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		Point coordinate = ConversionFunction.positionToLocation(getPosition(),
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
