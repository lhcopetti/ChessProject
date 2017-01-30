package com.copetti.pgn.integration;

import org.junit.Test;

import com.copetti.pgn.exception.KingIsInCheckException;
import com.copetti.pgn.exception.PGNInterpreterException;

public class TestPinnedMoveCommands {

	@Test
	public void testRookMoveInPinPosition() throws PGNInterpreterException {

		String initialBoard = "rnbb2k1/6r1/8/8/Q7/6R1/PPPP1PPP/RNBK1BN1 b - - 0 1";
		String finalBoard = "rnbb2k1/8/8/8/Q5r1/6R1/PPPP1PPP/RNBK1BN1 w - - 1 2";
		String command = "Rg4";
		FENBoardComparison.validate(initialBoard, finalBoard, command);

	}

	@Test(expected = KingIsInCheckException.class)
	public void testRookMoveOutOfPinPosition() throws PGNInterpreterException {
		String initialBoard = "rnbb2k1/8/4r3/8/8/1B6/PPPP1PPP/RNBK2NR b - - 0 1";
		String command = "Re1+";
		FENBoardComparison.tryCommand(initialBoard, command);
	}

	@Test(expected = KingIsInCheckException.class)
	public void testBishopMoveOutOfPinPosition() throws PGNInterpreterException {

		String inititalBoard = "rnb1k1nr/8/4b3/8/4Q3/8/PPPP1PPP/RNBK1BNR b kq - 0 1";
		String command = "Bg4+";
		FENBoardComparison.tryCommand(inititalBoard, command);

	}

	@Test(expected = KingIsInCheckException.class)
	public void testMoveKingIntoCheckPosition() throws PGNInterpreterException {
		String initialBoard = "rnb1kbnr/ppppqppp/8/8/8/8/PPPP1PPP/RNBK1BNR w kq - 0 1";
		FENBoardComparison.tryCommand(initialBoard, "Ke1");
	}

}
