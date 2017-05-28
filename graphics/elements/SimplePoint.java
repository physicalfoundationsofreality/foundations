package com.gruber.pfr.graphics.elements;

import com.gruber.pfr.space.numbers.real.RealNumber;

public class SimplePoint {
	
	float[] coord;
	
	public SimplePoint(float[] coordinates) {
		this.coord = coordinates;
	}
	
	public float[] getCoordinates() {
		return coord;
	}
}
