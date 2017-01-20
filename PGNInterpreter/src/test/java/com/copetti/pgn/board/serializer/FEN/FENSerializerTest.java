package com.copetti.pgn.board.serializer.FEN;

import static org.junit.Assert.*;

import org.junit.Test;

import com.copetti.pgn.board.FullMoveCounter;

public class FENSerializerTest {

	@Test
	public void testEmpty() {
		assertEquals("1", new FENSerializer().serialize(new FullMoveCounter()));
	}

	@Test
	public void testTenFullMove() {
		assertEquals("10", new FENSerializer().serialize(new FullMoveCounter(10)));
	}
}
