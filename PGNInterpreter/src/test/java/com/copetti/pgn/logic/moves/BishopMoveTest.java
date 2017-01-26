package com.copetti.pgn.logic.moves;

import static org.junit.Assert.assertTrue;

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

import static org.junit.Assert.*;

public class BishopMoveTest extends ChessPieceMoveTest {

	@Override
	public void getMovesOnEmptyBoardTest() {
		ChessSquare c = new ChessSquare("e4");

		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> b = builder.at("e4").put(ChessPiece.BISHOP).build();

		ChessBoard board = BoardTestFactory.createNew(b, ChessColor.WHITE);

		ChessMovementResolver cmr = new ChessMovementResolver();
		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertTrue(moves.size() == 13);
		assertContains(moves, new ChessSquare("f5"));
		assertContains(moves, new ChessSquare("g6"));
		assertContains(moves, new ChessSquare("h7"));
		assertContains(moves, new ChessSquare("d3"));
		assertContains(moves, new ChessSquare("c2"));
		assertContains(moves, new ChessSquare("b1"));

		assertContains(moves, new ChessSquare("d5"));
		assertContains(moves, new ChessSquare("c6"));
		assertContains(moves, new ChessSquare("b7"));
		assertContains(moves, new ChessSquare("a8"));
		assertContains(moves, new ChessSquare("f3"));
		assertContains(moves, new ChessSquare("g2"));
		assertContains(moves, new ChessSquare("h1"));
	}

	@Override
	public void getMovesWithBlockedBoard() {
		ChessSquare c = new ChessSquare("e4");
		ChessPiece whiteB = ChessPiece.BISHOP;

		ChessBoardBuilder builder = ChessBoardBuilder.newBuilder();
		builder.at("e4", "f5", "c6", "b1", "h1").put(whiteB);

		ChessBoard board = BoardTestFactory.createNew(builder.build(), ChessColor.WHITE);

		ChessMovementResolver cmr = new ChessMovementResolver();
		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertTrue(moves.size() == 5);
		assertContains(moves, new ChessSquare("d3"));
		assertContains(moves, new ChessSquare("c2"));

		assertContains(moves, new ChessSquare("d5"));

		assertContains(moves, new ChessSquare("f3"));
		assertContains(moves, new ChessSquare("g2"));
	}

	@Override
	public void getMovesWithCaptureAvailable() {
		ChessSquare c = new ChessSquare("e4");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at("e4").put(ChessPiece.BISHOP);
		b.setColor(ChessColor.BLACK);
		b.at("f5", "c6", "b1", "h1").put(ChessPiece.BISHOP);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertTrue(moves.size() == 9);
		assertContains(moves, new ChessSquare("f5"));

		assertContains(moves, new ChessSquare("d3"));
		assertContains(moves, new ChessSquare("c2"));
		assertContains(moves, new ChessSquare("b1"));

		assertContains(moves, new ChessSquare("d5"));
		assertContains(moves, new ChessSquare("c6"));

		assertContains(moves, new ChessSquare("f3"));
		assertContains(moves, new ChessSquare("g2"));
		assertContains(moves, new ChessSquare("h1"));
	}

	@Override
	public void getMovesWithNoneAvailable() {

		ChessSquare c = new ChessSquare("e4");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at("e4", "d5", "f5", "d3", "f3").put(ChessPiece.BISHOP);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();
		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertEquals(0, moves.size());
	}

}
