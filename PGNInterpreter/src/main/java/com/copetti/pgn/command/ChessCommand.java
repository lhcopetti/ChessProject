package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessBoardContext;
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

		ChessBoard cb = doExecute(input);

		if (cb == null)
			return null;

		return postSuccessfulExecution(cb);
	}

	private ChessBoard postSuccessfulExecution(ChessBoard cb) {

		ChessBoardContext ctx = new ChessBoardContext(cb.getNextToPlay().opposite(), cb.getCastleInfo(),
				cb.getEnPassantTarget(), cb.getHalfMoveCounter(), cb.getFullMoveNumber());

		return new ChessBoard(cb.getPieces(), ctx);

	}

	protected abstract boolean canExecute(ChessBoard input);

	protected abstract ChessBoard doExecute(ChessBoard input);
}
