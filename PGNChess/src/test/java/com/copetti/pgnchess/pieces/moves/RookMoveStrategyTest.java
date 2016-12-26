package com.copetti.pgnchess.pieces.moves;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.board.builder.ChessBoardBuilder;
import com.copetti.pgnchess.pieces.ChessColor;
import com.copetti.pgnchess.pieces.ChessPiece;
import com.copetti.pgnchess.pieces.ChessPieceMoveTest;
import com.copetti.pgnchess.pieces.ChessPieceType;


public class RookMoveStrategyTest extends ChessPieceMoveTest
{

	@Override
	public void getMovesOnEmptyBoardTest()
	{
		ChessSquare c = new ChessSquare("e4");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		ChessBoard board = b.at(c)
				.put(new ChessPiece(ChessPieceType.ROOK, ChessColor.WHITE))
				.build();

		Set<ChessSquare> moves = board.at(c).getMoves(c, board);

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
	public void getMovesWithBlockedBoard()
	{
		ChessSquare c = new ChessSquare("e4");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at(c).put(new ChessPiece(ChessPieceType.ROOK, ChessColor.WHITE));
		b.at(new ChessSquare("e7"))
				.put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));
		b.at(new ChessSquare("e3"))
				.put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));
		b.at(new ChessSquare("d4"))
				.put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));
		b.at(new ChessSquare("h4"))
				.put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));

		ChessBoard board = b.build();
		Set<ChessSquare> moves = board.at(c).getMoves(c, board);

		assertTrue(moves.size() == 4);
		assertContains(moves, new ChessSquare("e5"));
		assertContains(moves, new ChessSquare("e6"));

		assertContains(moves, new ChessSquare("f4"));
		assertContains(moves, new ChessSquare("g4"));
	}

	@Override
	public void getMovesWithCaptureAvailable()
	{
		ChessSquare c = new ChessSquare("e4");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at(c).put(new ChessPiece(ChessPieceType.ROOK, ChessColor.WHITE));
		b.at(new ChessSquare("e7"))
				.put(new ChessPiece(ChessPieceType.PAWN, ChessColor.BLACK));
		b.at(new ChessSquare("e3"))
				.put(new ChessPiece(ChessPieceType.PAWN, ChessColor.BLACK));
		b.at(new ChessSquare("h4"))
				.put(new ChessPiece(ChessPieceType.PAWN, ChessColor.BLACK));
		b.at(new ChessSquare("c4"))
				.put(new ChessPiece(ChessPieceType.PAWN, ChessColor.BLACK));

		ChessBoard board = b.build();

		Set<ChessSquare> moves = board.at(c).getMoves(c, board);

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
	public void getMovesWithNoneAvailable()
	{
		ChessSquare c = new ChessSquare("a1");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		b.at(c).put(new ChessPiece(ChessPieceType.ROOK, ChessColor.WHITE));

		b.at(new ChessSquare("b1"))
				.put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));
		b.at(new ChessSquare("a2"))
				.put(new ChessPiece(ChessPieceType.PAWN, ChessColor.WHITE));

		ChessBoard board = b.build();
		Set<ChessSquare> moves = board.at(c).getMoves(c, board);
		assertTrue(moves.isEmpty());
	}

}
