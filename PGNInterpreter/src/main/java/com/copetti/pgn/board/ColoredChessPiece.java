package com.copetti.pgn.board;

import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.Getter;

public class ColoredChessPiece {

	private @Getter ChessPiece piece;
	private @Getter ChessColor color;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((piece == null) ? 0 : piece.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ColoredChessPiece))
			return false;
		ColoredChessPiece other = (ColoredChessPiece) obj;
		if (color != other.color)
			return false;
		if (piece != other.piece)
			return false;
		return true;
	}

}
