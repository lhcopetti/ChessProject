package com.copetti.pgn.board;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HalfMoveCounter {

	private @Getter int counter = 0;

	public static HalfMoveCounter first() {
		return new HalfMoveCounter(0);
	}

	@Override
	public String toString() {
		return String.valueOf(counter);
	}

	public HalfMoveCounter next() {
		return new HalfMoveCounter(counter + 1);
	}

}
