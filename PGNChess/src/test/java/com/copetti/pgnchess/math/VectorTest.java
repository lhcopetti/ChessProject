package com.copetti.pgnchess.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class VectorTest {

	private static final double DELTA = 0.001d;

	@Test
	public void testSubtract() {

		Vector origin = new Vector(50.d, 25.d);

		origin.subtract(new Vector(10.d, 10.d));
		assertEquals(40.d, origin.getX(), DELTA);
		assertEquals(15.d, origin.getY(), DELTA);

		origin.subtract(new Vector(40.d, 15.d));
		assertEquals(0.d, origin.getX(), DELTA);
		assertEquals(0.d, origin.getY(), DELTA);

		origin.subtract(new Vector(12.d, 21.d));
		assertEquals(-12.d, origin.getX(), DELTA);
		assertEquals(-21.d, origin.getY(), DELTA);
	}

	@Test
	public void testNormalize() {

		{
			Vector norm = new Vector(50.d, 50.d);
			norm.normalize();

			assertEquals(norm.size(), 1.d, 0.001);
			assertEquals(norm.getX(), 1 / Math.sqrt(2), DELTA);
			assertEquals(norm.getY(), 1 / Math.sqrt(2), DELTA);
		}

		{
			/*
			 * https://www.wolframalpha.com/input/?i=normalize+the+vector+(14,
			 * 25)
			 */
			Vector norm = new Vector(14.d, 25.d);
			norm.normalize();

			assertEquals(norm.getX(), 14 / Math.sqrt(821), DELTA);
			assertEquals(norm.getY(), 25 / Math.sqrt(821), DELTA);
		}

	}

	public void testAngle() {
		assertEquals(45.d, new Vector(50.d, 50.d).getAngle(), DELTA);
		assertEquals(90.d, new Vector(0.d, 2.d).getAngle(), DELTA);
		assertEquals(60.75d, new Vector(14.d, 25.d).getAngle(), DELTA);
	}

	@Test
	public void testGetX() {
		assertEquals(2.5d, new Vector(2.5d, 5.5d).getX(), DELTA);
	}

	@Test
	public void testGetY() {
		assertEquals(5.5d, new Vector(2.5d, 5.5d).getY(), DELTA);
	}

}
