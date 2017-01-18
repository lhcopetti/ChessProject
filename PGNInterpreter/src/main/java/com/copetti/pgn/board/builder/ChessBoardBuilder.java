package com.copetti.pgn.board.builder;

import java.util.HashMap;
import java.util.Map;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class ChessBoardBuilder {

	private Map<ChessSquare, ColoredChessPiece> pieces;

	private ChessBoardBuilder() {
		pieces = new HashMap<>();
	}

	ChessBoardBuilder(Map<ChessSquare, ColoredChessPiece> map) {
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
