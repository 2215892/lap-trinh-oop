package com.btl.Model;

import java.awt.Point;

public class ModelTerminal {
	Point position;
	
	public ModelTerminal( Point p){
		position = p;
	}
	
	public Point getPosition(){
		return position;
	}
}
