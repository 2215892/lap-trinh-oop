package com.btl.Model;

import java.awt.Point;

public class ModelFactory {
	Point position;
	Direction direction;
	
	public ModelFactory(Point p){
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
