package com.copetti.pgnchess.pgn.command;

import com.copetti.pgncommon.chess.board.ChessSquare;

public class PawnMoveCommand extends ChessCommand
{

	public PawnMoveCommand(ChessSquare origin, ChessSquare dest)
	{
		super(origin, dest, false);
	}

	
}
