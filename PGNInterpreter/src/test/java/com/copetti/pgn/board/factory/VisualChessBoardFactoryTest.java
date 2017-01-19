package com.copetti.pgn.board.factory;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class VisualChessBoardFactoryTest {

	@Test
	public void testVisualBoardFactory() {
		String initialSetup = "rnbqkbnr" + "pppppppp" + "--------" + "--------" + "--------" + "--------" + "PPPPPPPP"
				+ "RNBQKBNR";

		Map<ChessSquare, ColoredChessPiece> map = new VisualChessBoardFactory().newBoard(initialSetup);

		assertThatRankIsFilled(map, ChessRank.of("7").get(), new ColoredChessPiece(ChessPiece.PAWN, ChessColor.BLACK));
		assertThatRankIsFilled(map, ChessRank.of("2").get(), new ColoredChessPiece(ChessPiece.PAWN, ChessColor.WHITE));

		assertThatRankIsOfColor(map, ChessRank.of("1").get(), ChessColor.WHITE);
		assertThatRankIsOfColor(map, ChessRank.of("8").get(), ChessColor.BLACK);

		assertFileIs(map, ChessRank.of("1").get(), Arrays.asList(ChessPiece.ROOK, ChessPiece.KNIGHT, ChessPiece.BISHOP,
				ChessPiece.QUEEN, ChessPiece.KING, ChessPiece.BISHOP, ChessPiece.KNIGHT, ChessPiece.ROOK));
		assertFileIs(map, ChessRank.of("8").get(), Arrays.asList(ChessPiece.ROOK, ChessPiece.KNIGHT, ChessPiece.BISHOP,
				ChessPiece.QUEEN, ChessPiece.KING, ChessPiece.BISHOP, ChessPiece.KNIGHT, ChessPiece.ROOK));

		assertRankIsEmpty(map, ChessRank.of("3").get());
		assertRankIsEmpty(map, ChessRank.of("4").get());
		assertRankIsEmpty(map, ChessRank.of("5").get());
		assertRankIsEmpty(map, ChessRank.of("6").get());
	}

	private void assertRankIsEmpty(Map<ChessSquare, ColoredChessPiece> map, ChessRank chessRank) {
		for (ChessFile f : ChessFile.values())
			assertTrue(map.get(new ChessSquare(f, chessRank)) == null);
	}

	private void assertFileIs(Map<ChessSquare, ColoredChessPiece> map, ChessRank chessRank, List<ChessPiece> asList) {

		for (int i = 0; i < ChessFile.values().length; i++) {
			ChessSquare cs = new ChessSquare(ChessFile.fromOrdinal(i).get(), chessRank);
			ChessPiece piece = map.get(cs).getPiece();
			assertEquals(piece, asList.get(i));
		}

	}

	private void assertThatRankIsOfColor(Map<ChessSquare, ColoredChessPiece> map, ChessRank rank, ChessColor color) {

		for (ChessFile f : ChessFile.values()) {
			ChessSquare cs = new ChessSquare(f, rank);
			assertTrue(map.get(cs).getColor() == color);
		}
	}

	private void assertThatRankIsFilled(Map<ChessSquare, ColoredChessPiece> map, ChessRank chessRank,
			ColoredChessPiece coloredChessPiece) {

		for (ChessFile f : ChessFile.values()) {
			ChessSquare cs = new ChessSquare(f, chessRank);
			assertEquals(map.get(cs), coloredChessPiece);
		}
	}
}
