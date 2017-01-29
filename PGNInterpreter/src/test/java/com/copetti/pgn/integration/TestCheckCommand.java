package com.copetti.pgn.integration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.copetti.pgn.exception.KingIsInCheckException;
import com.copetti.pgn.exception.KingNotCheckmatedException;
import com.copetti.pgn.exception.KingNotInCheckException;
import com.copetti.pgn.exception.PGNInterpreterException;

public class TestCheckCommand extends TestChessCommandCheckFlag {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

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

	@Test
	public void testWhiteCheckWithoutCheckFlag() throws PGNInterpreterException {
		expectedEx.expect(KingIsInCheckException.class);
		expectedEx.expectMessage("Black king is in check");
		String initialBoard = "rnbqkbnr/ppp2ppp/3p4/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 0 3";
		String finalBoard = "rnbqkbnr/ppp2ppp/3p4/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 1 3";
		String pgnCommand = "Bb5"; // as opposed to Bb5+
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Override
	public void testWithoutFlag() throws PGNInterpreterException {
		expectedEx.expect(KingIsInCheckException.class);
		expectedEx.expectMessage("Black king is in check");
		String initialBoard = "rnbqkbnr/ppp2ppp/3p4/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 0 3";
		String finalBoard = "rnbqkbnr/ppp2ppp/3p4/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 1 3";
		String pgnCommand = "Bb5"; // as opposed to Bb5+
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Override
	public void testWithCheckFlag() throws PGNInterpreterException {
		String initialBoard = "rnbqkbnr/ppp2ppp/3p4/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 0 3";
		String finalBoard = "rnbqkbnr/ppp2ppp/3p4/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 1 3";
		String pgnCommand = "Bb5+"; // as opposed to Bb5+
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	@Override
	public void testWithMateFlag() throws PGNInterpreterException {
		expectedEx.expect(KingNotCheckmatedException.class);
		String initialBoard = "rnbqkbnr/ppp2ppp/3p4/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 0 3";
		String finalBoard = "rnbqkbnr/ppp2ppp/3p4/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R b KQkq - 1 3";
		String pgnCommand = "Bb5#"; // as opposed to Bb5+
		FENBoardComparison.validate(initialBoard, finalBoard, pgnCommand);
	}

	// @Test
	// public void testBlackCheckButWhiteIsCheckmated() throws
	// PGNInterpreterException {
	// expectedEx.expect(KingIsCheckmatedException.class);
	// expectedEx.expectMessage("Black is checkmated");
	//
	// String initialBoard =
	// "rnb1k1nr/pppp1ppp/4p3/1Nb5/4P2q/3P4/PPP2PPP/R1BQKBNR b KQkq - 0 4";
	// String pgnCommand = "Qxf7+";
	// FENBoardComparison.tryCommand(initialBoard, pgnCommand);
	// }

}
