package com.copetti.pgn.logic.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleMathTest {

	@Test
	public void testEqualsDoubleDoubleDouble() {
		assertTrue(DoubleMath.equals(5.5d, 4.5d, 1.5d));
		assertFalse(DoubleMath.equals(5.5d, 4.5d, 0.5d));
		
		assertTrue(DoubleMath.equals(-74.d, -64.d, 11.d));
		assertFalse(DoubleMath.equals(-74.d, -64.d, 8.d));
	}

}
