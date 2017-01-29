package com.copetti.pgn.exception;

import com.copetti.pgn.board.ChessBoard;

public class CantCastleQueenSideException extends PGNInterpreterException {

	public CantCastleQueenSideException(ChessBoard board) {
		super("Castling queen side is no longer available for " + board.getNextToPlay().toString());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8256901622090420521L;

}
