package com.copetti.service;

import com.copetti.pgn.board.ChessBoard;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ChessBoardResult {

	private @Getter ChessBoard board;
	private String errorMessage;
	
	
	public boolean containsError()
	{
		return board == null;
	}
	public String getErrorMessage()
	{
		return errorMessage == null ? 
				"The command is invalid for the board supplied " + board.toString() : errorMessage;
	}
	
	
}
