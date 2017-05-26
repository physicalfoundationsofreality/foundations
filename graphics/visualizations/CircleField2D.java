package com.gruber.pfr.graphics.visualizations;

import com.gruber.pfr.graphics.elements.Point;
import com.gruber.pfr.graphics.elements.Vector;

public class CircleField2D implements VectorField {

	public Vector getVector(Point origin) {
		
		Vector vec = new Vector(origin);
		float[] dir = new float[2];
		dir[0] = -origin.getCoordinates()[1];
		dir[1] = origin.getCoordinates()[0];
		Point direction = new Point(dir);
		vec.setDirection(direction);
		return vec;
	}

}
