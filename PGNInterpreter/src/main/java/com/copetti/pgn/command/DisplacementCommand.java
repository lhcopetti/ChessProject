package com.copetti.pgn.command;

import java.util.List;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.builder.ChessBoardContextBuilder;
import com.copetti.pgn.logic.ChessMovementResolver;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class DisplacementCommand extends ChessCommand {

	private @Getter ChessSquare targetCommand;
	private @Getter ChessSquare destinationSquare;

	@Setter(value = AccessLevel.PUBLIC)
	private @Getter PromotionDecorator promotion;

	public DisplacementCommand(ChessPiece chessPiece, ChessSquare destSquare, CheckFlag flag) {
		super(chessPiece, flag);
		this.destinationSquare = destSquare;
	}

	protected abstract boolean checkTargetSquare(ChessBoard board);

	@Override
	protected final boolean canExecute(ChessBoard input) {

		if (!checkTargetSquare(input))
			return false;

		ChessSquare cs = getCommandTarget(input);

		if (cs == null)
			return false;

		targetCommand = cs;
		return true;
	}

	@Override
	protected boolean doExecute(ChessBoardContextBuilder builder, ChessBoard input) {

		if (null == targetCommand)
			return false;

		ColoredChessPiece cp = builder.getPieces().remove(targetCommand);
		builder.getPieces().put(destinationSquare, cp);
		return true;
	}

	protected final ChessSquare getCommandTarget(ChessBoard input) {

		ColoredChessPiece cp = new ColoredChessPiece(getPiece(), input.getNextToPlay());
		List<ChessSquare> pieces = input.getAllSquaresThatContains(cp);

		ChessMovementResolver cmr = new ChessMovementResolver();
		return pieces //
				.stream() //
				.filter(x -> cmr.isValidMovement(input, x, getDestinationSquare())) //
				.findFirst().orElse(null);
	}
}
