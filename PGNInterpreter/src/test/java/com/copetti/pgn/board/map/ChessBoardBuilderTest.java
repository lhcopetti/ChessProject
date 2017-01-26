package com.copetti.pgn.board.map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class ChessBoardBuilderTest {

	@Test
	public void testEmptyBoard() {
		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();

		Map<ChessSquare, ColoredChessPiece> board = builder.build();
		assertTrue(board.isEmpty());

		board = builder.at("a1").put(ChessPiece.PAWN).build();
		assertFalse(board.isEmpty());
	}

	@Test
	public void testPutHorse() {

		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> board = builder.at("a1").put(ChessPiece.PAWN).build();
		assertFalse(board.isEmpty());
	}

	@Test
	public void testFillRank() {

		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();

		Map<ChessSquare, ColoredChessPiece> board = builder.fill(ChessRank.of("2").get())
				.with(new ColoredChessPiece(ChessPiece.PAWN, ChessColor.WHITE)).build();

		for (ChessFile c : ChessFile.values()) {
			ColoredChessPiece piece = board.get(new ChessSquare(c, ChessRank.of("2").get()));
			assertTrue(piece.getPiece() == ChessPiece.PAWN);
			assertTrue(piece.getColor() == ChessColor.WHITE);
		}
	}

	@Test
	public void testFillFile() {

		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();

		Map<ChessSquare, ColoredChessPiece> board = builder.fill(ChessFile.E)
				.with(new ColoredChessPiece(ChessPiece.PAWN, ChessColor.BLACK)).build();

		for (int i = 1; i <= 8; i++) {
			ColoredChessPiece piece = board.get(new ChessSquare(ChessFile.E, ChessRank.of(String.valueOf(i)).get()));
			assertTrue(piece.getPiece() == ChessPiece.PAWN);
			assertTrue(piece.getColor() == ChessColor.BLACK);
		}
	}

	@Test
	public void testFillAndPut() {

		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> board = builder.fill(ChessRank.of("2").get())
				.with(new ColoredChessPiece(ChessPiece.PAWN, ChessColor.WHITE)).build();

		builder.setColor(ChessColor.BLACK);
		builder.at("e4").put(ChessPiece.PAWN);

		builder.setColor(ChessColor.WHITE);
		builder.at("h8").put(ChessPiece.PAWN);

		for (ChessFile c : ChessFile.values()) {
			ColoredChessPiece piece = board.get(new ChessSquare(c, ChessRank.of("2").get()));
			assertTrue(piece.getPiece() == ChessPiece.PAWN);
			assertTrue(piece.getColor() == ChessColor.WHITE);
		}

		assertTrue(board.get(new ChessSquare("h8")).getPiece() == ChessPiece.PAWN);
		assertTrue(board.get(new ChessSquare("h8")).getColor() == ChessColor.WHITE);

		assertTrue(board.get(new ChessSquare("e4")).getPiece() == ChessPiece.PAWN);
		assertTrue(board.get(new ChessSquare("e4")).getColor() == ChessColor.BLACK);
	}

}
