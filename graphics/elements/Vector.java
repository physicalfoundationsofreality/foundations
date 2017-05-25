package com.gruber.pfr.graphics.elements;

public class Vector {

	Point origin;
	Point direction;
	
	public Vector(Point origin) {
		this.origin = origin;
	}
	
	public Point getDirection() {
		return direction;
	}
	public void setDirection(Point direction) {
		this.direction = direction;
	}
	public Point getOrigin() {
		return origin;
	}	
}
