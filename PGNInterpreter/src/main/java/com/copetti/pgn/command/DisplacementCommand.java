package com.copetti.pgn.command;

import java.util.List;
import java.util.stream.Stream;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.builder.ChessBoardContextBuilder;
import com.copetti.pgn.exception.InvalidPromotionPieceException;
import com.copetti.pgn.exception.PGNInterpreterException;
import com.copetti.pgn.logic.ChessMovementResolver;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class DisplacementCommand extends ChessCommand {

	private @Setter @Getter ChessFile desambiguationFile;
	private @Setter @Getter ChessRank desambiguationRank;

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
	protected boolean doExecute(ChessBoardContextBuilder builder, ChessBoard input) throws PGNInterpreterException {

		if (null == targetCommand)
			return false;

		ColoredChessPiece cp = builder.getPieces().remove(targetCommand);

		ChessPiece targetPiece = checkForPromotion();
		if (targetPiece != null)
			cp = new ColoredChessPiece(targetPiece, cp.getColor());

		builder.getPieces().put(destinationSquare, cp);
		return true;
	}

	private ChessPiece checkForPromotion() throws PGNInterpreterException {

		if (promotion == null)
			return null;

		if (promotion.getPiece() == ChessPiece.KING || promotion.getPiece() == ChessPiece.PAWN)
			throw new InvalidPromotionPieceException(promotion.getPiece());

		return promotion.getPiece();
	}

	protected final ChessSquare getCommandTarget(ChessBoard input) {

		ColoredChessPiece cp = new ColoredChessPiece(getPiece(), input.getNextToPlay());
		List<ChessSquare> pieces = input.getAllSquaresThatContains(cp);

		ChessMovementResolver cmr = new ChessMovementResolver();

		Stream<ChessSquare> filter = pieces //
				.stream() //
				.filter(x -> cmr.isValidMovement(input, x, getDestinationSquare()))
				.filter(x -> desambiguationFile != null ? x.getFile().equals(desambiguationFile) : true)
				.filter(x -> desambiguationRank != null ? x.getRank().equals(desambiguationRank) : true);

		return filter.findFirst().orElse(null);
	}

}
