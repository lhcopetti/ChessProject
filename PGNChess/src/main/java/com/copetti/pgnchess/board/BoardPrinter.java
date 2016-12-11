package com.copetti.pgnchess.board;

import com.copetti.pgnchess.pieces.ChessPiece;


public class BoardPrinter
{

	public static String print(ChessBoard board)
	{
		String chessBoard = "";

		for( int y = 7; y >= 0; --y )
		{
			for( int x = 0; x < 8; ++x )
			{
				chessBoard += getString(board, x, y) + " ";
			}
			chessBoard += System.lineSeparator();
		}
		return chessBoard;
	}

	private static String getString(ChessBoard board, int x, int y)
	{
		ChessPiece piece;
		if ((piece = board.at(x, y)) == null) return "_";

		return piece.getChessPieceType().getPgnFormat();
	}
}
