package com.copetti.pgnchess.board;

public enum ChessFile
{
	A, B, C, D, E, F, G, H;

	public int value()
	{
		return this.ordinal();
	}

	public static ChessFile fromOrdinal(int ordinal)
	{
		for( ChessFile f : ChessFile.values() )
			if (f.ordinal() == ordinal) return f;

		throw new IllegalArgumentException(
				"No ChessFile could be found with value: " + ordinal);
	}
}
