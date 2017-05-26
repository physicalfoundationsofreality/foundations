package com.gruber.pfr.graphics.visualizations;

import com.gruber.pfr.graphics.elements.RealPoint;
import com.gruber.pfr.graphics.elements.RealVector;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealNumbers;

public class RealCircleField2D implements RealVectorField {

	public RealVector getVector(RealPoint origin) {
		
		RealVector vec = new RealVector(origin);
		RealNumber[] dir = new RealNumber[2];
		dir[0] = origin.getCoordinates()[1].getNegative();
		dir[1] = origin.getCoordinates()[0];
		RealPoint direction = new RealPoint(dir);
		vec.setDirection(direction);
		return vec;
	}

}
