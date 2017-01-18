package com.copetti.pgn.board.builder;

import java.util.Map;

import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;

public class ChessBoardBuilderAt {

	private Map<ChessSquare, ColoredChessPiece> pieces;
	private ChessSquare square;

	public ChessBoardBuilderAt(Map<ChessSquare, ColoredChessPiece> map, ChessSquare square) {
		this.pieces = map;
		this.square = square;
	}

	public ChessBoardBuilder put(ColoredChessPiece piece) {
		pieces.put(square, piece);
		return new ChessBoardBuilder(pieces);
	}

}
