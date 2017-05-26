package com.gruber.pfr.graphics.visualizations;

import com.gruber.pfr.graphics.elements.Point;
import com.gruber.pfr.graphics.elements.Vector;

public class SimpleNumericCurvesApproximation extends Curves {

	VectorField field;

	public SimpleNumericCurvesApproximation(Vector[] startingPoints, int length, int granularity, int dimension,
			VectorField field) {
		super(startingPoints, length, granularity, dimension);
		this.field = field;
	}

	public Vector getNextCurveValue(Vector current) {

		float[] newOrigin = new float[this.coord.getDimension()];
		for (int i = 0; i < this.coord.getDimension(); i++)
			newOrigin[i] = current.getOrigin().getCoordinates()[i] + current.getDirection().getCoordinates()[i] / gran;

		Vector newVector = field.getVector(new Point(newOrigin));

		return newVector;
	}
}
