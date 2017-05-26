package com.gruber.pfr.graphics.elements;

import com.gruber.pfr.space.numbers.real.RealNumber;

public class Point {
	
	float[] coord;
	
	public Point(float[] coordinates) {
		this.coord = coordinates;
	}
	
	public float[] getCoordinates() {
		return coord;
	}
}
