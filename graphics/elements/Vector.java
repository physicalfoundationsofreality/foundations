package com.gruber.pfr.graphics.elements;

public class Vector {

	Point origin;
	Point direction;

	public Vector(Point origin) {
		this.origin = origin;
	}

	public Point getDirection() {
		return direction;
	}

	public void setDirection(Point direction) {
		this.direction = direction;
	}

	public Point getOrigin() {
		return origin;
	}

	public int[] getMaxInt() {

		int[] max = new int[origin.coord.length];
		for(int i = 0; i < origin.coord.length; i++)
			max[i] = Math.max(new Double(Math.ceil(origin.coord[i] + direction.coord[i])).intValue(),
				new Double(Math.ceil(origin.coord[i])).intValue());
		
		return max;
	}
	public int[] getMinInt() {

		int[] min = new int[origin.coord.length];
		for(int i = 0; i < origin.coord.length; i++)
			min[i] = Math.min(new Double(Math.floor(origin.coord[i] + direction.coord[i])).intValue(),
				new Double(Math.floor(origin.coord[i])).intValue());
		
		return min;
	}
}
