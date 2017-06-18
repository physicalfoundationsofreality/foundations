package com.gruber.pfr.graphics;

import com.gruber.pfr.graphics.elements.Coordinates;

public interface Visualization {

//	public List<List<SimpleVector>> getCurves();
	
	public Coordinates getCoordinates();
	
	public void setDisplayParameters(int granularity);
}
