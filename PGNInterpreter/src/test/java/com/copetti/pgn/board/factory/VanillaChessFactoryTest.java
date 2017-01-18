package com.copetti.pgn.board.factory;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class VanillaChessFactoryTest {

	@Test
	public void testNewBoardFactory() {
		Map<ChessSquare, ColoredChessPiece> map = new VanillaChessFactory().newBoard();

		assertThatRankIsFilled(ChessRank.of("7").get(), new ColoredChessPiece(ChessPiece.PAWN, ChessColor.BLACK));
		assertThatRankIsFilled(ChessRank.of("2").get(), new ColoredChessPiece(ChessPiece.PAWN, ChessColor.BLACK));

		assertThatRankIsOfColor(ChessRank.of("1").get(), ChessColor.WHITE);
		assertThatRankIsOfColor(ChessRank.of("8").get(), ChessColor.BLACK);

		assertFileIs(ChessRank.of("1").get(), Arrays.asList(ChessPiece.ROOK, ChessPiece.KNIGHT, ChessPiece.BISHOP,
				ChessPiece.QUEEN, ChessPiece.KING, ChessPiece.BISHOP, ChessPiece.KNIGHT, ChessPiece.ROOK));
		assertFileIs(ChessRank.of("8").get(), Arrays.asList(ChessPiece.ROOK, ChessPiece.KNIGHT, ChessPiece.BISHOP,
				ChessPiece.QUEEN, ChessPiece.KING, ChessPiece.BISHOP, ChessPiece.KNIGHT, ChessPiece.ROOK));

		assertRankIsEmpty(ChessRank.of("3").get());
		assertRankIsEmpty(ChessRank.of("4").get());
		assertRankIsEmpty(ChessRank.of("5").get());
		assertRankIsEmpty(ChessRank.of("6").get());
	}

	private void assertRankIsEmpty(ChessRank chessRank) {
		// TODO Auto-generated method stub

	}

	private void assertFileIs(ChessRank chessRank, List<ChessPiece> asList) {
		// TODO Auto-generated method stub

	}

	private void assertThatRankIsOfColor(ChessRank chessRank, ChessColor white) {
		// TODO Auto-generated method stub

	}

	private void assertThatRankIsFilled(ChessRank chessRank, ColoredChessPiece coloredChessPiece) {
		// TODO Auto-generated method stub

	}

}
