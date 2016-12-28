package com.copetti.pgnchess.pieces.moves;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.board.builder.ChessBoardBuilder;
import com.copetti.pgnchess.pieces.ChessColor;
import com.copetti.pgnchess.pieces.ChessPiece;
import com.copetti.pgnchess.pieces.ChessPieceType;

public class PawnMoveTest extends ChessPieceMoveTest {

	@Override
	public void getMovesOnEmptyBoardTest() {

		ChessSquare c = new ChessSquare("e2");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		ChessBoard board = b.at(c).put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE)).build();

		Set<ChessSquare> moves = board.at(c).getMoves(c, board);

		assertTrue(moves.size() == 2);
		assertContains(moves, new ChessSquare("e3"));
		assertContains(moves, new ChessSquare("e4"));
	}

	@Override
	public void getMovesWithBlockedBoard() {

		ChessSquare c = new ChessSquare("e2");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at(c).put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));
		b.at(new ChessSquare("e3")).put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));

		ChessBoard board = b.build();

		Set<ChessSquare> moves = board.at(c).getMoves(c, board);
		assertTrue(moves.isEmpty());
	}

	@Override
	public void getMovesWithCaptureAvailable() {

		ChessSquare c = new ChessSquare("e2");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at(c).put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));
		b.at(new ChessSquare("f3")).put(new ChessPiece(ChessPieceType.PAWN, ChessColor.BLACK));
		b.at(new ChessSquare("d3")).put(new ChessPiece(ChessPieceType.PAWN, ChessColor.BLACK));
		ChessBoard board = b.build();

		Set<ChessSquare> moves = board.at(c).getMoves(c, board);

		assertContains(moves, new ChessSquare("e3"));
		assertContains(moves, new ChessSquare("e4"));
		assertContains(moves, new ChessSquare("f3"));
		assertContains(moves, new ChessSquare("d3"));
	}

	@Override
	public void getMovesWithNoneAvailable() {

		ChessSquare c = new ChessSquare("e2");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at(c).put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));
		b.at(new ChessSquare("d3")).put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));
		b.at(new ChessSquare("e3")).put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));
		b.at(new ChessSquare("f3")).put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));
		ChessBoard board = b.build();

		Set<ChessSquare> moves = board.at(c).getMoves(c, board);

		assertTrue(moves.isEmpty());
	}

}
