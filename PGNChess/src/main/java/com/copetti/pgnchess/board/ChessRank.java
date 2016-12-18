package com.copetti.pgnchess.board;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Getter;

public class ChessRank {

	@Getter
	private int value;

	public ChessRank(int rank) {
		if (rank < 1 || rank > 8)
			throw new IllegalArgumentException("A rank of " + rank + " is invalid.");

		this.value = rank;
	}

	public static List<ChessRank> values() {
		return IntStream //
				.rangeClosed(1, 8) //
				.mapToObj(v -> new ChessRank(v)) //
				.collect(Collectors.toList());
	}

}
