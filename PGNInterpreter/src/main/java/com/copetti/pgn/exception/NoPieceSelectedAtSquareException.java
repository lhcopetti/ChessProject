package com.copetti.pgn.exception;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;

public class NoPieceSelectedAtSquareException extends PGNInterpreterException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3578865375867227178L;

	public NoPieceSelectedAtSquareException(ChessBoard board, ChessSquare cs) {
		super("There is no piece at [" + cs.toString() + "] for the board: " + board.toString());
	}

}
