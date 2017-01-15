package com.copetti.pgnchess.pieces.moves;

import java.util.Set;

import org.junit.Test;

import com.copetti.pgncommon.chess.board.ChessSquare;

import static org.junit.Assert.*;

public abstract class ChessPieceMoveTest {

	@Test
	public abstract void getMovesOnEmptyBoardTest();

	@Test
	public abstract void getMovesWithBlockedBoard();

	@Test
	public abstract void getMovesWithCaptureAvailable();

	@Test
	public abstract void getMovesWithNoneAvailable();
	
	public final void assertContains(Set<ChessSquare> moves, ChessSquare c)
	{
		assertTrue(moves.contains(c));
	}

}
