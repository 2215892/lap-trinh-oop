package com.btl.GameElements.gameplay;

import java.awt.Graphics;
import java.awt.Point;

import com.btl.GameEngine.Drawable;
import com.btl.Model.ConversionFunction;
import com.btl.Model.Direction;

// TODO: Auto-generated Javadoc
/**
 * The Class PlaySquare.
 */
public class PlaySquare implements Drawable {
    private Direction direction;
    private Point position;

    /** The Constant SIZE. */
    public final static int SIZE = PlaySwitch.SIZE;

    /**
     * Instantiates a new play square.
     * 
     * @param direction
     *            the direction
     * @param position
     *            the position
     */
    public PlaySquare(Direction direction, Point position) {
	this.direction = direction;
	this.position = position;
    }

    /**
     * Lay toa do de ve hinh
     * 
     * @return toa do goc tren ben trai de ve hinh
     */
    private Point getCoordinate() {
	Point coordinateLogic = new Point(position.x * SIZE, position.y * SIZE);
	/* Goc tren ben trai */
	Point vertexA = ConversionFunction.logicToReal(coordinateLogic);
	/* Goc tren ben phai */
	Point vertexB = ConversionFunction.logicToReal(new Point(
		coordinateLogic.x + SIZE, coordinateLogic.y));

	return new Point(vertexA.x, vertexB.y);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.btl.GameEngine.Drawable#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
	Point coordinate = this.getCoordinate();
	int imageSize = 2 * SIZE;

	switch (direction) { /* Xet huong de chon hinh phu hop */
	case UP: {
	    g.drawImage(DirectionImage.SUP, coordinate.x, coordinate.y,
		    imageSize, imageSize, null);
	    break;
	}
	case DOWN: {
	    g.drawImage(DirectionImage.SDOWN, coordinate.x, coordinate.y,
		    imageSize, imageSize, null);
	    break;
	}
	case RIGHT: {
	    g.drawImage(DirectionImage.SRIGHT, coordinate.x, coordinate.y,
		    imageSize, imageSize, null);
	    break;
	}
	case LEFT: {
	    g.drawImage(DirectionImage.SLEFT, coordinate.x, coordinate.y,
		    imageSize, imageSize, null);
	    break;
	}
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.btl.GameEngine.Drawable#contains(java.awt.Point)
     */
    @Override
    public boolean contains(Point point) {
	return false;
    }

}
