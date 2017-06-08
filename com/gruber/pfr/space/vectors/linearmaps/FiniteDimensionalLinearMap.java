package com.gruber.pfr.space.vectors.linearmaps;

import java.util.ArrayList;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVector;
import com.gruber.pfr.space.vectors.FiniteDimensionalVectorSpaceBasis;
import com.gruber.pfr.space.vectors.VectorSpace;
import com.gruber.pfr.space.vectors.VectorSpan;
import com.gruber.pfr.space.vectors.affine.AffineSubspace;

public class FiniteDimensionalLinearMap extends LinearMap {

	// phi(ej) = sum(mij * fi)
	// -> phi(sum(bj * ej)) = phi(bj * sum(mij * fi ))
	// -> phi(b) has coordinates bi = sum( mij bj )
	FiniteMatrix matrix;
	FiniteDimensionalVectorSpaceBasis domain;
	FiniteDimensionalVectorSpaceBasis range;
	VectorSpan kernel;
	VectorSpan image;

	public FiniteDimensionalLinearMap(FiniteDimensionalVectorSpaceBasis domain, FiniteDimensionalVectorSpaceBasis range,
			FiniteMatrix matrix) {

		super(domain.getBaseSpace(), range.getBaseSpace());

		this.matrix = matrix;
		this.range = range;
		this.domain = domain;
	}
//	public 	FiniteDimensionalLinearMap(FiniteDimensionalVectorSpaceBasis basis, FiniteDimensionalVector[] kernelBasis) {
//		
//		
//	}

	public FiniteDimensionalVectorSpaceBasis getDomain() {
		return domain;
	}

	public void setDomain(FiniteDimensionalVectorSpaceBasis domain) {
		this.domain = domain;
	}

	public FiniteDimensionalVectorSpaceBasis getRange() {
		return range;
	}

	public void setRange(FiniteDimensionalVectorSpaceBasis range) {
		this.range = range;
	}

	public FiniteMatrix getMatrix() {
		return matrix;
	}

	public void setMatrix(FiniteMatrix matrix) {
		this.matrix = matrix;
	}

	public FiniteDimensionalVector getImage(FiniteDimensionalVector vec) {

		FiniteDimensionalVector im = (FiniteDimensionalVector) this.range.getBaseSpace().getNullElement();
		for (int i = 0; i < this.range.getBaseVectors().length; i++)
			for (int j = 0; j < this.domain.getBaseVectors().length; j++)
				im = (FiniteDimensionalVector) im.add(this.range.getBaseVectors()[i]
						.multiply(this.matrix.getElement(i, j).multiply(this.domain.getCoordinate(j, vec))));

		return vec;
	}

	public Set getImage(Set set) {

		return this.getImage((FiniteDimensionalVector) set);
	}

	public Set getPreImage(Set image) {

		// normalize bases to semi identity
		this.getImage();
		this.getKernel();

		FiniteDimensionalVector vec = (FiniteDimensionalVector) image;
		FiniteDimensionalVector proj = (FiniteDimensionalVector) ((VectorSpan) this.image).getProjection(vec);
		if (!vec.equals(proj))
			return null;

		FiniteDimensionalVector pre = (FiniteDimensionalVector) this.domain.getBaseSpace().getNullElement();
		for (int i = 0; i < this.image.getDim(); i++) 
			pre = (FiniteDimensionalVector) pre
					.add(this.domain.getBaseVectors()[i].multiply(this.range.getCoordinate(i, vec)));

		return new AffineSubspace(this.domain.getBaseSpace(),pre,this.kernel);
	}

