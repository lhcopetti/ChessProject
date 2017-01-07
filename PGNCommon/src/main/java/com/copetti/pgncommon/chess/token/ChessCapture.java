package com.copetti.pgncommon.chess.token;

import java.util.Optional;

import com.copetti.pgncommon.chess.TokenInterface;

public class ChessCapture implements TokenInterface {

	private String value;

	private ChessCapture(String input) {
		this.value = input;
	}

	public static Optional<ChessCapture> of(String input) {

		if (!input.equals("x"))
			return Optional.empty();

		return Optional.of(new ChessCapture(input));
	}

	@Override
	public String getValue() {
		return value;
	}

}
