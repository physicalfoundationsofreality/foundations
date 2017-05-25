package com.gruber.pfr.space.base;

public class QuotientOperation extends AutoOperation {

	Operation baseOperation;
	QuotientProjection projection;

	public QuotientOperation(QuotientProjection projection, Operation baseOperation) {
		
		this.base = projection.getDomain();
		this.baseOperation = baseOperation;
		this.projection = projection;
	}

	public Set operate(Set op1, Set op2) throws OperantException {

		EquivalenceClass cl1 = (EquivalenceClass)op1;
		EquivalenceClass cl2 = (EquivalenceClass)op2;
		
		Set image = this.baseOperation.operate(cl1.getBaseElement(), cl2.baseElement);
		
		return this.projection.getImage(image);
	}
}
