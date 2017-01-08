package com.copetti.pgncommon.chess.token;

import java.util.Optional;

import com.copetti.pgncommon.chess.TokenInterface;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChessRank implements TokenInterface {

	private @Getter int rank;

	public static Optional<ChessRank> of(String input) {
		int result;
		try {
			result = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return Optional.empty();
		}

		if (result < 0 || result > 8)
			return Optional.empty();

		return Optional.of(new ChessRank(result));
	}

	public String getValue() {
		return String.valueOf(rank);
	}

}