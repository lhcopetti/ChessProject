package com.copetti.pgn.logic.moves.strategies;

import org.junit.Test;

import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.factory.BoardTestFactory;
import com.copetti.pgn.exception.NoPieceSelectedAtSquareException;
import com.copetti.pgn.exception.PGNInterpreterException;

public class ChessMoveExecutorTest {

	@Test(expected = NoPieceSelectedAtSquareException.class)
	public void testNoPieceAtSquare() throws PGNInterpreterException {

		ChessMoveExecutor cme = new ChessMoveExecutor(null);

		String board = //
				"" + //
						"--------" + //
						"--------" + //
						"--------" + //
						"--------" + //
						"--------" + //
						"--------" + //
						"--------" + //
						"--------"; //
		cme.getMoves(new ChessSquare("a1"), BoardTestFactory.createNew(board, ChessColor.WHITE));
	}
}
