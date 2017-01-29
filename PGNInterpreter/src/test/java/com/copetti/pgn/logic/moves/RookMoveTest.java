package com.copetti.pgn.logic.moves;

import static com.copetti.pgn.logic.moves.ChessAssertiveLibrary.assertContains;
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

public class RookMoveTest extends ChessPieceMoveTest {

	@Override
	public void getMovesOnEmptyBoardTest() {

		ChessSquare e4 = new ChessSquare("e4");
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b.at("e4").put(ChessPiece.ROOK).build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(e4, board);

		assertTrue(moves.size() == 14);
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
	}

	@Override
	public void getMovesWithBlockedBoard() {
		ChessSquare c = new ChessSquare("e4");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at("e4").put(ChessPiece.ROOK);
		b.at("e7").put(ChessPiece.PAWN);
		b.at("e3").put(ChessPiece.PAWN);
		b.at("d4").put(ChessPiece.PAWN);
		b.at("h4").put(ChessPiece.PAWN);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertTrue(moves.size() == 4);
		assertContains(moves, new ChessSquare("e5"));
		assertContains(moves, new ChessSquare("e6"));

		assertContains(moves, new ChessSquare("f4"));
		assertContains(moves, new ChessSquare("g4"));
	}

	@Override
	public void getMovesWithCaptureAvailable() {
		ChessSquare c = new ChessSquare("e4");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at("e4").put(ChessPiece.ROOK);
		b.setColor(ChessColor.BLACK);
		b.at("e7").put(ChessPiece.PAWN);
		b.at("e3").put(ChessPiece.PAWN);
		b.at("h4").put(ChessPiece.PAWN);
		b.at("c4").put(ChessPiece.PAWN);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();
		Set<ChessSquare> moves = cmr.getMoves(c, board);

		assertTrue(moves.size() == 9);
		assertContains(moves, new ChessSquare("e5"));
		assertContains(moves, new ChessSquare("e6"));
		assertContains(moves, new ChessSquare("e7"));

		assertContains(moves, new ChessSquare("e3"));

		assertContains(moves, new ChessSquare("f4"));
		assertContains(moves, new ChessSquare("g4"));
		assertContains(moves, new ChessSquare("h4"));

		assertContains(moves, new ChessSquare("d4"));
		assertContains(moves, new ChessSquare("c4"));
	}

	@Override
	public void getMovesWithNoneAvailable() {
		ChessSquare c = new ChessSquare("a1");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at("a1").put(ChessPiece.ROOK);

		b.at("b1").put(ChessPiece.PAWN);
		b.at("a2").put(ChessPiece.PAWN);

		ChessBoard board = BoardTestFactory.createNew(b.build(), ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();
		Set<ChessSquare> moves = cmr.getMoves(c, board);
		assertTrue(moves.isEmpty());
	}

}
