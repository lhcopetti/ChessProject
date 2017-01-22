package com.copetti.pgn.command;

import java.util.List;
import java.util.Map;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessBoardContext;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.command.decorator.PromotionDecorator;
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
	protected boolean canExecute(ChessBoard input) {

		if (!checkTargetSquare(input))
			return false;

		ChessSquare cs = getCommandTarget(input);

		if (cs == null)
			return false;

		targetCommand = cs;
		return true;
	}

	@Override
	protected ChessBoard doExecute(ChessBoard input) {

		if (null == targetCommand)
			return null;

		Map<ChessSquare, ColoredChessPiece> map = input.getPieces();
		ColoredChessPiece cp = map.remove(targetCommand);
		map.put(destinationSquare, cp);

		ChessBoardContext ctx = new ChessBoardContext(input.getNextToPlay(), input.getCastleInfo(),
				input.getEnPassantTarget(), input.getHalfMoveCounter(), input.getFullMoveNumber());

		return new ChessBoard(map, ctx);
	}

	protected final ChessSquare getCommandTarget(ChessBoard input) {

		ColoredChessPiece cp = new ColoredChessPiece(getPiece(), input.getNextToPlay());
		List<ChessSquare> pieces = input.getAllSquaresThatContains(cp);

		ChessMovementResolver cmr = new ChessMovementResolver(getPiece());
		return pieces //
				.stream() //
				.filter(x -> cmr.isValidMovement(input, x, getDestinationSquare())) //
				.findFirst().orElse(null);
	}
}
