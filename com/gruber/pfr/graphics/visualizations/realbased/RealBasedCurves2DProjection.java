package com.gruber.pfr.graphics.visualizations.realbased;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gruber.pfr.graphics.Visualization2D;
import com.gruber.pfr.graphics.elements.Coordinates2D;
import com.gruber.pfr.graphics.elements.RealBasedVector;
import com.gruber.pfr.graphics.elements.SimpleVector;
import com.gruber.pfr.space.numbers.real.RealNumber;
import com.gruber.pfr.space.numbers.real.RealVector;
import com.gruber.pfr.space.numbers.real.RnSpace;
import com.gruber.pfr.space.vectors.knspaces.KnVector.InvalidElementsException;
import com.gruber.pfr.space.vectors.linearmaps.FiniteDimensionalLinearMap;
import com.gruber.pfr.space.vectors.linearmaps.FiniteMatrix;

public class RealBasedCurves2DProjection implements Visualization2D {

	RealBasedCurves curves;
	FiniteDimensionalLinearMap projection;

	public RealBasedCurves2DProjection(RealBasedCurves curves, FiniteMatrix projectionMatrix) {
		this.curves = curves;
		this.projection = new FiniteDimensionalLinearMap(RnSpace.getInstance(2).getStandardBasis(),
				RnSpace.getInstance(projectionMatrix.getRownNumber()).getStandardBasis(), projectionMatrix);
	}

	public List<List<SimpleVector>> getCurves() {

		List<List<RealBasedVector>> realCurves = this.curves.getRealCurves();
		List<List<SimpleVector>> curves = new ArrayList<List<SimpleVector>>();

		Iterator<List<RealBasedVector>> curvesIter = realCurves.iterator();
		while (curvesIter.hasNext()) {
			ArrayList<SimpleVector> curve = new ArrayList<SimpleVector>();

			Iterator<RealBasedVector> curveIter = curvesIter.next().iterator();
			while (curveIter.hasNext()) {
				RealBasedVector vector = curveIter.next();
				vector.setDirection((RealVector)projection.getImage(vector.getDirection()));
				vector.setOrigin((RealVector)projection.getImage(vector.getOrigin()));
				curve.add(vector.asVector());
			}

			curves.add(curve);
		}
		return curves;
	}

	public Coordinates2D getCoordinates() {

		try {
			int[] min = this.curves.coord.getMin();
			RealNumber[] nums = new RealNumber[min.length];
			for (int i = 0; i < min.length; i++)
				nums[i] = new RealNumber(min[i]);

			RealVector vec = new RealVector(nums);
			vec = (RealVector) this.projection.getImage(vec);
			nums = vec.getElements();

			int minX = new Double(Math.floor(new Double(nums[0].getBase()).doubleValue())).intValue();
			int minY = new Double(Math.floor(new Double(nums[1].getBase()).doubleValue())).intValue();

			int[] max = this.curves.coord.getMax();
			nums = new RealNumber[max.length];
			for (int i = 0; i < max.length; i++)
				nums[i] = new RealNumber(max[i]);

			vec = new RealVector(nums);
			vec = (RealVector) this.projection.getImage(vec);
			nums = vec.getElements();

			int maxX = new Double(Math.floor(new Double(nums[0].getBase()).doubleValue())).intValue();
			int maxY = new Double(Math.floor(new Double(nums[1].getBase()).doubleValue())).intValue();

			return new Coordinates2D(minX, maxX, minY, maxY);
		} catch (InvalidElementsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setDisplayParameters(int granularity) {
		this.curves.setDisplayParameters(granularity);
	}
}
