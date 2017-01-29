package com.copetti.pgn.exception;

import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;

public class KingNotCheckmatedException extends PGNInterpreterException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8950531785984466214L;

	public KingNotCheckmatedException(ChessColor color, ColoredChessPiece p, ChessSquare origin,
			ChessSquare destination) {
		super("The " + color.opposite() + " king is not checkmated. " + p.getPiece().toString() + "(" + origin + ") -> "
				+ destination + " is a valid move.");
	}

}
