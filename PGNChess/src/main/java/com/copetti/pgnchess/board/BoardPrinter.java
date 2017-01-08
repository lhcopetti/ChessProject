package com.copetti.pgnchess.board;

import com.copetti.pgnchess.pieces.ChessPiece;
import com.copetti.pgncommon.chess.board.ChessBoard;


public class BoardPrinter
{

	public String print(ChessBoard board)
	{
		String chessBoard = "";

		for( int y = 7; y >= 0; --y )
		{
			for( int x = 0; x < 8; ++x )
			{
				chessBoard += getString(board, x, y);
				chessBoard += x < 7 ? " " : ""; 
			}
			chessBoard += y > 0 ?  System.lineSeparator() : "";
		}
		return chessBoard;
	}

	private String getString(ChessBoard board, int x, int y)
	{
		ChessPiece piece;
		if ((piece = board.at(x, y)) == null) return "__";

		return piece.getColor().shortString() + piece.getChessPieceType().getPgnFormat();
	}
}
