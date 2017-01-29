package com.copetti.pgn.exception;

import com.copetti.pgn.board.ChessColor;

public class KingIsInCheckException extends PGNInterpreterException {

	public KingIsInCheckException(ChessColor color) {
		super(color + " king is in check");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2774513011414450136L;

}
