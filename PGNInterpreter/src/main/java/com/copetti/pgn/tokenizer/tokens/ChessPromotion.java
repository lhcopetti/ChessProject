package com.copetti.pgn.tokenizer.tokens;

import java.util.Optional;

import com.copetti.pgn.tokenizer.TokenInterface;

public enum ChessPromotion implements TokenInterface {

	PROMOTION_SIGN("=");

	private String value;

	private ChessPromotion(String input) {
		this.value = input;
	}

	public static Optional<ChessPromotion> of(String input) {

		if (!input.equals("="))
			return Optional.empty();

		return Optional.of(ChessPromotion.PROMOTION_SIGN);
	}

	@Override
	public String getValue() {
		return value;
	}
}
