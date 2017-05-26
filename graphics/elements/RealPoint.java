package com.gruber.pfr.graphics.elements;

import com.gruber.pfr.space.numbers.real.RealNumber;

public class RealPoint {
	
	RealNumber[] coord;
	
	public RealPoint(RealNumber[] coordinates) {
		this.coord = coordinates;
	}
	
	public RealNumber[] getCoordinates() {
		return coord;
	}
}
