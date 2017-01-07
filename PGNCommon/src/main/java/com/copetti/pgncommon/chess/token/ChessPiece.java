package com.copetti.pgncommon.chess.token;

import java.util.Arrays;
import java.util.Optional;

import com.copetti.pgncommon.chess.TokenInterface;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ChessPiece implements TokenInterface {

	PAWN("P"), //
	KNIGHT("N"), //
	BISHOP("B"), //
	ROOK("R"), //
	QUEEN("Q"), //
	KING("K"); //

	private @Getter String pgnNotation;

	public String getValue() {
		return pgnNotation;
	}

	public static Optional<ChessPiece> of(String input) {

		return Arrays //
				.stream(ChessPiece.values()) //
				.filter(x -> x.getPgnNotation().equals(input)) //
				.findFirst(); //
	}

}
