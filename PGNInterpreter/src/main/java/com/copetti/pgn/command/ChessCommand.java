package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessBoardContext;
import com.copetti.pgn.board.HalfMoveCounter;
import com.copetti.pgn.board.builder.ChessBoardContextBuilder;
import com.copetti.pgn.board.map.ChessBoardBuilder;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.Getter;

public abstract class ChessCommand {

	public enum ChessCommandType {
		DISPLACEMENT_COMMAND, CAPTURE_COMMAND;
	}

	public enum CheckFlag {
		FLAG_NONE, FLAG_CHECK, FLAG_MATE
	}

	private @Getter ChessPiece piece;
	private @Getter CheckFlag flag;

	private ChessCommandType type;

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

		ChessBoardContextBuilder builder = ChessBoardContextBuilder.newBuilder(cb);

		updateNextToPlay(builder, cb);
		updateHalfMoveCounter(builder, cb);

		return builder.build();

	}

	private void updateNextToPlay(ChessBoardContextBuilder builder, ChessBoard cb) {
		builder.nextPlayer();
	}

	private void updateHalfMoveCounter(ChessBoardContextBuilder builder, ChessBoard cb) {

		if (piece != ChessPiece.PAWN && type != ChessCommandType.CAPTURE_COMMAND)
			builder.incrementHalfMove();
	}

	protected abstract boolean canExecute(ChessBoard input);

	protected abstract ChessBoard doExecute(ChessBoard input);
}
