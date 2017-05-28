package com.gruber.pfr.graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.gruber.pfr.graphics.GraphWizard.FunctionType;
import com.gruber.pfr.graphics.elements.RealBasedVector;
import com.gruber.pfr.graphics.elements.SimplePoint;
import com.gruber.pfr.graphics.elements.SimpleVector;
import com.gruber.pfr.graphics.visualizations.CircleField2D;
import com.gruber.pfr.graphics.visualizations.Curves;
import com.gruber.pfr.graphics.visualizations.Curves2D;
import com.gruber.pfr.graphics.visualizations.Polynomial;
import com.gruber.pfr.graphics.visualizations.SimpleNumericCurvesApproximation;
import com.gruber.pfr.graphics.visualizations.phasespace.CentralForceField;
import com.gruber.pfr.graphics.visualizations.phasespace.PhaseSpaceCurvesApproximation;
import com.gruber.pfr.graphics.visualizations.realbased.AdvancedNumericCurvesApproximation;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCircleField2D;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurves;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedCurves2D;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealVector;

public class Main {

	public static void main(String[] args) {

		JFrame f = new JFrame("Graph Wizard");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GraphWizard ap = new GraphWizard(phaseSpaceTest(), new Dimension(1000, 1000),
				FunctionType.LINE_STRAIGHT_TANGENT);

		ap.init();
		ap.start();
		f.add("Center", ap);
		f.pack();
		f.setVisible(true);
	}

	public static Visualization2D testSetup() {

		return new Polynomial(new float[] { 0, 63, -3, 5, -1 }, -2, 6, 5);
	}

	public static Visualization2D  vectorTest1() {

		SimpleVector[] start = new SimpleVector[3];

		start[0] = new SimpleVector(new SimplePoint(new float[] { 1, 0 }));
		start[0].setDirection(new SimplePoint(new float[] { 0, 1 }));

		start[1] = new SimpleVector(new SimplePoint(new float[] { 2, 0 }));
		start[1].setDirection(new SimplePoint(new float[] { 0, 2 }));

		start[2] = new SimpleVector(new SimplePoint(new float[] { 4, 0 }));
		start[2].setDirection(new SimplePoint(new float[] { 0, 4 }));

		Curves curves = new SimpleNumericCurvesApproximation(start, 50, 10, 2, new CircleField2D());

		return new Curves2D(curves);
	}

	public static Visualization2D vectorTest2() {

		RealBasedVector[] start = new RealBasedVector[3];

		try {
			start[0] = new RealBasedVector(new RealVector( new RealNumber[] { new RealNumber(1), new RealNumber(0) }));
			start[0].setDirection(new RealVector(new RealNumber[] { new RealNumber(0), new RealNumber(1) }));

			start[1] = new RealBasedVector(new RealVector(new RealNumber[] { new RealNumber(2), new RealNumber(0) }));
			start[1].setDirection(new RealVector(new RealNumber[] { new RealNumber(0), new RealNumber(2) }));

			start[2] = new RealBasedVector(new RealVector(new RealNumber[] { new RealNumber(4), new RealNumber(0) }));
			start[2].setDirection(new RealVector(new RealNumber[] { new RealNumber(0), new RealNumber(4) }));

			RealBasedCurves curves = new AdvancedNumericCurvesApproximation(start, 70, 10, 2,
					new RealBasedCircleField2D());

			return new RealBasedCurves2D(curves);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Visualization2D phaseSpaceTest() {

		RealVector[] start = new RealVector[1];
		RealVector[] vel = new RealVector[1];

		try {
			start[0] = new RealVector(new RealNumber[] { new RealNumber(10) });
			vel[0] = new RealVector(new RealNumber[] { new RealNumber(-1) });
//			start[1] = new RealVector(new RealNumber[] { new RealNumber(2) });
//			vel[1] = new RealVector(new RealNumber[] { new RealNumber(0) });
//			
//			start[2] = new RealVector(new RealNumber[] { new RealNumber(4) });
//			vel[2] = new RealVector(new RealNumber[] { new RealNumber(0) });
			
			return new RealBasedCurves2D(new PhaseSpaceCurvesApproximation(start, vel, 30, 10, 1,
					new CentralForceField(1, 1, new Double(-2).floatValue())));


		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
