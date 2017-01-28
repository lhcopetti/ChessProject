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

public class KingMoveTest extends ChessPieceMoveTest {

	@Override
	public void getMovesOnEmptyBoardTest() {

		ChessSquare d5 = new ChessSquare("d5");
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b.at("d5").put(ChessPiece.KING).build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(d5, board);

		assertTrue(moves.size() == 8);
		assertContains(moves, "c6", "d6", "e6", "c5", "e5", "c4", "d4", "e4");
	}

	@Override
	public void getMovesWithBlockedBoard() {

		ChessSquare a8 = new ChessSquare("a8");
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b //
				.at("a8").put(ChessPiece.KING) //
				//
				.at("a7").put(ChessPiece.KNIGHT) //
				.at("b8").put(ChessPiece.KNIGHT) //
				.build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(a8, board);
		assertTrue(moves.size() == 1);
		assertContains(moves, "b7");
	}

	@Override
	public void getMovesWithCaptureAvailable() {

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b //
				.at("c7").put(ChessPiece.KING) //
				//
				.at("b8", "d8", "b6", "d6").put(ChessPiece.KNIGHT) //
				.setColor(ChessColor.BLACK) //
				.at("c8", "b7", "d7", "c6").put(ChessPiece.KNIGHT) //
				.build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(new ChessSquare("c7"), board);
		assertTrue(moves.size() == 4);
		assertContains(moves, "c8", "b7", "d7", "c6");

	}

	@Override
	public void getMovesWithNoneAvailable() {

		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();
		Map<ChessSquare, ColoredChessPiece> map = b //
				.at("c7").put(ChessPiece.KING) //
				.at("b8", "d8", "b6", "d6").put(ChessPiece.KNIGHT) //
				.at("c8", "b7", "d7", "c6").put(ChessPiece.KNIGHT) //
				.build();

		ChessBoard board = BoardTestFactory.createNew(map, ChessColor.WHITE);
		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(new ChessSquare("c7"), board);
		assertTrue(moves.size() == 0);

	}

}
