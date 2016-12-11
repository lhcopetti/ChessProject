package com.copetti.pgnchess.pieces;

import lombok.Getter;

public enum ChessPieceType
{
	PAWN("p"), ROOK("r"), BISHOP("b"), KNIGHT("n"), QUEEN("q"), KING("k");

	private @Getter String pgnFormat;

	private ChessPieceType(String pgnFormat)
	{
		this.pgnFormat = pgnFormat;
	}
}
