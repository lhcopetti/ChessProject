package com.copetti.pgnchess.board;

import lombok.Getter;


public class ChessRank
{

	@Getter
	private int value;

	public ChessRank(int rank)
	{
		if (rank < 1 || rank > 8) throw new IllegalArgumentException(
				"A rank of " + rank + " is invalid.");

		this.value = rank;
	}

}
