package com.copetti.pgn.integration;

import org.junit.Test;

import com.copetti.pgn.exception.PGNInterpreterException;

public class TestPawnCommand {

	@Test
	public void testInitialPawnMove() throws PGNInterpreterException {
		String initialBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		String finalBoard = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
		String pgnCommand = "e4";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Test
	public void testInitialKnigtMove() throws PGNInterpreterException {
		String initialBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		String finalBoard = "rnbqkbnr/pppppppp/8/8/8/5N2/PPPPPPPP/RNBQKB1R b KQkq - 1 1";
		String pgnCommand = "Nf3";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Test
	public void testBlackPawnMove() throws PGNInterpreterException {
		String initialBoard = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
		String finalBoard = "rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2";
		String pgnCommand = "e5";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Test
	public void testWhiteMoveWithoutEnPassantAvailable() throws PGNInterpreterException {
		String initialBoard = "rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2";
		String finalBoard = "rnbqkbnr/pppp1ppp/8/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";
		String pgnCommand = "Nf3";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Test
	public void testWhitePawnCapture() throws PGNInterpreterException {
		String initialBoard = "rnbqkbnr/ppp2ppp/8/3pp3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq d6 0 3";
		String finalBoard = "rnbqkbnr/ppp2ppp/8/3Pp3/8/5N2/PPPP1PPP/RNBQKB1R b KQkq - 0 3";
		String pgnCommand = "exd5";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Test
	/* https://lichess.org/urXzcfwv */
	public void testBlackPawnCaptureFromRealGame() throws PGNInterpreterException {
		String initialBoard = "rnbq1rk1/pp2ppbp/2pp1np1/3P4/4P3/2NB1N2/PPP2PPP/R1BQ1RK1 b - - 7 7";
		String finalBoard = "rnbq1rk1/pp2ppbp/3p1np1/3p4/4P3/2NB1N2/PPP2PPP/R1BQ1RK1 w - - 7 8";
		String pgnCommand = "cxd5";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Test
	public void testBlackPawnCaptureIsolated() throws PGNInterpreterException {
		String initialBoard = "k7/8/2pp4/3P4/8/8/8/K7 b - - 0 1";
		String finalBoard = "k7/8/3p4/3p4/8/8/8/K7 w - - 0 2";
		String pgnCommand = "cxd5";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}
}
