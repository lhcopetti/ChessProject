package com.copetti.pgn.tokenizer.tokens;

import java.util.Optional;

import com.copetti.pgn.tokenizer.TokenInterface;

public enum ChessCheckMate implements TokenInterface {

	CHESS_MATE("#");

	private String value;

	private ChessCheckMate(String input) {
		this.value = input;
	}

	public static Optional<ChessCheckMate> of(String input) {

		if (!input.equals("#"))
			return Optional.empty();

		return Optional.of(ChessCheckMate.CHESS_MATE);
	}

	@Override
	public String getValue() {
		return value;
	}
}
