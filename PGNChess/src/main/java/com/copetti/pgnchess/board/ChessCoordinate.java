package com.copetti.pgnchess.board;

import lombok.Getter;

public class ChessCoordinate
{
	@Getter
	private ChessFile file;
	@Getter
	private ChessRank rank;
	

	public ChessCoordinate(ChessSquare square) 
	{
		this.file = ChessFile.fromOrdinal(square.getX());
		this.rank = new ChessRank(square.getY());
	}
}
