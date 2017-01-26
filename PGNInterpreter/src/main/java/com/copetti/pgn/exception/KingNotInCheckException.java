package com.copetti.pgn.exception;

import com.copetti.pgn.board.ChessColor;

public class KingNotInCheckException extends PGNInterpreterException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9005001263513375753L;

	public KingNotInCheckException(ChessColor playerColor) {
		super("The " + playerColor.opposite() + " king is not in check");
	}

}
