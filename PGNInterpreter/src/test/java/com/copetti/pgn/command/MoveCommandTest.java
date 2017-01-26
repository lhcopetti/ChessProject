package com.copetti.pgn.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.factory.BoardTestFactory;
import com.copetti.pgn.command.ChessCommand.CheckFlag;
import com.copetti.pgn.exception.PGNInterpreterException;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class MoveCommandTest {

	@Test
	public void testMoveForBishop() throws PGNInterpreterException {

		String board = //
				"" + //
						"--------" + //
						"--------" + //
						"--------" + //
						"--------" + //
						"--------" + //
						"--------" + //
						"B-------" + //
						"--------"; //
		ChessBoard cb = BoardTestFactory.createNew(board, ChessColor.WHITE);
		MoveCommand move = new MoveCommand(ChessPiece.BISHOP, new ChessSquare("c4"), CheckFlag.FLAG_NONE);
		ChessBoard newCB = move.execute(cb);

		assertEquals(new ColoredChessPiece(ChessPiece.BISHOP, ChessColor.WHITE), newCB.at(new ChessSquare("c4")));
		assertEquals(null, newCB.at(new ChessSquare("a2")));
	}

}
