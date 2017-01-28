package com.copetti.pgn.command;

import java.util.Map;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.builder.ChessBoardContextBuilder;
import com.copetti.pgn.exception.KingNotInCheckException;
import com.copetti.pgn.exception.PGNInterpreterException;
import com.copetti.pgn.logic.ChessMovementResolver;
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

	public ChessBoard execute(ChessBoard input) throws PGNInterpreterException {
		if (!canExecute(input))
			return null;

		ChessBoardContextBuilder builder = ChessBoardContextBuilder.newBuilder(input);

		if (!doExecute(builder, input))
			return null;

		checkFlag(builder, input);

		postSuccessfulExecution(builder, input);

		return builder.build();
	}

	private void checkFlag(ChessBoardContextBuilder builder, ChessBoard input) throws PGNInterpreterException {

		if (getFlag() == CheckFlag.FLAG_NONE)
			return;

		if (getFlag() == CheckFlag.FLAG_CHECK)
			verifyCheckCondition(builder, input);

	}

	private void verifyCheckCondition(ChessBoardContextBuilder builder, ChessBoard input)
			throws KingNotInCheckException {

		ChessBoard board = builder.build();

		Map<ChessSquare, ColoredChessPiece> all = board.getAllPieces(input.getNextToPlay());

		ChessMovementResolver cmr = new ChessMovementResolver();

		Object oppositeKingSquare = board.getKing(input.getNextToPlay().opposite());

		all.entrySet().stream() //
				.filter(x -> cmr.getMoves(x.getKey(), board).contains(oppositeKingSquare)).findFirst()
				.orElseThrow(() -> new KingNotInCheckException(input.getNextToPlay()));

	}

	private void postSuccessfulExecution(ChessBoardContextBuilder builder, ChessBoard cb) {

		updateNextToPlay(builder, cb);
		updateEnPassant(builder, cb);
		updateHalfMoveCounter(builder, cb);
		updateFullMove(builder, cb);
	}

	private void updateFullMove(ChessBoardContextBuilder builder, ChessBoard cb) {
		if (cb.getNextToPlay() == ChessColor.BLACK)
			builder.incrementFullMove();
	}

	protected void updateEnPassant(ChessBoardContextBuilder builder, ChessBoard cb) {
		// builder.setEnpassant(null);
		return;
	}

	private void updateNextToPlay(ChessBoardContextBuilder builder, ChessBoard cb) {
		builder.nextPlayer();
	}

	private void updateHalfMoveCounter(ChessBoardContextBuilder builder, ChessBoard cb) {

		if (piece != ChessPiece.PAWN && type != ChessCommandType.CAPTURE_COMMAND)
			builder.incrementHalfMove();
	}

	protected abstract boolean canExecute(ChessBoard input);

	protected abstract boolean doExecute(ChessBoardContextBuilder builder, ChessBoard input);
}
