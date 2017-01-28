package com.copetti.pgn.logic.moves;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import com.copetti.pgn.board.ChessSquare;

public class ChessAssertiveLibrary {

	public static final void assertContains(Set<ChessSquare> moves, ChessSquare c) {
		assertTrue(moves.contains(c));
	}

	public static final void assertContains(Set<ChessSquare> moves, String... cs) {
		for (String c : cs)
			assertContains(moves, new ChessSquare(c));
	}

}
