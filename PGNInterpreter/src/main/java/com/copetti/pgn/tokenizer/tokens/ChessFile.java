package com.copetti.pgn.tokenizer.tokens;

import java.util.Arrays;
import java.util.Optional;

import com.copetti.pgn.tokenizer.TokenInterface;

public enum ChessFile implements TokenInterface {

	A, B, C, D, E, F, G, H;

	public static Optional<ChessFile> of(String input) {

		return Arrays //
				.stream(ChessFile.values()) //
				.filter(x -> x.getPgnNotation().equals(input)) //
				.findFirst(); //
	}

	public String getPgnNotation() {
		return this.name().toLowerCase();
	}

	@Override
	public String getValue() {
		return getPgnNotation();
	}

	public static Optional<ChessFile> fromOrdinal(int ordinal) {

		return Arrays //
				.stream(ChessFile.values()). //
				filter(x -> x.ordinal() == ordinal). //
				findFirst();
	}

	@Override
	public String toString() {
		return getPgnNotation();
	}

}
