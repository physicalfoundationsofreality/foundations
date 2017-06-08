package com.gruber.pfr.graphics.visualizations;

public class Polynomial extends Function {

	float[] coefficients = new float[0];

	public Polynomial(float[] coefficients,int minX, int maxX, int granularity) {
		super(minX, maxX, granularity);
		this.coefficients = coefficients;
	}

	public float getFunctionValue(float x) {

		float value = 0;

		for (int i = 0; i < this.coefficients.length; i++)
			value += this.coefficients[i] * Math.pow(x, i);

		return value;
	}
}
