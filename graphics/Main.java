package com.gruber.pfr.graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.gruber.pfr.graphics.GraphWizard.FunctionType;
import com.gruber.pfr.graphics.elements.Point;
import com.gruber.pfr.graphics.elements.RealPoint;
import com.gruber.pfr.graphics.elements.RealVector;
import com.gruber.pfr.graphics.elements.Vector;
import com.gruber.pfr.graphics.visualizations.AdvancedNumericCurvesApproximation;
import com.gruber.pfr.graphics.visualizations.CircleField2D;
import com.gruber.pfr.graphics.visualizations.Curves;
import com.gruber.pfr.graphics.visualizations.Curves2D;
import com.gruber.pfr.graphics.visualizations.Polynomial;
import com.gruber.pfr.graphics.visualizations.RealCircleField2D;
import com.gruber.pfr.graphics.visualizations.RealCurves;
import com.gruber.pfr.graphics.visualizations.RealCurves2D;
import com.gruber.pfr.graphics.visualizations.SimpleNumericCurvesApproximation;
import com.gruber.pfr.space.numbers.real.RealNumber;

public class Main {

	public static void main(String[] args) {

		vectorTest2();
	}
	public static void testSetup() {
		
		JFrame f = new JFrame("Graph Wizard");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		Polynomial pol = new Polynomial(new float[] { 0, 63, -3, 5, -1}, -2, 6, 5);
	
		GraphWizard ap = new GraphWizard(pol,new Dimension(1500,1000), FunctionType.LINE_STRAIGHT);
	
		ap.init();
		ap.start();
		f.add("Center", ap);
		f.pack();
		f.setVisible(true);
	}
	public static void vectorTest1() {
		
		JFrame f = new JFrame("Graph Wizard");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		Vector[] start = new Vector[3];
		
		start[0] = new Vector(new Point(new float[] {1,0}));
		start[0].setDirection(new Point(new float[] {0,1}));
		
		start[1] = new Vector(new Point(new float[] {2,0}));
		start[1].setDirection(new Point(new float[] {0,2}));
		
		start[2] = new Vector(new Point(new float[] {4,0}));
		start[2].setDirection(new Point(new float[] {0,4}));
		
		Curves curves = new SimpleNumericCurvesApproximation(start, 50, 10, 2, new CircleField2D());
	
		GraphWizard ap = new GraphWizard(new Curves2D(curves),new Dimension(1500,1000), FunctionType.LINE_STRAIGHT);
	
		ap.init();
		ap.start();
		f.add("Center", ap);
		f.pack();
		f.setVisible(true);
	}
	public static void vectorTest2() {
		
		JFrame f = new JFrame("Graph Wizard");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		RealVector[] start = new RealVector[3];
		
		start[0] = new RealVector(new RealPoint(new RealNumber[] {new RealNumber(1),new RealNumber(0)}));
		start[0].setDirection(new RealPoint(new RealNumber[] {new RealNumber(0),new RealNumber(1)}));
		
		start[1] = new RealVector(new RealPoint(new RealNumber[] {new RealNumber(2),new RealNumber(0)}));
		start[1].setDirection(new RealPoint(new RealNumber[] {new RealNumber(0),new RealNumber(2)}));
		
		start[2] = new RealVector(new RealPoint(new RealNumber[] {new RealNumber(4),new RealNumber(0)}));
		start[2].setDirection(new RealPoint(new RealNumber[] {new RealNumber(0),new RealNumber(4)}));
		
		RealCurves curves = new AdvancedNumericCurvesApproximation(start, 50, 10, 2, new RealCircleField2D());
	
		GraphWizard ap = new GraphWizard(new RealCurves2D(curves),new Dimension(1500,1000), FunctionType.LINE_STRAIGHT);
	
		ap.init();
		ap.start();
		f.add("Center", ap);
		f.pack();
		f.setVisible(true);
	}
}
