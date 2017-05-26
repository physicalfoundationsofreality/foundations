package com.gruber.pfr.graphics.visualizations;

import java.util.List;

import com.gruber.pfr.graphics.Visualization2D;
import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.elements.Coordinates2D;
import com.gruber.pfr.graphics.elements.Vector;

public class RealCurves2D implements Visualization2D {

	RealCurves curves;
	public RealCurves2D(RealCurves curves) {
		this.curves = curves;
	}

	public List<List<Vector>> getCurves() {
		
		return this.curves.getCurves();
	}

	public Coordinates2D getCoordinates() {
		
		Coordinates coord = this.curves.coord;
		return new Coordinates2D(coord.getMin(0),coord.getMax(0),coord.getMin(1),coord.getMax(1));
	}

	public void setDisplayParameters(int granularity) {
		this.curves.setDisplayParameters(granularity);
	}

}
