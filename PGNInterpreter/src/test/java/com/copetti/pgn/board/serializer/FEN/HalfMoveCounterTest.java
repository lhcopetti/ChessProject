package com.copetti.pgn.board.serializer.FEN;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.copetti.pgn.board.HalfMoveCounter;

public class HalfMoveCounterTest {

	private FENSerializer ser;

	@Before
	public void before() {
		ser = new FENSerializer();
	}

	@Test
	public void testEmpty() {
		assertEquals("0", ser.serialize(new HalfMoveCounter()));
	}

	@Test
	public void testTenHalfMoves() {
		assertEquals("10", ser.serialize(new HalfMoveCounter(10)));
	}
}
