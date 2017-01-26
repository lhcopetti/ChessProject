package com.copetti.pgn.logic.moves;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Set;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.factory.BoardTestFactory;
import com.copetti.pgn.board.map.ChessBoardBuilder;
import com.copetti.pgn.logic.ChessMovementResolver;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class KnightMoveTest extends ChessPieceMoveTest {

	@Override
	public void getMovesOnEmptyBoardTest() {

		ChessSquare e4 = new ChessSquare("e4");
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b.at("e4").put(ChessPiece.KNIGHT).build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(e4, board);

		assertEquals(8, moves.size());

		assertContains(moves, new ChessSquare("d6"));
		assertContains(moves, new ChessSquare("c5"));

		assertContains(moves, new ChessSquare("c3"));
		assertContains(moves, new ChessSquare("d2"));

		assertContains(moves, new ChessSquare("f2"));
		assertContains(moves, new ChessSquare("g3"));

		assertContains(moves, new ChessSquare("g5"));
		assertContains(moves, new ChessSquare("f6"));
	}

	@Override
	public void getMovesWithBlockedBoard() {

		ChessSquare e1 = new ChessSquare("e1");
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b //
				.at("e1").put(ChessPiece.KNIGHT) //
				.at("c2").put(ChessPiece.KNIGHT) //
				.at("d3").put(ChessPiece.KNIGHT) //
				.at("f3").put(ChessPiece.KNIGHT) //
				.at("g2").put(ChessPiece.KNIGHT) //
				.build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(e1, board);
		assertTrue(moves.size() == 0);

	}

	@Override
	public void getMovesWithCaptureAvailable() {

		ChessSquare h8 = new ChessSquare("h8");
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b //
				.at("h8").put(ChessPiece.KNIGHT) //
				.setColor(ChessColor.BLACK) //
				.at("f7").put(ChessPiece.KNIGHT) //
				.at("g6").put(ChessPiece.KNIGHT) //
				.build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(h8, board);

		assertTrue(moves.size() == 2);
		assertContains(moves, new ChessSquare("f7"));
		assertContains(moves, new ChessSquare("g6"));

	}

	@Override
	public void getMovesWithNoneAvailable() {

		ChessSquare e4 = new ChessSquare("e4");
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b //
				.at("e4").put(ChessPiece.KNIGHT) //
				.at("d6").put(ChessPiece.KNIGHT) //
				.at("c5").put(ChessPiece.KNIGHT) //
				.at("c3").put(ChessPiece.KNIGHT) //
				.at("d2").put(ChessPiece.KNIGHT) //
				.at("f2").put(ChessPiece.KNIGHT) //
				.at("g3").put(ChessPiece.KNIGHT) //
				.at("g5").put(ChessPiece.KNIGHT) //
				.at("f6").put(ChessPiece.KNIGHT) //
				.build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(e4, board);

		assertTrue(moves.size() == 0);

	}

}
