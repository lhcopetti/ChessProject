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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rank;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ChessRank))
			return false;
		ChessRank other = (ChessRank) obj;
		if (rank != other.rank)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getValue();
	}

}