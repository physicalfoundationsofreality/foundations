package com.gruber.pfr.graphics;

import java.awt.Color;
import java.util.ArrayList;

import com.gruber.pfr.graphics.elements.SimpleVector;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVector;

public class SimpleCurve {
	
	Color originColor;
	Color directionColor;
	SimpleVector startingPoint;
	
	ArrayList<SimpleVector> curve = new ArrayList<SimpleVector>();
	
	public SimpleCurve(Color originColor, Color directionColor, SimpleVector startingPoint) {
		super();
		this.originColor = originColor;
		this.directionColor = directionColor;
		this.startingPoint = startingPoint;
	}
	
	public SimpleVector getStartingPoint() {
		return startingPoint;
	}

	public boolean add(SimpleVector vector) {
		return this.curve.add(vector);
	}
	
	public SimpleVector get(int index) {
		return this.curve.get(index);
	}
	
	public Color getOriginColor() {
		return originColor;
	}
	
	public void setOriginColor(Color originColor) {
		this.originColor = originColor;
	}
	
	public Color getDirectionColor() {
		return directionColor;
	}
	
	public void setDirectionColor(Color directionColor) {
		this.directionColor = directionColor;
	}
	
	public ArrayList<SimpleVector> getVectors() {
		return curve;
	}
}
