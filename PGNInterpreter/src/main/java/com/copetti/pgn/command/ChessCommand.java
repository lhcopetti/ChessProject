package com.copetti.pgn.command;

import com.copetti.pgn.analysis.CheckBoardAnalyser;
import com.copetti.pgn.analysis.CheckmateBoardAnalyser;
import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.builder.ChessBoardContextBuilder;
import com.copetti.pgn.exception.KingIsCheckmatedException;
import com.copetti.pgn.exception.KingIsInCheckException;
import com.copetti.pgn.exception.KingNotCheckmatedException;
import com.copetti.pgn.exception.KingNotInCheckException;
import com.copetti.pgn.exception.PGNInterpreterException;
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

		postSuccessfulExecution(builder, input);

		checkFlag(builder, input);

		return builder.build();
	}

	private void checkFlag(ChessBoardContextBuilder builder, ChessBoard input) throws PGNInterpreterException {

		if (getFlag() == CheckFlag.FLAG_NONE)
			verifiyNoFlagCondition(builder, input);

		if (getFlag() == CheckFlag.FLAG_CHECK)
			verifyCheckCondition(builder, input);

		if (getFlag() == CheckFlag.FLAG_MATE)
			verifyMateCondition(builder, input);
	}

	private void verifiyNoFlagCondition(ChessBoardContextBuilder builder, ChessBoard input)
			throws PGNInterpreterException {

		CheckBoardAnalyser analyser = new CheckBoardAnalyser();
		ChessBoard board = builder.build();
		if (!analyser.validateCheckCondition(board, board.getNextToPlay()))
			return;

		CheckmateBoardAnalyser mateAnalyser = new CheckmateBoardAnalyser();
		if (mateAnalyser.validateMateCondition(board, board.getNextToPlay()))
			throw new KingIsCheckmatedException(board.getNextToPlay());

		throw new KingIsInCheckException(board.getNextToPlay());
	}

	private void verifyMateCondition(ChessBoardContextBuilder builder, ChessBoard input)
			throws PGNInterpreterException {

		CheckmateBoardAnalyser analyser = new CheckmateBoardAnalyser();

		ChessBoard board = builder.build();

		if (!analyser.validateMateCondition(board, board.getNextToPlay())) {
			throw new KingNotCheckmatedException(board.getNextToPlay(), analyser.getPiece(), analyser.getOriginSquare(),
					analyser.getDestinationSquare());
		}
	}

	private void verifyCheckCondition(ChessBoardContextBuilder builder, ChessBoard input)
			throws PGNInterpreterException {

		ChessBoard board = builder.build();
		CheckBoardAnalyser analyser = new CheckBoardAnalyser();
		ChessColor checkColor = board.getNextToPlay();

		if (!analyser.validateCheckCondition(board, checkColor))
			throw new KingNotInCheckException(checkColor);

		CheckmateBoardAnalyser mateAnalyser = new CheckmateBoardAnalyser();
		if (mateAnalyser.validateMateCondition(board, checkColor))
			throw new KingIsCheckmatedException(checkColor);

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

	protected abstract boolean canExecute(ChessBoard input) throws PGNInterpreterException;

	protected abstract boolean doExecute(ChessBoardContextBuilder builder, ChessBoard input)
			throws PGNInterpreterException;
}
