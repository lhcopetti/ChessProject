package com.copetti.pgnchess.pgn.command;

import com.copetti.pgncommon.chess.board.ChessSquare;

import lombok.Getter;


public abstract class ChessCommand
{

	public ChessCommand(ChessSquare origin, ChessSquare destination,
			boolean isCapture)
	{
		this.origin = origin;
		this.destination = destination;
		this.isCapture = isCapture;
	}

	private @Getter ChessSquare origin;
	private @Getter ChessSquare destination;

	private @Getter boolean isCapture;

}
