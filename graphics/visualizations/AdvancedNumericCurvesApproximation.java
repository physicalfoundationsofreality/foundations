package com.gruber.pfr.graphics.visualizations;

import com.gruber.pfr.graphics.elements.RealPoint;
import com.gruber.pfr.graphics.elements.RealVector;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealNumbers;

public class AdvancedNumericCurvesApproximation extends RealCurves {

	RealVectorField field;
	RealNumber increment;

	public AdvancedNumericCurvesApproximation(RealVector[] startingPoints, int length, int granularity, int dimension,
			RealVectorField field) {
		super(startingPoints, length, granularity, dimension);
		this.field = field;
		this.increment = new RealNumber(1 / (new Float(granularity).floatValue()));
	}

	public RealVector getNextCurveValue(RealVector current) {

		RealNumber[] newOrigin = new RealNumber[this.coord.getDimension()];

		for (int i = 0; i < this.coord.getDimension(); i++)
			newOrigin[i] = current.getOrigin().getCoordinates()[i].add(
					current.getDirection().getCoordinates()[i].multiply(this.increment));

		RealVector newVector = field.getVector(new RealPoint(newOrigin));

		RealNumber factor = new RealNumber(new Float(1).floatValue()/2);
		factor = this.increment.multiply(factor);
		// correct the approximation
		for (int i = 0; i < this.coord.getDimension(); i++)
			newOrigin[i] = current.getOrigin().getCoordinates()[i].add(factor.multiply(
							newVector.getDirection().getCoordinates()[i].add(current.getDirection().getCoordinates()[i])));

		newVector = field.getVector(new RealPoint(newOrigin));

		return newVector;
	}
}
