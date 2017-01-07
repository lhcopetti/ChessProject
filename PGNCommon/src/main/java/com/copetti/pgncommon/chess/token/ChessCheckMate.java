package com.copetti.pgncommon.chess.token;

import java.util.Optional;

import com.copetti.pgncommon.chess.TokenInterface;

public class ChessCheckMate implements TokenInterface {
	private String value;

	private ChessCheckMate(String input) {
		this.value = input;
	}

	public static Optional<ChessCheckMate> of(String input) {

		if (!input.equals("#"))
			return Optional.empty();

		return Optional.of(new ChessCheckMate(input));
	}

	@Override
	public String getValue() {
		return value;
	}
}
