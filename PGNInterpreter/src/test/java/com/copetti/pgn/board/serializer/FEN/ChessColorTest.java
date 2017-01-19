package com.copetti.pgn.board.serializer.FEN;

import static org.junit.Assert.*;

import org.junit.Test;

import com.copetti.pgn.board.ChessColor;

public class ChessColorTest {

	@Test
	public void testNextToPlayIsBlack() {
		assertEquals("b", new FENSerializer().serialize(ChessColor.BLACK));
	}

	@Test
	public void testNextToPlayIsWhite() {
		assertEquals("w", new FENSerializer().serialize(ChessColor.WHITE));
	}
}
