package com.copetti.pgn.tokenizer.tokens;

import java.util.Arrays;
import java.util.Optional;

import com.copetti.pgn.tokenizer.TokenInterface;

public enum ChessFile implements TokenInterface {

	A, B, C, D, E, F, G, H;

	private static ChessFile[] chessFile = ChessFile.values();

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

	public static ChessFile last() {
		return chessFile[chessFile.length - 1];
	}

	@Override
	public String toString() {
		return getPgnNotation();
	}

	public ChessFile next() {
		return chessFile[(ordinal() + 1) % chessFile.length];
	}

	public static ChessFile first() {
		return chessFile[0];
	}

}
