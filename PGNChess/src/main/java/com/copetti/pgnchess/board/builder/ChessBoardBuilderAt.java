package com.copetti.pgnchess.board.builder;

import java.util.Map;

import com.copetti.pgnchess.pieces.ChessPiece;
import com.copetti.pgncommon.chess.board.ChessSquare;

public class ChessBoardBuilderAt {

	private Map<ChessSquare, ChessPiece> pieces;
	private ChessSquare square;

	public ChessBoardBuilderAt(Map<ChessSquare, ChessPiece> map, ChessSquare square) {
		this.pieces = map;
		this.square = square;
	}

	public ChessBoardBuilder put(ChessPiece piece) {
		pieces.put(square, piece);
		return new ChessBoardBuilder(pieces);
	}

}
