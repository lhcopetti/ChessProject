package com.copetti.pgn.exception;

import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class InvalidPromotionPieceException extends PGNInterpreterException {

	public InvalidPromotionPieceException(ChessPiece targetPiece) {
		super("Cannot promote to " + targetPiece.name().toLowerCase());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6850936626662810951L;

}
