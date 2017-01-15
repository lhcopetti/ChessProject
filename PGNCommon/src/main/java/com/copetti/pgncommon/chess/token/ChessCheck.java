package com.copetti.pgncommon.chess.token;

import java.util.Optional;

import com.copetti.pgncommon.chess.TokenInterface;

public enum ChessCheck implements TokenInterface {

	CHESS_CHECK("+");

	private String value;

	private ChessCheck(String input) {
		this.value = input;
	}

	public static Optional<ChessCheck> of(String input) {

		if (!input.equals("+"))
			return Optional.empty();

		return Optional.of(CHESS_CHECK);
	}

	@Override
	public String getValue() {
		return value;
	}
}
