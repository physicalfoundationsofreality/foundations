package com.gruber.pfr.graphics.visualizations;

import java.util.ArrayList;
import java.util.List;

import com.gruber.pfr.graphics.Visualization;
import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.elements.Point;
import com.gruber.pfr.graphics.elements.Vector;

public abstract class Function implements Visualization {

	Coordinates coord = null;
	int gran;

	protected Function(int minX, int maxX, int granularity) {
		
		this.coord = new Coordinates(minX, maxX, 0, 0);
		this.gran = granularity;
	}

	public abstract float getFunctionValue(float x);

	public List<List<Vector>> getCurves() {

		if (gran == 0)
			return null;
		else {

			ArrayList<List<Vector>> paths = new ArrayList<List<Vector>>(1);
			ArrayList<Vector> path = new ArrayList<Vector>();

			float inc = 1 / new Float(gran).floatValue();
			
			float[] oldpoint = new float[2];
			oldpoint[0] = coord.getMinX() - inc;

			int pos = 0;
			oldpoint[1] = getFunctionValue(oldpoint[0]);

			coord.setMinY(new Double(Math.floor(oldpoint[1])).intValue());
			coord.setMaxY(new Double(Math.ceil(oldpoint[1])).intValue());

			for (float x = coord.getMinX(); x <= coord.getMaxX(); x += inc) {

				float[] point = new float[2];
				point[0] = x;
				point[1] = getFunctionValue(x);
				Vector vec = new Vector(new Point(point));

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
				
				vec.setDirection(new Point(dir));
				oldpoint = point;
				
				path.add(vec);
				pos++;
			}
			paths.add(path);
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
