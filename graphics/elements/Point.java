package com.gruber.pfr.graphics.elements;

public class Point {
	
	float[] coord;
	
	public Point(float[] coordinates) {
		this.coord = coordinates;
	}
	
	public float[] getCoordinates() {
		return coord;
	}
}
