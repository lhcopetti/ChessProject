package com.copetti.pgn.logic.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class UnitVectorInterpolerTest {

	private UnitVectorInterpoler interpoler = new UnitVectorInterpoler();

	@Test
	public void testGetIntermediatePointsHorizontal() {

		Vector origin = new Vector(0, 0);
		Vector target = new Vector(7, 0);

		List<Vector> intermediate = interpoler.getIntermediatePoints(origin, target);

		assertTrue(intermediate.contains(new Vector(1, 0)));
		assertTrue(intermediate.contains(new Vector(2, 0)));
		assertTrue(intermediate.contains(new Vector(3, 0)));
		assertTrue(intermediate.contains(new Vector(4, 0)));
		assertTrue(intermediate.contains(new Vector(5, 0)));
		assertTrue(intermediate.contains(new Vector(6, 0)));
		assertEquals(6, intermediate.size());

	}

	@Test
	public void testGetIntermediatePointsVertical() {

		Vector origin = new Vector(3, 0);
		Vector target = new Vector(7, 0);

		List<Vector> intermediate = interpoler.getIntermediatePoints(origin, target);

		assertTrue(intermediate.contains(new Vector(4, 0)));
		assertTrue(intermediate.contains(new Vector(5, 0)));
		assertTrue(intermediate.contains(new Vector(6, 0)));
		assertEquals(3, intermediate.size());
	}

	@Test
	public void testGetIntermediatePointsMainDiagonal() {

		Vector origin = new Vector(0, 0);
		Vector target = new Vector(7, 7);

		List<Vector> intermediate = interpoler.getIntermediatePoints(origin, target);

		assertTrue(intermediate.contains(new Vector(1, 1)));
		assertTrue(intermediate.contains(new Vector(2, 2)));
		assertTrue(intermediate.contains(new Vector(3, 3)));
		assertTrue(intermediate.contains(new Vector(4, 4)));
		assertTrue(intermediate.contains(new Vector(5, 5)));
		assertTrue(intermediate.contains(new Vector(6, 6)));
		assertEquals(6, intermediate.size());
	}

	@Test
	public void testGetIntermediatePointsDiagonal() {

		Vector origin = new Vector(7, 0);
		Vector target = new Vector(0, 7);

		List<Vector> intermediate = interpoler.getIntermediatePoints(origin, target);

		assertTrue(intermediate.contains(new Vector(6, 1)));
		assertTrue(intermediate.contains(new Vector(5, 2)));
		assertTrue(intermediate.contains(new Vector(4, 3)));
		assertTrue(intermediate.contains(new Vector(3, 4)));
		assertTrue(intermediate.contains(new Vector(2, 5)));
		assertTrue(intermediate.contains(new Vector(1, 6)));
		assertEquals(6, intermediate.size());
	}

	@Test
	public void testGetIntermediatePointsMinorDiagonal() {

		Vector origin = new Vector(4, 3);
		Vector target = new Vector(3, 4);

		List<Vector> intermediate = interpoler.getIntermediatePoints(origin, target);
		assertEquals(0, intermediate.size());
	}

	@Test
	public void testGetIntermediatePointsInvalidKnight() {

		Vector origin = new Vector(5, 2);
		Vector target = new Vector(4, 4);

		List<Vector> intermediate = interpoler.getIntermediatePoints(origin, target);
		assertEquals(null, intermediate);
	}

	@Test
	public void testGetIntermediatePointsInvalidLongKnight() {
		Vector origin = new Vector(5, 2);
		Vector target = new Vector(3, 6);

		List<Vector> intermediate = interpoler.getIntermediatePoints(origin, target);
		assertEquals(null, intermediate);
	}
}
