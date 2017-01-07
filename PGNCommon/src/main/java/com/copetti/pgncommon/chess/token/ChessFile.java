package com.copetti.pgncommon.chess.token;

import java.util.Arrays;
import java.util.Optional;

import com.copetti.pgncommon.chess.TokenInterface;

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

}
