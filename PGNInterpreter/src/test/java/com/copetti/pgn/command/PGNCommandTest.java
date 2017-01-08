package com.copetti.pgn.command;

import static org.junit.Assert.*;

import org.junit.Test;

import com.copetti.pgncommon.chess.board.ChessColor;
import com.copetti.pgncommon.chess.board.ChessSquare;
import com.copetti.pgncommon.chess.token.ChessFile;
import com.copetti.pgncommon.chess.token.ChessPiece;
import com.copetti.pgncommon.chess.token.ChessRank;

public class PGNCommandTest {

	@Test
	public void testEquals() {

		MoveCommand moveCommand = new MoveCommand(ChessPiece.BISHOP,
				new ChessSquare(ChessFile.A, ChessRank.of("3").get()));
		MoveCommand moveCommand2 = new MoveCommand(ChessPiece.BISHOP,
				new ChessSquare(ChessFile.A, ChessRank.of("3").get()));

		assertEquals(moveCommand, moveCommand2);
	}

}
