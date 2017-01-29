package com.copetti.pgn.integration;

import org.junit.Assert;
import org.junit.Test;

import com.copetti.pgn.exception.KingNotCheckmatedException;
import com.copetti.pgn.exception.PGNInterpreterException;

public class TestMateCommand {

	@Test
	public void testCheckMateWithPawnPromotionToKnight() throws PGNInterpreterException {

		String initialBoard = "r2qk3/2pppppp/8/n3n3/8/2P5/pPKP1PPP/4rBNR b q - 0 1";
		String endBoard = "r2qk3/2pppppp/8/n3n3/8/2P5/1PKP1PPP/n3rBNR w q - 0 2";
		FENBoardComparison.validate(initialBoard, endBoard, "a1=N#");
	}

	@Test(expected = KingNotCheckmatedException.class)
	public void testAttemptToMateWithPawnPromotionToKnightWithOnlyOneMoveAvailable() throws PGNInterpreterException {

		String initialBoard = "r2qk3/2pppppp/8/n2n4/8/2P5/pPKP1PPP/4rBNR b q - 0 1";
		String endBoard = "r2qk3/2pppppp/8/n3n3/8/2P5/1PKP1PPP/n3rBNR w q - 0 2";
		FENBoardComparison.validate(initialBoard, endBoard, "a1=N#");
	}

	@Test
	public void testAttemptToMateWithQueenMoveWithOnlyOneMoveAvailable_BlockWithAPawn() throws PGNInterpreterException {

		try {
			String initialBoard = "r3k3/q1pppppp/8/4n3/8/2P5/1PKP1PPP/4rBNR b q - 0 1";
			FENBoardComparison.tryCommand(initialBoard, "Qa4#");
			Assert.fail("This should throw an exception");

		} catch (PGNInterpreterException e) {
			Assert.assertEquals("The Black king is not checkmated. PAWN(b2) -> b3 is a valid move.", e.getMessage());
		}
	}

	@Test
	public void testEpauletteMate() throws PGNInterpreterException {

		String initialBoard = "5rkr/8/Q7/8/8/8/8/6K1 w - - 0 1";
		String finalBoard = "5rkr/8/6Q1/8/8/8/8/6K1 b - - 1 1";
		FENBoardComparison.validate(initialBoard, finalBoard, "Qg6#");
	}

	@Test(expected = KingNotCheckmatedException.class)
	public void testAttemptMateWithKnightCheck() throws PGNInterpreterException {

		String initialBoard = "2N2rkr/8/Q7/8/8/8/8/6K1 w - - 0 1";
		FENBoardComparison.tryCommand(initialBoard, "Ne7#");
	}

}
