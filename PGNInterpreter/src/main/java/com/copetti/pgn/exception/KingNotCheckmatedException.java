package com.copetti.pgn.exception;

import com.copetti.pgn.board.ChessColor;

public class KingNotCheckmatedException extends PGNInterpreterException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8950531785984466214L;

	public KingNotCheckmatedException(ChessColor color) {
		super("The " + color.opposite() + " king is not checkmated.");
	}

}
