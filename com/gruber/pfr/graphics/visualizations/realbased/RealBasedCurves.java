package com.gruber.pfr.graphics.visualizations.realbased;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gruber.pfr.graphics.Visualization;
import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.elements.RealBasedVector;
import com.gruber.pfr.graphics.elements.SimpleVector;

public abstract class RealBasedCurves implements Visualization {

	Coordinates coord = null;
	int gran;
	int length;
	RealBasedVector[] startingPoints;

	protected RealBasedCurves(RealBasedVector[] startingPoints, int length, int granularity, int dimension) {

		this.coord = new Coordinates(new int[dimension], new int[dimension]);
		this.gran = granularity;
		this.startingPoints = startingPoints;
		this.length = length;
	}

	public abstract RealBasedVector getNextCurveValue(RealBasedVector current);

	public RealBasedVector[] getStartingPoints() {
		return startingPoints;
	}

	public void setStartingPoints(RealBasedVector[] startingPoints) {
		this.startingPoints = startingPoints;
	}

	public List<List<RealBasedVector>> getRealCurves() {

		if (gran == 0 || startingPoints.length == 0)
			return null;
		else {

			ArrayList<List<RealBasedVector>> paths = new ArrayList<List<RealBasedVector>>(startingPoints.length);
			float inc = 1 / new Float(gran).floatValue();

			for (int i = 0; i < this.coord.getDimension(); i++) {
				coord.setMax(startingPoints[0].getMaxInt()[i], i);
				coord.setMin(startingPoints[0].getMinInt()[i], i);
			}

			for (int i = 0; i < startingPoints.length; i++) {

				for (int j = 0; j < this.coord.getDimension(); j++) {

					if (startingPoints[i].getMinInt()[j] < coord.getMin(j))
						coord.setMin(startingPoints[i].getMinInt()[j], j);

					if (startingPoints[i].getMaxInt()[j] > coord.getMax(j))
						coord.setMax(startingPoints[i].getMaxInt()[j], j);
				}

				ArrayList<RealBasedVector> path = new ArrayList<RealBasedVector>();

				RealBasedVector current = startingPoints[i];
				path.add(current);

				for (float r = 0; r < length; r += inc) {

					current = this.getNextCurveValue(current);

					for (int j = 0; j < this.coord.getDimension(); j++) {

						if (current.getMinInt()[j] < coord.getMin(j))
							coord.setMin(current.getMinInt()[j], j);

						if (current.getMaxInt()[j] > coord.getMax(j))
							coord.setMax(current.getMaxInt()[j], j);
					}

					path.add(current);
				}
				paths.add(path);
			}
			return paths;
		}
	}

	public Coordinates getCoordinates() {
		return coord;
	}

	public void setDisplayParameters(int granularity) {
		this.gran = granularity;
	}
}
