package com.copetti.pgnchess.pieces;


public enum ChessColor
{
	WHITE
	{
		@Override
		public String toString()
		{
			return "White";
		}

		@Override
		public ChessColor opposite()
		{
			return BLACK;
		}
	},
	BLACK
	{
		@Override
		public String toString()
		{
			return "Black";
		}

		@Override
		public ChessColor opposite()
		{
			return WHITE;
		}
	};
	
	
	public abstract ChessColor opposite();
}
