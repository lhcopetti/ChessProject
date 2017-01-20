package com.copetti.pgncommon.chess.token;

import java.util.Optional;

import com.copetti.pgn.tokenizer.TokenInterface;

public enum ChessBigO implements TokenInterface {

	CHESS_BIG_O("O");

	private String value;

	private ChessBigO(String input) {
		this.value = input;
	}

	public static Optional<ChessBigO> of(String input) {

		if (!input.equals("O"))
			return Optional.empty();

		return Optional.of(ChessBigO.CHESS_BIG_O);
	}

	@Override
	public String getValue() {
		return value;
	}
}
