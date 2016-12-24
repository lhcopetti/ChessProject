package com.copetti.pgnchess.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class VectorTest {

	private static final double DELTA = 0.01d;

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
	public void testAdd() {

		Vector origin = new Vector(50.d, 25.d);

		origin.add(new Vector(10.d, 10.d));
		assertEquals(60.d, origin.getX(), DELTA);
		assertEquals(35.d, origin.getY(), DELTA);

		origin.add(new Vector(40.d, 15.d));
		assertEquals(100.d, origin.getX(), DELTA);
		assertEquals(50.d, origin.getY(), DELTA);

		origin.add(new Vector(12.d, 21.d));
		assertEquals(112.d, origin.getX(), DELTA);
		assertEquals(71.d, origin.getY(), DELTA);
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

	@Test
	public void testAngle() {
		assertEquals(45.d, new Vector(50.d, 50.d).getAngle(), DELTA);
		assertEquals(90.d, new Vector(0.d, 2.d).getAngle(), DELTA);
		assertEquals(60.75d, new Vector(14.d, 25.d).getAngle(), DELTA);
		
		assertEquals(315.d, new Vector(1.d, -1.d).getAngle(), DELTA);
		assertEquals(225.d, new Vector(-1.d, -1.d).getAngle(), DELTA);
		assertEquals(0.d, new Vector(1.d, 0.d).getAngle(), DELTA);
	}

	@Test
	public void testGetX() {
		assertEquals(2.5d, new Vector(2.5d, 5.5d).getX(), DELTA);
	}

	@Test
	public void testGetY() {
		assertEquals(5.5d, new Vector(2.5d, 5.5d).getY(), DELTA);
	}

	@Test
	public void testEquals() {
		assertEquals(new Vector(5.5d, 5.5d), new Vector(5.5d, 5.5d));
		assertEquals(new Vector(0.5d, -1.5d), new Vector(0.5d, -1.5d));
	}

	@Test
	public void testHashCode() {
		assertEquals(new Vector(5.5d, 5.5d).hashCode(), new Vector(5.5d, 5.5d).hashCode());
		assertEquals(new Vector(0.5d, -1.5d).hashCode(), new Vector(0.5d, -1.5d).hashCode());
	}
	
	@Test
	public void testUnitify() {

		assertEquals(new Vector(1.d, 1.d), new Vector(7.d, 7.d).unitify());
		assertEquals(new Vector(-1.d, 1.d), new Vector(-7.d, 7.d).unitify());
		assertEquals(new Vector(1.d, -1.d), new Vector(7.d, -7.d).unitify());
		assertEquals(new Vector(-1.d, -1.d), new Vector(-7.d, -7.d).unitify());
		
		assertEquals(new Vector(1.d, 0.d), new Vector(5.d, 0.d).unitify());
		assertEquals(new Vector(-1.d, 0.d), new Vector(-5.d, 0.d).unitify());
		assertEquals(new Vector(0.d, 1.d), new Vector(0.d, 10.d).unitify());
		assertEquals(new Vector(0.d, -1.d), new Vector(0.d, -10.d).unitify());
	}

	@Test
	public void testToString() {
		
		assertEquals("[1.0,2.0]", new Vector(1.d, 2.d).toString());
		assertEquals("[-1.0,-2.0]", new Vector(-1.d, -2.d).toString());
	}
}
