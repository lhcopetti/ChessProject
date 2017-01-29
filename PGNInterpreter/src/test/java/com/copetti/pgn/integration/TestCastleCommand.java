package com.copetti.pgn.integration;

import org.junit.Test;

import com.copetti.pgn.exception.CantCastleKingSideException;
import com.copetti.pgn.exception.CantCastleQueenSideException;
import com.copetti.pgn.exception.PGNInterpreterException;

public class TestCastleCommand {

	@Test
	public void testWhiteShortCastle() throws PGNInterpreterException {
		String initialBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w KQkq - 0 1";
		String finalBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R4RK1 b kq - 1 1";
		String pgnCommand = "O-O";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Test
	public void testBlackShortCastle() throws PGNInterpreterException {
		String initialBoard = "r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1";
		String finalBoard = "r4rk1/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQ - 1 2";
		String pgnCommand = "O-O";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Test
	public void testWhiteLongCastle() throws PGNInterpreterException {
		String initialBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w KQkq - 0 1";
		String finalBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/2KR3R b kq - 1 1";
		String pgnCommand = "O-O-O";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Test
	public void testBlackLongCastle() throws PGNInterpreterException {
		String initialBoard = "r3k2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1";
		String finalBoard = "2kr3r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQ - 1 2";
		String pgnCommand = "O-O-O";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Test(expected = CantCastleKingSideException.class)
	public void testRookMoveInvalidatesCastlingShortForWhite() throws PGNInterpreterException {

		String initialBoard = "rnbqkbnr/pppppp2/8/6pp/8/8/PPPPPPPP/R3K2R w Qkq - 0 1";
		String pgnCommand = "O-O";
		FENBoardComparison.tryCommand(initialBoard, pgnCommand);
	}

	@Test(expected = CantCastleQueenSideException.class)
	public void testRookMoveInvalidatesCastlingLongForBlack() throws PGNInterpreterException {
		String initialBoard = "r3k2r/pppppppp/8/8/8/1PP5/P2PPPPP/RNBQKBNR b KQk - 0 3";
		String pgnCommand = "O-O-O";

		FENBoardComparison.tryCommand(initialBoard, pgnCommand);
	}
}
