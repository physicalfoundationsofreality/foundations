package com.gruber.pfr.graphics;

import java.util.List;

import com.gruber.pfr.graphics.elements.Coordinates2D;
import com.gruber.pfr.graphics.elements.Vector;

public interface Visualization2D {

	public List<List<Vector>> getCurves();
	
	public Coordinates2D getCoordinates();
	
	public void setDisplayParameters(int granularity);
}
