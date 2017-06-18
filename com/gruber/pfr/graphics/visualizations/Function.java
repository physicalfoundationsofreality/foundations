package com.gruber.pfr.graphics.visualizations;

import java.util.ArrayList;
import java.util.List;

import com.gruber.pfr.graphics.Visualization2D;
import com.gruber.pfr.graphics.elements.Coordinates2D;
import com.gruber.pfr.graphics.elements.SimplePoint;
import com.gruber.pfr.graphics.elements.SimpleVector;

public abstract class Function implements Visualization2D {

	Coordinates2D coord = null;
	int gran;

	protected Function(int minX, int maxX, int granularity) {
		
		this.coord = new Coordinates2D(minX, maxX, 0, 0);
		this.gran = granularity;
	}

	public abstract float getFunctionValue(float x);

	public List<List<SimpleVector>> getCurves() {

		if (gran == 0)
			return null;
		else {

			ArrayList<List<SimpleVector>> paths = new ArrayList<List<SimpleVector>>(1);
			ArrayList<SimpleVector> path = new ArrayList<SimpleVector>();

			float inc = 1 / new Float(gran).floatValue();
			
			float[] oldpoint = new float[2];
			oldpoint[0] = coord.getMinX() - inc;

			oldpoint[1] = getFunctionValue(oldpoint[0]);

			coord.setMinY(new Double(Math.floor(oldpoint[1])).intValue());
			coord.setMaxY(new Double(Math.ceil(oldpoint[1])).intValue());

			for (float x = coord.getMinX(); x <= coord.getMaxX(); x += inc) {

				float[] point = new float[2];
				point[0] = x;
				point[1] = getFunctionValue(x);
				SimpleVector vec = new SimpleVector(new SimplePoint(point));

				if (point[1] < coord.getMinY())
					coord.setMinY(new Double(Math.floor(point[1])).intValue());

				if (point[1] > coord.getMaxY())
					coord.setMaxY(new Double(Math.ceil(point[1])).intValue());
				
				float[] dir = new float[2];
				dir[0] = inc;
				dir[1] = (point[1] - oldpoint[1]) / inc;

				if (point[1] + dir[1] < coord.getMinY())
					coord.setMinY(new Double(Math.floor(point[1] + dir[1])).intValue());

				if (point[1] + dir[1] > coord.getMaxY())
					coord.setMaxY(new Double(Math.ceil(point[1] + dir[1])).intValue());
				
				if (point[1] - dir[1] < coord.getMinY())
					coord.setMinY(new Double(Math.floor(point[1] - dir[1])).intValue());

				if (point[1] - dir[1] > coord.getMaxY())
					coord.setMaxY(new Double(Math.ceil(point[1] - dir[1])).intValue());
				
				vec.setDirection(new SimplePoint(dir));
				oldpoint = point;
				
				path.add(vec);
			}
			paths.add(path);
			return paths;
		}
	}

	public Coordinates2D getCoordinates() {
		return coord;
	}

	public void setDisplayParameters(int granularity) {
		this.gran = granularity;
	}
}
