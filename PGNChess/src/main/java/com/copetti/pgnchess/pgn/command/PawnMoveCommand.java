package com.copetti.pgnchess.pgn.command;

import com.copetti.pgnchess.board.ChessSquare;

public class PawnMoveCommand extends ChessCommand
{

	public PawnMoveCommand(ChessSquare origin, ChessSquare dest)
	{
		super(origin, dest, false);
	}

	
}
