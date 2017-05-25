package com.gruber.pfr.graphics;

import java.util.List;

import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.elements.Vector;

public interface Visualization {

	public List<List<Vector>> getCurves();
	
	public Coordinates getCoordinates();
	
	public void setDisplayParameters(int granularity);
}
