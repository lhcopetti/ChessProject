package com.copetti.pgnchess.math;

import static com.copetti.pgnchess.math.VectorMath.subtract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import one.util.streamex.DoubleStreamEx;

public class UnitVectorInterpoler {

	private final double[] angleArray = DoubleStreamEx //
			.iterate(0d, x -> x + 45) //
			.takeWhile(x -> x < 360) //
			.toArray(); //

	public List<Vector> getIntermediatePoints(Vector origin, Vector target) {

		Vector directionVector = subtract(target, origin).unitify();

		if (Arrays.stream(angleArray).noneMatch(angle -> DoubleMath.equals(angle, directionVector.getAngle(), 0.001d)))
			return null;

		List<Vector> points = new ArrayList<>();

		Vector counter = VectorMath.add(origin, directionVector);
		while (!counter.equals(target)) {
			points.add(new Vector(counter));
			counter.add(directionVector);
		}

		return points;
	}

}
