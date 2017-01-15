package com.copetti.pgnchess.board;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.copetti.pgnchess.board.builder.ChessBoardBuilder;
import com.copetti.pgnchess.pieces.ChessPiece;
import com.copetti.pgnchess.pieces.ChessPieceType;
import com.copetti.pgncommon.chess.board.ChessBoard;
import com.copetti.pgncommon.chess.board.ChessColor;
import com.copetti.pgncommon.chess.board.ChessSquare;

public class ChessBoardTest {

	@Test
	public void testToString() {

		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();

		builder.fill(new ChessRank(2)).with(new ChessPiece(ChessPieceType.PAWN, ChessColor.BLACK));
		builder.at(new ChessSquare("d1")).put(new ChessPiece(ChessPieceType.QUEEN, ChessColor.BLACK));
		builder.at(new ChessSquare("e1")).put(new ChessPiece(ChessPieceType.KING, ChessColor.BLACK));

		ChessBoard board = builder.build();

		assertEquals("__ __ __ __ __ __ __ __\r\n" + //
				"__ __ __ __ __ __ __ __\r\n" + //
				"__ __ __ __ __ __ __ __\r\n" + //
				"__ __ __ __ __ __ __ __\r\n" + //
				"__ __ __ __ __ __ __ __\r\n" + //
				"__ __ __ __ __ __ __ __\r\n" + //
				"bp bp bp bp bp bp bp bp\r\n" + //
				"__ __ __ bq bk __ __ __", new BoardPrinter().print(board)); //
	}

}
