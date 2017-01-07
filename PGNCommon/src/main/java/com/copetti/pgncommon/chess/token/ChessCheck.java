package com.copetti.pgncommon.chess.token;

import java.util.Optional;

import com.copetti.pgncommon.chess.TokenInterface;

public class ChessCheck implements TokenInterface {
	private String value;

	private ChessCheck(String input) {
		this.value = input;
	}

	public static Optional<ChessCheck> of(String input) {

		if (!input.equals("+"))
			return Optional.empty();

		return Optional.of(new ChessCheck(input));
	}

	@Override
	public String getValue() {
		return value;
	}
}
