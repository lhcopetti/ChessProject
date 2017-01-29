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

import lombok.Getter;

public class ChessBoardContextBuilder {

	private @Getter Map<ChessSquare, ColoredChessPiece> pieces;

	private ChessSquare enPassant;
	private ChessColor nextToPlay;
	private CastleInformation castle;
	private HalfMoveCounter halfMove;
	private FullMoveCounter fullMove;

	private boolean gameOver;

	private ChessBoardContextBuilder(ChessBoard board) {
		pieces = new HashMap<>(board.getPieces());
		nextToPlay = board.getNextToPlay();
		castle = board.getCastleInfo();
		halfMove = board.getHalfMoveCounter();
		fullMove = board.getFullMoveNumber();
		gameOver = false;
	}

	public static ChessBoardContextBuilder newBuilder(ChessBoard board) {

		return new ChessBoardContextBuilder(board);
	}

	public ChessBoard build() {

		ChessBoardContext ctx = new ChessBoardContext(nextToPlay, castle, enPassant, halfMove, fullMove);
		return new ChessBoard(pieces, ctx, gameOver);

	}

	public void removeCastleFor(ChessColor color) {
	}

	public void setGameOver() {
		gameOver = true;
	}

	public void incrementHalfMove() {
		halfMove = halfMove.next();
	}

	public void nextPlayer() {
		nextToPlay = nextToPlay.opposite();
	}

	public void setEnpassant(ChessSquare chessSquare) {
		enPassant = chessSquare;
	}

	public void incrementFullMove() {
		fullMove = fullMove.next();
	}

	public CastleInformation getCastle() {
		return castle;
	}

	public void setCastle(CastleInformation remove) {
		castle = remove;
	}

	public boolean movePiece(ChessSquare origin, ChessSquare destination) {
		ColoredChessPiece piece = pieces.remove(origin);

		if (null == piece)
			return false;

		pieces.put(destination, piece);
		return true;
	}

}
