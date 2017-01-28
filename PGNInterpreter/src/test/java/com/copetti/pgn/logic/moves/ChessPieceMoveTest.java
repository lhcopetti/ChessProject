package com.copetti.pgn.logic.moves;

import org.junit.Test;

public abstract class ChessPieceMoveTest {

	@Test
	public abstract void getMovesOnEmptyBoardTest();

	@Test
	public abstract void getMovesWithBlockedBoard();

	@Test
	public abstract void getMovesWithCaptureAvailable();

	@Test
	public abstract void getMovesWithNoneAvailable();
}
