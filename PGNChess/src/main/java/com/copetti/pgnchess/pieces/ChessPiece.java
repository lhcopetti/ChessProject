package com.copetti.pgnchess.pieces;

import lombok.Getter;


public abstract class ChessPiece
{

	public ChessPiece(ChessPieceType pieceType, ChessColor color)
	{
		this.color = color;
		this.chessPieceType = pieceType;
	}

	private @Getter ChessColor color;
	private @Getter ChessPieceType chessPieceType;
}
