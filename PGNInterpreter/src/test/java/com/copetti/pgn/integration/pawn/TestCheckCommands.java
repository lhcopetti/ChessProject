package com.copetti.pgn.integration.pawn;

import org.junit.Test;

import com.copetti.pgn.exception.KingNotInCheckException;
import com.copetti.pgn.exception.PGNInterpreterException;

public class TestCheckCommands {

	@Test
	public void testWhiteCheck() throws PGNInterpreterException {
		String initialBoard = "rnbqkbnr/ppp2ppp/3p4/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 0 3";
		String finalBoard = "rnbqkbnr/ppp2ppp/3p4/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 1 3";
		String pgnCommand = "Bb5+";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Test(expected = KingNotInCheckException.class)
	public void testWhiteCheckCommandWihoutBlackKingInCheck() throws PGNInterpreterException {
		String initialBoard = "rnbqkbnr/pppp2pp/5p2/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 0 3";
		String finalBoard = "rnbqkbnr/pppp2pp/5p2/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 1 3";
		String pgnCommand = "Bb5+";
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}
}
