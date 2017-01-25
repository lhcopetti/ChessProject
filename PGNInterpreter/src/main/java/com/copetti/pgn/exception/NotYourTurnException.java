package com.copetti.pgn.exception;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;

public class NotYourTurnException extends PGNInterpreterException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotYourTurnException(ChessBoard cb, ChessSquare self) {

		super("It is not " + cb.at(self).getColor() + "'s turn to play. Board: " + cb.toString());
	}

}
