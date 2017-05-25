package com.gruber.pfr.graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.gruber.pfr.graphics.GraphWizard.FunctionType;
import com.gruber.pfr.graphics.visualizations.Polynomial;

public class Main {

	public static void main(String[] args) {

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
}
