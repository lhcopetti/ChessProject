package com.copetti.pgnchess.pieces;

import java.util.Set;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;

import lombok.Getter;

public class ChessPiece {
	public ChessPiece(ChessPieceType pieceType, ChessColor color) {
		this.color = color;
		this.chessPieceType = pieceType;
	}

	private @Getter ChessColor color;
	private @Getter ChessPieceType chessPieceType;

	public Set<ChessSquare> getMoves(ChessSquare square, ChessBoard board) {
		return chessPieceType.getMoves(square, board);
	}
}
