package com.copetti.pgn.board.builder;

import java.util.HashMap;
import java.util.Map;

import com.copetti.pgn.board.CastleInformation;
import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessBoardContext;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.FullMoveCounter;
import com.copetti.pgn.board.HalfMoveCounter;

public class ChessBoardContextBuilder {

	private Map<ChessSquare, ColoredChessPiece> pieces;

	private ChessSquare chessSquare;
	private ChessColor nextToPlay;
	private CastleInformation castle;
	private HalfMoveCounter halfMove;
	private FullMoveCounter fullMove;

	private ChessBoardContextBuilder(ChessBoard board) {
		pieces = new HashMap<>(board.getPieces());
		nextToPlay = board.getNextToPlay();
		castle = board.getCastleInfo();
		halfMove = board.getHalfMoveCounter();
		fullMove = board.getFullMoveNumber();
	}

	public static ChessBoardContextBuilder newBuilder(ChessBoard board) {

		return new ChessBoardContextBuilder(board);
	}

	public ChessBoard build() {

		ChessBoardContext ctx = new ChessBoardContext(nextToPlay, castle, chessSquare, halfMove, fullMove);
		return new ChessBoard(pieces, ctx);

	}

	public void incrementHalfMove() {
		halfMove = halfMove.next();
	}

	public void nextPlayer() {
		nextToPlay = nextToPlay.opposite();
	}

}
