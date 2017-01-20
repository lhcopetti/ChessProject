package com.copetti.pgn.board.builder;

import java.util.HashMap;
import java.util.Map;

import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class ChessBoardBuilder {

	private Map<ChessSquare, ColoredChessPiece> pieces;
	private ChessColor color;

	private ChessBoardBuilder() {
		pieces = new HashMap<>();
		color = ChessColor.WHITE;
	}

	ChessBoardBuilder(Map<ChessSquare, ColoredChessPiece> map, ChessColor color) {
		this.pieces = map;
		this.color = color;
	}

	public static ChessBoardBuilder newBuilder() {
		return new ChessBoardBuilder();
	}

	public ChessBoardBuilderAt at(String... square) {
		return new ChessBoardBuilderAt(pieces, color, square);
	}

	public Map<ChessSquare, ColoredChessPiece> build() {
		return pieces;
	}

	public ChessBoardBuilderFillRank fill(ChessRank chessRank) {
		return new ChessBoardBuilderFillRank(pieces, chessRank);
	}

	public ChessBoardBuilderFillFile fill(ChessFile chessFile) {
		return new ChessBoardBuilderFillFile(pieces, chessFile);
	}

	public ChessBoardBuilder setColor(ChessColor color) {
		this.color = color;
		return this;
	}

}