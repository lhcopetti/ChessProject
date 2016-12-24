package com.copetti.pgnchess.math;

import static com.copetti.pgnchess.math.VectorMath.subtract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UnitVectorInterpoler {

	private final Double[] angleArray = { 0.d, 45.d, 90.d, 135.d, 180.d, 225.d, 270.d, 315.d };
	private final List<Double> angles = Collections.unmodifiableList(Arrays.asList(angleArray));

	public List<Vector> getIntermediatePoints(Vector origin, Vector target) {

		Vector directionVector = subtract(target, origin).unitify();

		if (angles.stream().noneMatch(angle -> DoubleMath.equals(angle, directionVector.getAngle(), 0.001d)))
			return null;
		
		List<Vector> points = new ArrayList<>();

		Vector counter = VectorMath.add(origin, directionVector);
		while (!counter.equals(target))
		{
			points.add(new Vector(counter));
			counter.add(directionVector);
		}
		
		return points;
	}

}
