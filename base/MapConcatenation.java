package com.gruber.pfr.space.base;

public class MapConcatenation implements Map {
	
	public static class MapIncompatibilityException extends Exception {	}
	
	Map map1;
	Map map2;
	
	protected MapConcatenation(Map map1, Map map2) {
		
		this.map1 = map1;
		this.map2 = map2;
	}

	public static Map concatenateMaps(Map map1, Map map2) throws MapIncompatibilityException {
		
		if(!map1.getRange().equals(map2.getDomain()))
				throw new MapIncompatibilityException();
		
		return new MapConcatenation(map1, map2);
	}

	public Set getDomain() {
		
		return map1.getDomain();
	}

	public Set getRange() {
		
		return map2.getRange();
	}

	public Set getImage(Set orig) {

		return map2.getImage(map1.getImage(orig));
	}
}
