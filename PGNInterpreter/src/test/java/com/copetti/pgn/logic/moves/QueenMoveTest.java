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

public class QueenMoveTest extends ChessPieceMoveTest {

	@Override
	public void getMovesOnEmptyBoardTest() {

		ChessSquare e4 = new ChessSquare("e4");
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b.at("e4").put(ChessPiece.QUEEN).build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(e4, board);

		assertTrue(moves.size() == 27);
		assertContains(moves, new ChessSquare("e5"));
		assertContains(moves, new ChessSquare("e6"));
		assertContains(moves, new ChessSquare("e7"));
		assertContains(moves, new ChessSquare("e8"));
		assertContains(moves, new ChessSquare("e3"));
		assertContains(moves, new ChessSquare("e2"));
		assertContains(moves, new ChessSquare("e1"));

		assertContains(moves, new ChessSquare("a4"));
		assertContains(moves, new ChessSquare("b4"));
		assertContains(moves, new ChessSquare("c4"));
		assertContains(moves, new ChessSquare("d4"));
		assertContains(moves, new ChessSquare("f4"));
		assertContains(moves, new ChessSquare("g4"));
		assertContains(moves, new ChessSquare("h4"));

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
		ChessSquare a8 = new ChessSquare("a8");
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b //
				.at("a8").put(ChessPiece.QUEEN) //
				.at("a5").put(ChessPiece.KNIGHT) //
				.at("b6").put(ChessPiece.PAWN) //
				.at("c7").put(ChessPiece.PAWN) //
				.at("d8").put(ChessPiece.BISHOP) //
				.at("d5").put(ChessPiece.BISHOP) //
				.build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(a8, board);

		assertTrue(moves.size() == 6);
		assertContains(moves, new ChessSquare("a7"));
		assertContains(moves, new ChessSquare("a6"));
		assertContains(moves, new ChessSquare("b7"));
		assertContains(moves, new ChessSquare("c6"));
		assertContains(moves, new ChessSquare("b8"));
		assertContains(moves, new ChessSquare("c8"));
	}

	@Override
	public void getMovesWithCaptureAvailable() {
		ChessSquare a8 = new ChessSquare("a8");
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b //
				.at("a8").put(ChessPiece.QUEEN) //
				.setColor(ChessColor.BLACK) //
				.at("a5").put(ChessPiece.KNIGHT) //
				.at("b6").put(ChessPiece.PAWN) //
				.at("c7").put(ChessPiece.PAWN) //
				.at("d8").put(ChessPiece.BISHOP) //
				.at("d5").put(ChessPiece.BISHOP) //
				.build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(a8, board);

		assertTrue(moves.size() == 9);
		assertContains(moves, new ChessSquare("a7"));
		assertContains(moves, new ChessSquare("a6"));
		assertContains(moves, new ChessSquare("a5"));

		assertContains(moves, new ChessSquare("b7"));
		assertContains(moves, new ChessSquare("c6"));
		assertContains(moves, new ChessSquare("d5"));

		assertContains(moves, new ChessSquare("b8"));
		assertContains(moves, new ChessSquare("c8"));
		assertContains(moves, new ChessSquare("d8"));

	}

	@Override
	public void getMovesWithNoneAvailable() {

		ChessSquare d5 = new ChessSquare("d5");
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b //
				.at("d5").put(ChessPiece.QUEEN) //
				.at("c6", "e6").put(ChessPiece.BISHOP) //
				.at("c5", "d6", "e5").put(ChessPiece.PAWN) //
				.at("c4", "e4").put(ChessPiece.KNIGHT) //
				.at("d4").put(ChessPiece.KING) //
				.build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(d5, board);

		assertTrue(moves.size() == 0);
	}

}
