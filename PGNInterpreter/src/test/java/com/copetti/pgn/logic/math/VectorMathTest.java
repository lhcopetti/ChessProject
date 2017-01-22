package com.copetti.pgn.logic.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class VectorMathTest {

	private static final double DELTA = 0.001d;

	@Test
	public void testSubtract() {

		{
			Vector arg1 = new Vector(100.d, 100.d);
			Vector arg2 = new Vector(50.d, 50.d);
			Vector res = VectorMath.subtract(arg1, arg2);
			assertEquals(res.getX(), 50.d, DELTA);
			assertEquals(res.getY(), 50.d, DELTA);
		}

		{
			Vector arg1 = new Vector(80.d, 50.d);
			Vector arg2 = new Vector(120.d, 120.d);
			Vector res = VectorMath.subtract(arg1, arg2);
			assertEquals(res.getX(), -40.d, DELTA);
			assertEquals(res.getY(), -70.d, DELTA);
		}
	}

	@Test
	public void testAdd() {

		{
			Vector arg1 = new Vector(100.d, 100.d);
			Vector arg2 = new Vector(50.d, 50.d);
			Vector res = VectorMath.add(arg1, arg2);
			assertEquals(res.getX(), 150.d, DELTA);
			assertEquals(res.getY(), 150.d, DELTA);
		}

		{
			Vector arg1 = new Vector(80.d, 50.d);
			Vector arg2 = new Vector(120.d, 120.d);
			Vector res = VectorMath.add(arg1, arg2);
			assertEquals(res.getX(), 200.d, DELTA);
			assertEquals(res.getY(), 170.d, DELTA);
		}
	}
}
