package com.copetti.pgn.tokenizer.tokens;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.copetti.pgn.tokenizer.TokenInterface;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChessRank implements TokenInterface {

	private @Getter int rank;

	private static List<ChessRank> values = values();

	private static ChessRank last = values.get(values.size() - 1);
	private static ChessRank first = values.get(0);

	public static ChessRank fromIndex(int index) {
		return ChessRank.of("" + (index + 1)).get();
	}

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
	public String toString() {
		return getValue();
	}

	public static List<ChessRank> values() {
		return IntStream //
				.rangeClosed(1, 8) //
				.mapToObj(v -> new ChessRank(v)) //
				.collect(Collectors.toList());
	}

	public ChessRank previous() {
		return rank - 1 >= 0 ? new ChessRank(rank - 1) : last;
	}

	public static ChessRank last() {
		return last;
	}

	public static ChessRank first() {
		return first;
	}

}