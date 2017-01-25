package com.copetti.pgn.integration.pawn;

import org.junit.Test;

public class TestPawnCommand {

	@Test
	public void testInitialPawnMove() {
		String initialBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		String finalBoard = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
		String pgnCommand = "e4";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Test
	public void testInitialKnigtMove() {
		String initialBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		String finalBoard = "rnbqkbnr/pppppppp/8/8/8/5N2/PPPPPPPP/RNBQKB1R b KQkq - 1 1";
		String pgnCommand = "Nf3";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}
	
	// @Test
	// public void test() {
	// Assert.fail();
	// }
	//
	// @Test
	// public void test() {
	// Assert.fail();
	// }
	//
	// @Test
	// public void test() {
	// Assert.fail();
	// }
}
