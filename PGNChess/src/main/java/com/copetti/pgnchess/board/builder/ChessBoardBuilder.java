package com.copetti.pgnchess.board.builder;

import java.util.HashMap;
import java.util.Map;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessFile;
import com.copetti.pgnchess.board.ChessRank;
import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.pieces.ChessPiece;

public class ChessBoardBuilder {

	private Map<ChessSquare, ChessPiece> pieces;

	private ChessBoardBuilder() {
		pieces = new HashMap<>();
	}

	ChessBoardBuilder(Map<ChessSquare, ChessPiece> map) {
		this.pieces = map;
	}

	public static ChessBoardBuilder newBuilder() {
		return new ChessBoardBuilder();
	}

	public ChessBoardBuilderAt at(ChessSquare square) {
		return new ChessBoardBuilderAt(pieces, square);
	}

	public ChessBoard build() {
		return new ChessBoard(pieces);
	}

	public ChessBoardBuilderFillRank fill(ChessRank chessRank) {
		return new ChessBoardBuilderFillRank(pieces, chessRank);
	}

	public ChessBoardBuilderFillFile fill(ChessFile chessFile) {
		return new ChessBoardBuilderFillFile(pieces, chessFile);
	}
}