	public Module getKernel() {

		if (this.kernel != null)
			return this.kernel;

		this.triangulateRows();
		this.triangulateColumns();

		ArrayList<FiniteDimensionalVector> ims = new ArrayList<FiniteDimensionalVector>();

		for (int i = 0; i < this.domain.getBaseVectors().length; i++) {
			FiniteDimensionalVector im = this.getImage(this.domain.getBaseVectors()[i]);
			if (im.equals((FiniteDimensionalVector) this.domain.getBaseSpace().getBaseRing().getNullElement()))
				ims.add(im);
		}
		FiniteDimensionalVector[] basis = new FiniteDimensionalVector[ims.size()];
		basis = ims.toArray(basis);
		return new VectorSpan(basis, (VectorSpace) this.domain.getBaseSpace());
	}

	public Module getImage() {

		if (this.image != null)
			return this.image;

		this.triangulateRows();
		this.triangulateColumns();

		ArrayList<FiniteDimensionalVector> ims = new ArrayList<FiniteDimensionalVector>();

		for (int i = 0; i < this.domain.getBaseVectors().length; i++) {
			FiniteDimensionalVector im = this.getImage(this.domain.getBaseVectors()[i]);
			if (!im.equals((FiniteDimensionalVector) this.domain.getBaseSpace().getBaseRing().getNullElement()))
				ims.add(im);
		}
		FiniteDimensionalVector[] basis = new FiniteDimensionalVector[ims.size()];
		basis = ims.toArray(basis);
		return new VectorSpan(basis, (VectorSpace) this.range.getBaseSpace());
	}

	// transform image basis such that matrix has column triangulated form
	public void triangulateColumns() {

		int pos = 0;
		int size = this.range.getBaseVectors().length;
		int colPos = 0;

		while (colPos < size) {

			// find a remaining Vector with map factor not 0
			for (int i = pos; i < size; i++) {
				if (!this.matrix.getElement(colPos, i).equals(this.matrix.baseRing.getNullElement())) {
					this.range.resortBasis(pos, i);
					this.matrix.exchangeColumns(pos, i);
					break;
				}
			}
			if (this.matrix.getElement(colPos, pos).equals(this.matrix.getBaseRing().getNullElement())) {
				colPos++;
				continue;
			}

			RingElement factor = matrix.getElement(colPos, pos).getInverse().getNegative();
			// adapt the rest of the vectors and matrix rows to to factor 0 at
			// pos
			for (int i = pos + 1; i < size; i++) {
				this.range.modifyVector(i, pos, matrix.getElement(i, pos - 1).multiply(factor));
				this.matrix.addColumn(i, pos, factor);
			}
			this.range.getBaseVectors()[pos] = (FiniteDimensionalVector)this.range.getBaseVectors()[pos].multiply(matrix.getElement(pos, colPos).getInverse());
			pos++;
			colPos++;
		}
	}

	// transform domain basis such that matrix has row triangulated form
	public void triangulateRows() {

		int pos = 0;
		int size = this.domain.getBaseVectors().length;
		int colPos = 0;

		while (colPos < size) {

			// find a remaining Vector with map factor not 0
			for (int i = pos; i < size; i++) {
				if (!this.matrix.getElement(i, colPos).equals(this.matrix.getBaseRing().getNullElement())) {
					this.domain.resortBasis(i, pos);
					this.matrix.exchangeRows(i, pos);
					break;
				}
			}
			if (this.matrix.getElement(pos, colPos).equals(this.matrix.getBaseRing().getNullElement())) {
				colPos++;
				continue;
			}

			RingElement factor = matrix.getElement(pos, colPos).getInverse().getNegative();
			// adapt the rest of the vectors and matrix rows to to factor 0 at
			// pos
			for (int i = pos + 1; i < size; i++) {
				this.domain.modifyVector(i, pos, matrix.getElement(i, pos - 1).multiply(factor));
				this.matrix.addRow(i, pos, factor);
			}
			this.domain.getBaseVectors()[pos] = (FiniteDimensionalVector)this.domain.getBaseVectors()[pos].multiply(matrix.getElement(pos, colPos).getInverse());
			pos++;
			colPos++;
		}
	}
}
