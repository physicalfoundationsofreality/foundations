package com.gruber.pfr.graphics;

import java.util.List;

import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.elements.SimpleVector;

public interface Visualization {

	public List<List<SimpleVector>> getCurves();
	
	public Coordinates getCoordinates();
	
	public void setDisplayParameters(int granularity);
}
