package com.copetti.pgn.exception;

import com.copetti.pgn.board.ChessColor;

public class KingIsCheckmatedException extends PGNInterpreterException {

	public KingIsCheckmatedException(ChessColor color) {
		super(color + " is checkmated");
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7586651583923611142L;

}
