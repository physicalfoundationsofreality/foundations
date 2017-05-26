package com.gruber.pfr.graphics.visualizations;

import java.util.ArrayList;
import java.util.List;

import com.gruber.pfr.graphics.Visualization;
import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.elements.Point;
import com.gruber.pfr.graphics.elements.Vector;

public abstract class Curves implements Visualization {

	Coordinates coord = null;
	int gran;
	int length;
	Vector[] startingPoints;

	protected Curves(Vector[] startingPoints, int length, int granularity, int dimension) {

		this.coord = new Coordinates(new int[dimension], new int[dimension]);
		this.gran = granularity;
		this.startingPoints = startingPoints;
		this.length = length;
	}

	public abstract Vector getNextCurveValue(Vector current);

	public List<List<Vector>> getCurves() {

		if (gran == 0 || startingPoints.length == 0)
			return null;
		else {

			ArrayList<List<Vector>> paths = new ArrayList<List<Vector>>(startingPoints.length);
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

				ArrayList<Vector> path = new ArrayList<Vector>();

				Vector current = startingPoints[i];
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
