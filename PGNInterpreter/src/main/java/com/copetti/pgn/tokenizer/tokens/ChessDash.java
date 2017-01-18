package com.copetti.pgn.tokenizer.tokens;

import java.util.Optional;

import com.copetti.pgn.tokenizer.TokenInterface;

public enum ChessDash implements TokenInterface {

	DASH_SIGN("-");

	private String value;

	private ChessDash(String input) {
		this.value = input;
	}

	public static Optional<ChessDash> of(String input) {
		if (!input.equals("-"))
			return Optional.empty();

		return Optional.of(ChessDash.DASH_SIGN);
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

}
