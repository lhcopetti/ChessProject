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
}
