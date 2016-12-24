package com.copetti.pgnchess.board;

import java.util.Map;

import com.copetti.pgnchess.pieces.ChessPiece;

public class ChessBoard {
	private Map<ChessSquare, ChessPiece> pieces;

	public ChessBoard(Map<ChessSquare, ChessPiece> map) {
		pieces = map;
	}

	public ChessPiece at(int x, int y) {
		return pieces.get(new ChessSquare(x, y));
	}

	public boolean isEmpty() {
		return pieces.isEmpty();
	}

	public ChessPiece at(ChessFile c, ChessRank chessRank) {
		return at(new ChessSquare(c, chessRank));
	}

	public ChessPiece at(ChessSquare chessSquare) {
		return pieces.get(chessSquare);
	}

}
