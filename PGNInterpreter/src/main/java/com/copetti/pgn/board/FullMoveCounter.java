package com.copetti.pgn.board;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FullMoveCounter {

	private @Getter int counter = 1;

	public static FullMoveCounter first() {
		return new FullMoveCounter(1);
	}
	
	@Override
	public String toString() {
		return String.valueOf(counter);
	}
}
