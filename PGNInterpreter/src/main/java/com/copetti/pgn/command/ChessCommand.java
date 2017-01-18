package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.Getter;

public abstract class ChessCommand {

	private @Getter ChessPiece piece;

	public ChessCommand(ChessPiece chessPiece) {
		this.piece = chessPiece;
	}

	public ChessBoard execute(ChessBoard input) {
		if (!canExecute(input))
			return null;

		return doExecute(input);
	}

	protected abstract boolean canExecute(ChessBoard input);

	protected abstract ChessBoard doExecute(ChessBoard input);
}
