package com.copetti.pgn.logic.moves;

import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.Before;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
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

}
