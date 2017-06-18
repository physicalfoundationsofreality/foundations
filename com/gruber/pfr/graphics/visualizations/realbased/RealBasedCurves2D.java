package com.gruber.pfr.graphics.visualizations.realbased;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gruber.pfr.graphics.Visualization2D;
import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.elements.Coordinates2D;
import com.gruber.pfr.graphics.elements.RealBasedVector;
import com.gruber.pfr.graphics.elements.SimpleVector;

public class RealBasedCurves2D implements Visualization2D {

	RealBasedCurves curves;

	public RealBasedCurves2D(RealBasedCurves curves) {
		this.curves = curves;
	}

	public List<List<SimpleVector>> getCurves() {

		List<List<RealBasedVector>> realCurves = this.curves.getRealCurves();
		List<List<SimpleVector>> curves = new ArrayList<List<SimpleVector>>();

		Iterator<List<RealBasedVector>> curvesIter = realCurves.iterator();
		while (curvesIter.hasNext()) {
			ArrayList<SimpleVector> curve = new ArrayList<SimpleVector>();

			Iterator<RealBasedVector> curveIter = curvesIter.next().iterator();
			while (curveIter.hasNext())
				curve.add(curveIter.next().asVector());

			curves.add(curve);
		}
		return curves;
	}

	public Coordinates2D getCoordinates() {

		Coordinates coord = this.curves.coord;
		return new Coordinates2D(coord.getMin(0), coord.getMax(0), coord.getMin(1), coord.getMax(1));
	}

	public void setDisplayParameters(int granularity) {
		this.curves.setDisplayParameters(granularity);
	}

}
