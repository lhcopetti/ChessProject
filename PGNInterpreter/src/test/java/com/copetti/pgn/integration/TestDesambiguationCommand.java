package com.copetti.pgn.integration;

import org.junit.Test;

import com.copetti.pgn.exception.PGNInterpreterException;

public class TestDesambiguationCommand {

	/*
	 * http://chess.stackexchange.com/questions/1817/how-are-pgn-ambiguities-
	 * handled
	 */
	@Test
	public void testAmbiguousQueenCaptureOnEFile_WithQueenAtG8() throws PGNInterpreterException {
		String initialBoard = "kq4QQ/6nQ/8/8/8/8/PPP1PPPP/RNB1KBNR w KQ - 0 1";
		String finalBoard = "kq5Q/6QQ/8/8/8/8/PPP1PPPP/RNB1KBNR b KQ - 1 1";
		String command = "Qgxg7";
		FENBoardComparison.validate(initialBoard, finalBoard, command);
	}

	@Test
	public void testAmbiguousQueenCaptureOnEFile_WithQueenAtH7() throws PGNInterpreterException {
		String initialBoard = "kq4QQ/6nQ/8/8/8/8/PPP1PPPP/RNB1KBNR w KQ - 0 1";
		String finalBoard = "kq4QQ/6Q1/8/8/8/8/PPP1PPPP/RNB1KBNR b KQ - 1 1";
		String command = "Q7xg7";
		FENBoardComparison.validate(initialBoard, finalBoard, command);
	}

	@Test
	public void testAmbiguousQueenCaptureOnEFile_WithQueenAtH8() throws PGNInterpreterException {
		String initialBoard = "kq4QQ/6nQ/8/8/8/8/PPP1PPPP/RNB1KBNR w KQ - 0 1";
		String finalBoard = "kq4Q1/6QQ/8/8/8/8/PPP1PPPP/RNB1KBNR b KQ - 1 1";
		String command = "Qh8xg7";
		FENBoardComparison.validate(initialBoard, finalBoard, command);
	}

	@Test
	public void testAmbiguousRookMoveInFile() throws PGNInterpreterException {
		String initialBoard = "R5n1/6pp/8/b6k/8/8/3PPPPP/R4BNK w - - 0 1";
		String finalBoard = "6n1/6pp/8/R6k/8/8/3PPPPP/R4BNK b - - 1 1";
		String command = "R8xa5+";
		FENBoardComparison.validate(initialBoard, finalBoard, command);
	}

	@Test
	public void testAmbiguousRookMoveInRank() throws PGNInterpreterException {
		String initialBoard = "3k2n1/6pp/8/R2b3R/8/8/3PPPPP/5BNK w - - 0 1";
		String finalBoard = "3k2n1/6pp/8/R2R4/8/8/3PPPPP/5BNK b - - 1 1";
		String command = "Rhxd5+";
		FENBoardComparison.validate(initialBoard, finalBoard, command);
	}
}
