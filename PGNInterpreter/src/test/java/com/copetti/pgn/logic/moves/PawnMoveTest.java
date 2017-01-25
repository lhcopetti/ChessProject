package com.copetti.pgn.logic.moves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.factory.BoardTestFactory;
import com.copetti.pgn.board.map.ChessBoardBuilder;
import com.copetti.pgn.logic.ChessMovementResolver;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class PawnMoveTest extends ChessPieceMoveTest {

	private ChessMovementResolver cmr;

	@Before
	public void setUp() {
		cmr = new ChessMovementResolver(ChessPiece.PAWN);
	}

	@Override
	public void getMovesOnEmptyBoardTest() {

		ChessSquare c = new ChessSquare("e2");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at("e2").put(ChessPiece.PAWN);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.WHITE);
		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertTrue(moves.size() == 2);
		assertContains(moves, new ChessSquare("e3"));
		assertContains(moves, new ChessSquare("e4"));
	}

	@Test
	public void getMovesOnEmptyBoardTestForBlack() {

		ChessSquare c = new ChessSquare("e7");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.setColor(ChessColor.BLACK);
		b.at("e7").put(ChessPiece.PAWN);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.BLACK);
		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertTrue(moves.size() == 2);
		assertContains(moves, new ChessSquare("e5"));
		assertContains(moves, new ChessSquare("e6"));
	}

	@Override
	public void getMovesWithBlockedBoard() {

		ChessSquare c = new ChessSquare("e2");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at("e2").put(ChessPiece.PAWN);
		b.at("e3").put(ChessPiece.PAWN);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.WHITE);
		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertTrue(moves.isEmpty());
	}

	@Test
	public void getMovesWithBlockedBoardForBlack() {

		ChessSquare c = new ChessSquare("e7");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.setColor(ChessColor.BLACK);
		b.at("e7").put(ChessPiece.PAWN);
		b.at("e6").put(ChessPiece.PAWN);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.BLACK);
		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertTrue(moves.isEmpty());
	}

	@Override
	public void getMovesWithCaptureAvailable() {

		ChessSquare c = new ChessSquare("e2");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();

		b.at("e2").put(ChessPiece.PAWN);
		b.setColor(ChessColor.BLACK);
		b.at("f3", "d3").put(ChessPiece.PAWN);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.WHITE);
		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertContains(moves, new ChessSquare("e3"));
		assertContains(moves, new ChessSquare("e4"));
		assertContains(moves, new ChessSquare("f3"));
		assertContains(moves, new ChessSquare("d3"));
	}

	@Override
	public void getMovesWithNoneAvailable() {

		ChessSquare c = new ChessSquare("e2");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at("e2", "d3", "e3", "f3").put(ChessPiece.PAWN);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.WHITE);
		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertTrue(moves.isEmpty());
	}

	@Test
	public void getMovesWithEnPassantForWhite() {

		ChessSquare c = new ChessSquare("e5");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at("e5").put(ChessPiece.PAWN);
		b.setColor(ChessColor.BLACK);
		b.at("d5").put(ChessPiece.PAWN);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.WHITE, new ChessSquare("d6"));
		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertEquals(2, moves.size());

		assertContains(moves, new ChessSquare("e6"));
		assertContains(moves, new ChessSquare("d6"));
	}

	@Test
	public void getMovesWithEnPassantForBlack() {

		ChessSquare c = new ChessSquare("d4");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.setColor(ChessColor.BLACK);
		b.at("d4").put(ChessPiece.PAWN);
		b.setColor(ChessColor.WHITE);
		b.at("e4").put(ChessPiece.PAWN);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.BLACK, new ChessSquare("e3"));
		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertEquals(2, moves.size());

		assertContains(moves, new ChessSquare("d3"));
		assertContains(moves, new ChessSquare("e3"));
	}

}
