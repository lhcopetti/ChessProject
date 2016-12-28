package com.copetti.pgnchess.pieces.moves;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.board.builder.ChessBoardBuilder;
import com.copetti.pgnchess.pieces.ChessColor;
import com.copetti.pgnchess.pieces.ChessPiece;
import com.copetti.pgnchess.pieces.ChessPieceType;


public class BishopMoveTest extends ChessPieceMoveTest
{

	@Override
	public void getMovesOnEmptyBoardTest()
	{
		ChessSquare c = new ChessSquare("e4");

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		ChessBoard board = b.at(c)
				.put(new ChessPiece(ChessPieceType.BISHOP, ChessColor.WHITE))
				.build();

		Set<ChessSquare> moves = board.at(c).getMoves(c, board);

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
	public void getMovesWithBlockedBoard()
	{
		ChessSquare c = new ChessSquare("e4");
		ChessPiece whiteB = new ChessPiece(ChessPieceType.BISHOP,
				ChessColor.WHITE);

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		ChessBoard board = b.at(c).put(whiteB) //
				.at(new ChessSquare("f5")).put(whiteB) //
				.at(new ChessSquare("c6")).put(whiteB) //
				.at(new ChessSquare("b1")).put(whiteB) //
				.at(new ChessSquare("h1")).put(whiteB) //
				.build();

		Set<ChessSquare> moves = board.at(c).getMoves(c, board);

		assertTrue(moves.size() == 5);
		assertContains(moves, new ChessSquare("d3"));
		assertContains(moves, new ChessSquare("c2"));

		assertContains(moves, new ChessSquare("d5"));

		assertContains(moves, new ChessSquare("f3"));
		assertContains(moves, new ChessSquare("g2"));
	}

	@Override
	public void getMovesWithCaptureAvailable()
	{
		ChessSquare c = new ChessSquare("e4");

		ChessPiece whiteB = new ChessPiece(ChessPieceType.BISHOP,
				ChessColor.WHITE);
		ChessPiece blackB = new ChessPiece(ChessPieceType.BISHOP,
				ChessColor.BLACK);

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		ChessBoard board = b.at(c).put(whiteB) //
				.at(new ChessSquare("f5")).put(blackB) //
				.at(new ChessSquare("c6")).put(blackB) //
				.at(new ChessSquare("b1")).put(blackB) //
				.at(new ChessSquare("h1")).put(blackB) //
				.build();

		Set<ChessSquare> moves = board.at(c).getMoves(c, board);

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
	public void getMovesWithNoneAvailable()
	{
		ChessSquare c = new ChessSquare("e4");
		ChessPiece whiteB = new ChessPiece(ChessPieceType.BISHOP,
				ChessColor.WHITE);

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		ChessBoard board = b //
				.at(c).put(whiteB) //
				.at(new ChessSquare("f5")).put(whiteB).at(new ChessSquare("d5"))
				.put(whiteB).at(new ChessSquare("d3")).put(whiteB)
				.at(new ChessSquare("f3")).put(whiteB).build();

		Set<ChessSquare> moves = board.at(c).getMoves(c, board);
		assertTrue(moves.size() == 0);
	}

}
