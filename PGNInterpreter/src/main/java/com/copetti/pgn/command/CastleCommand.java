package com.copetti.pgn.command;

import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public abstract class CastleCommand extends ChessCommand {

	public CastleCommand(ChessPiece chessPiece) {
		super(chessPiece);
	}

}
