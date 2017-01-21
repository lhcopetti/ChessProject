package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.Getter;

public abstract class ChessCommand {

	public enum CheckFlag {
		FLAG_NONE, FLAG_CHECK, FLAG_MATE
	}

	private @Getter ChessPiece piece;
	private @Getter CheckFlag flag;

	public ChessCommand(ChessPiece chessPiece, CheckFlag flag) {
		this.piece = chessPiece;
		this.flag = flag;
	}

	public ChessBoard execute(ChessBoard input) {
		if (!canExecute(input))
			return null;

		return doExecute(input);
	}

	protected abstract boolean canExecute(ChessBoard input);

	protected abstract ChessBoard doExecute(ChessBoard input);
}
