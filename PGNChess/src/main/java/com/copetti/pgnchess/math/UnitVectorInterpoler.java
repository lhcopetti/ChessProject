package com.copetti.pgnchess.math;

import java.awt.Point;
import java.util.List;

import static com.copetti.pgnchess.math.VectorMath.*;

public class UnitVectorInterpoler {

	public static List<Vector> getIntermediatePoints(Vector origin, Vector target) {

		Vector directionVector = subtract(target, origin).normalize();
		
		return null;
	}

}
