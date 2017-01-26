package com.copetti.pgn.logic.moves;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.copetti.pgn.board.ChessSquare;

public abstract class ChessPieceMoveTest {

	@Test
	public abstract void getMovesOnEmptyBoardTest();

	@Test
	public abstract void getMovesWithBlockedBoard();

	@Test
	public abstract void getMovesWithCaptureAvailable();

	@Test
	public abstract void getMovesWithNoneAvailable();

	public final void assertContains(Set<ChessSquare> moves, ChessSquare c) {
		assertTrue(moves.contains(c));
	}

	public final void assertContains(Set<ChessSquare> moves, String... cs) {
		for (String c : cs)
			assertContains(moves, new ChessSquare(c));
	}

}
