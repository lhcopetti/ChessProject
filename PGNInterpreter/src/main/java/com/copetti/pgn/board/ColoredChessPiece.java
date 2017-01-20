package com.copetti.pgn.board;

import java.util.function.Function;

import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class ColoredChessPiece {

	private @Getter ChessPiece piece;
	private @Getter ChessColor color;

	@Override
	public String toString() {

		Function<String, String> f = color == ChessColor.WHITE ? String::toUpperCase : String::toLowerCase;
		return f.apply(piece.getPgnNotation());
	}

}
