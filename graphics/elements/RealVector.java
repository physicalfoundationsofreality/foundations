package com.gruber.pfr.graphics.elements;

public class RealVector {

	RealPoint origin;
	RealPoint direction;

	public RealVector(RealPoint origin) {
		this.origin = origin;
	}

	public RealPoint getDirection() {
		return direction;
	}

	public void setDirection(RealPoint direction) {
		this.direction = direction;
	}

	public RealPoint getOrigin() {
		return origin;
	}

	public int[] getMaxInt() {

		int[] max = new int[origin.coord.length];
		for(int i = 0; i < origin.coord.length; i++)
			max[i] = Math.max(new Double(Math.ceil(origin.coord[i].getBase() + direction.coord[i].getBase())).intValue(),
				new Double(Math.ceil(origin.coord[i].getBase())).intValue());
		
		return max;
	}
	public int[] getMinInt() {

		int[] min = new int[origin.coord.length];
		for(int i = 0; i < origin.coord.length; i++)
			min[i] = Math.min(new Double(Math.floor(origin.coord[i].getBase() + direction.coord[i].getBase())).intValue(),
				new Double(Math.floor(origin.coord[i].getBase())).intValue());
		
		return min;
	}
	public Vector asVector() {
		
		float[] orig = new float[this.origin.coord.length];
		for(int i = 0; i < orig.length; i++)
			orig[i] = this.origin.coord[i].getBase();
		
		float[] dir = new float[this.direction.coord.length];
		for(int i = 0; i < dir.length; i++)
			dir[i] = this.direction.coord[i].getBase();
		
		
		Vector vec = new Vector(new Point(orig));
		vec.setDirection(new Point(dir));
		
		return vec;
	}
}
