package com.copetti.pgn.exception;

import com.copetti.pgn.board.ChessBoard;

public class CantCastleKingSideException extends PGNInterpreterException {

	public CantCastleKingSideException(ChessBoard board) {
		super("Castling king side is no longer available for " + board.getNextToPlay().toString());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 9181842387076499802L;

}
