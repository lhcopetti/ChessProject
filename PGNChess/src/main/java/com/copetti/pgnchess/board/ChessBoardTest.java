package com.copetti.pgnchess.board;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.copetti.pgnchess.board.builder.ChessBoardBuilder;
import com.copetti.pgnchess.pieces.ChessColor;
import com.copetti.pgnchess.pieces.King;
import com.copetti.pgnchess.pieces.Pawn;
import com.copetti.pgnchess.pieces.Queen;

public class ChessBoardTest {

	@Test
	public void testToString() {

		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();

		builder.fill(new ChessRank(2)).with(new Pawn(ChessColor.BLACK));
		builder.at(new ChessSquare("d1")).put(new Queen(ChessColor.BLACK));
		builder.at(new ChessSquare("e1")).put(new King(ChessColor.BLACK));

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
