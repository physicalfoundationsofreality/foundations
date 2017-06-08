package com.gruber.pfr.graphics.visualizations;

import com.gruber.pfr.graphics.elements.SimplePoint;
import com.gruber.pfr.graphics.elements.SimpleVector;

public class SimpleNumericCurvesApproximation extends Curves {

	VectorField field;

	public SimpleNumericCurvesApproximation(SimpleVector[] startingPoints, int length, int granularity, int dimension,
			VectorField field) {
		super(startingPoints, length, granularity, dimension);
		this.field = field;
	}

	public SimpleVector getNextCurveValue(SimpleVector current) {

		float[] newOrigin = new float[this.coord.getDimension()];
		for (int i = 0; i < this.coord.getDimension(); i++)
			newOrigin[i] = current.getOrigin().getCoordinates()[i] + current.getDirection().getCoordinates()[i] / gran;

		SimpleVector newVector = field.getVector(new SimplePoint(newOrigin));

		return newVector;
	}
}
