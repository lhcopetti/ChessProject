package com.copetti.pgn.integration;

import org.junit.Test;

import com.copetti.pgn.exception.InvalidPromotionPieceException;
import com.copetti.pgn.exception.PGNInterpreterException;

public class TestPromotionCommand {

	@Test
	public void testPromotionCommandForWhite() throws PGNInterpreterException {
		String initialBoard = "1n3bnr/ppPppppp/7k/8/8/8/PPPP1PPP/RNBQKBNR w KQ - 0 1";
		String endBoard = "1Q3bnr/pp1ppppp/7k/8/8/8/PPPP1PPP/RNBQKBNR b KQ - 0 1";
		FENBoardComparison.validate(initialBoard, endBoard, "cxb8=Q");
	}

	@Test
	public void testPromotionCommandForBlack() throws PGNInterpreterException {
		String initialBoard = "rnbqkbnr/1ppppppp/8/8/8/8/pPPP1PPP/4KBNR b Kkq - 0 1";
		String endBoard = "rnbqkbnr/1ppppppp/8/8/8/8/1PPP1PPP/r3KBNR w Kkq - 0 2";
		FENBoardComparison.validate(initialBoard, endBoard, "a1=R+");
	}

	@Test
	public void testPromotingToQueen() throws PGNInterpreterException {
		String initialBoard = "r2qk3/2ppppPp/8/n3n3/8/2P5/pPKP1P1P/4rBNR w q - 0 1";
		String endBoard = "r2qk1R1/2pppp1p/8/n3n3/8/2P5/pPKP1P1P/4rBNR b q - 0 1";
		FENBoardComparison.validate(initialBoard, endBoard, "g8=R#");
	}

	@Test
	public void testPromotingToKnight() throws PGNInterpreterException {

		String initialBoard = "r2qk3/2pppppp/8/n3n3/8/2P5/pPKP1PPP/4rBNR b q - 0 1";
		String endBoard = "r2qk3/2pppppp/8/n3n3/8/2P5/1PKP1PPP/n3rBNR w q - 0 2";
		FENBoardComparison.validate(initialBoard, endBoard, "a1=N#");
	}

	@Test(expected = InvalidPromotionPieceException.class)
	public void testPromotingToKing() throws PGNInterpreterException {
		String initialBoard = "r2qk3/2pppppp/8/n3n3/8/2P5/pPKP1PPP/4rBNR b q - 0 1";
		FENBoardComparison.tryCommand(initialBoard, "a1=K#");
	}

	@Test(expected = InvalidPromotionPieceException.class)
	public void testPromotingToPawn() throws PGNInterpreterException {
		String initialBoard = "r2qk3/2pppppp/8/n3n3/8/2P5/pPKP1PPP/4rBNR b q - 0 1";
		FENBoardComparison.tryCommand(initialBoard, "a1=K#");
	}
}
