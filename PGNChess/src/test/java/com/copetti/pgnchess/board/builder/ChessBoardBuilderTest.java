package com.copetti.pgnchess.board.builder;

import static org.junit.Assert.*;

import org.junit.Test;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessFile;
import com.copetti.pgnchess.board.ChessRank;
import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.pieces.ChessColor;
import com.copetti.pgnchess.pieces.ChessPiece;
import com.copetti.pgnchess.pieces.ChessPieceType;
import com.copetti.pgnchess.pieces.Pawn;

public class ChessBoardBuilderTest {

	@Test
	public void testEmptyBoard() {
		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();

		ChessBoard board = builder.build();
		assertTrue(board.isEmpty());

		builder.at(new ChessSquare(0, 0)).put(new Pawn(ChessColor.WHITE)).build();
		assertFalse(board.isEmpty());
	}

	@Test
	public void testPutHorse() {

		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();
		ChessBoard board = builder.at(new ChessSquare(0, 0)).put(new Pawn(ChessColor.WHITE)).build();
		assertFalse(board.isEmpty());
	}

	@Test
	public void testFillRank() {

		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();

		ChessBoard board = builder.fill(new ChessRank(2)).with(new Pawn(ChessColor.WHITE)).build();

		for (ChessFile c : ChessFile.values()) {
			ChessPiece piece = board.at(c, new ChessRank(2));
			assertTrue(piece.getChessPieceType() == ChessPieceType.PAWN);
			assertTrue(piece.getColor() == ChessColor.WHITE);
		}
	}

	@Test
	public void testFillFile() {

		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();

		ChessBoard board = builder.fill(ChessFile.E).with(new Pawn(ChessColor.BLACK)).build();

		for (int i = 1; i <= 8; i++) {
			ChessPiece piece = board.at(ChessFile.E, new ChessRank(i));
			assertTrue(piece.getChessPieceType() == ChessPieceType.PAWN);
			assertTrue(piece.getColor() == ChessColor.BLACK);
		}
	}

	@Test
	public void testFillAndPut() {

		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();
		ChessBoard board = builder.fill(new ChessRank(2)).with(new Pawn(ChessColor.WHITE)).build();
		builder.at(new ChessSquare(ChessFile.E, new ChessRank(4))).put(new Pawn(ChessColor.BLACK));
		builder.at(new ChessSquare(ChessFile.H, new ChessRank(8))).put(new Pawn(ChessColor.WHITE));

		for (ChessFile c : ChessFile.values()) {
			ChessPiece piece = board.at(c, new ChessRank(2));
			assertTrue(piece.getChessPieceType() == ChessPieceType.PAWN);
			assertTrue(piece.getColor() == ChessColor.WHITE);
		}

		assertTrue(board.at(ChessFile.H, new ChessRank(8)).getChessPieceType() == ChessPieceType.PAWN);
		assertTrue(board.at(ChessFile.H, new ChessRank(8)).getColor() == ChessColor.WHITE);

		assertTrue(board.at(ChessFile.E, new ChessRank(4)).getChessPieceType() == ChessPieceType.PAWN);
		assertTrue(board.at(ChessFile.E, new ChessRank(4)).getColor() == ChessColor.BLACK);
	}

}
