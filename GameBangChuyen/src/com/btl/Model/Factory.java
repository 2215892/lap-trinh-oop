package com.btl.Model;

import java.awt.Point;

public class Factory {
	Point position;
	Direction direction;
	
	public Factory(Point p){
		position = p;
	}
	
	public Point getPosition(){
		return position;
	}
	
	public Direction getDirection(){
		return direction;
	}
	
	public void setDirection(Direction d){
		direction = d;
	}
}
