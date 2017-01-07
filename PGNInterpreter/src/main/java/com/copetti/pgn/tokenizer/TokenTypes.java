package com.copetti.pgn.tokenizer;

import java.util.Arrays;
import java.util.Optional;

import com.copetti.pgncommon.chess.TokenInterface;

public enum TokenTypes {

	CHESS_FILE, //
	CHESS_RANK, //
	CHESS_PIECE, //
	CHESS_CAPTURE, //
	CHESS_PROMOTION, //
	CHESS_CHECK, //
	CHESS_CHECKMATE, //
	CHESS_BIG_O, //
	CHESS_DASH;

	public static Optional<TokenTypes> from(TokenInterface t) {
		String name = t.getClass() //
				.getSimpleName() //
				.toLowerCase();

		return Arrays //
				.stream(TokenTypes.values()) //
				.filter(x -> x.name().replaceAll("_", "").toLowerCase().equals(name)) //
				.findFirst(); //
	}

	@Override
	public String toString() {
		return this.name().replaceAll("CHESS_", "").toLowerCase();
	}

}
