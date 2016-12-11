package com.copetti.pgnchess.board;

import java.util.HashMap;
import java.util.Map;

import com.copetti.pgnchess.board.message.RequestPrint;
import com.copetti.pgnchess.pieces.ChessColor;
import com.copetti.pgnchess.pieces.ChessPiece;
import com.copetti.pgnchess.pieces.Pawn;

import akka.actor.UntypedActor;

public class ChessBoard extends UntypedActor
{
	private Map<ChessSquare, ChessPiece> pieces;
	
	public ChessBoard() {
		pieces = new HashMap<>();
		
		for (int i = 0; i < 8; ++i)
		{
			pieces.put(new ChessSquare(i, 1), new Pawn(ChessColor.WHITE));
			pieces.put(new ChessSquare(i, 6), new Pawn(ChessColor.WHITE));
		}
	}

	@Override
	public void onReceive(Object msg) throws Exception
	{
		if (msg instanceof RequestPrint)
			System.out.println(BoardPrinter.print(this));
	}
	
	public ChessPiece at(int x, int y)
	{
		return pieces.get(new ChessSquare(x, y));
	}
}
