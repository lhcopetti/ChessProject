package com.copetti.pgn.integration;

import org.junit.Test;

import com.copetti.pgn.exception.KingIsInCheckException;
import com.copetti.pgn.exception.PGNInterpreterException;

public class TestPinnedMoveCommands {

	@Test
	public void testRookMoveInPinPosition() {

	}

	@Test
	public void testRookMoveOutOfPinPosition() {

	}

	@Test
	public void testBishopMoveOutOfPinPosition() {

	}

	@Test(expected = KingIsInCheckException.class)
	public void testMoveKingIntoCheckPosition() throws PGNInterpreterException {
		String initialBoard = "rnb1kbnr/ppppqppp/8/8/8/8/PPPP1PPP/RNBK1BNR w kq - 0 1";
		FENBoardComparison.tryCommand(initialBoard, "Ke1");
	}

}
