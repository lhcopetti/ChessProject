package com.copetti.pgn.command.factory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.command.ChessCommand.CheckFlag;
import com.copetti.pgn.lexical.state.CaptureState;
import com.copetti.pgn.lexical.state.CastleLongState;
import com.copetti.pgn.lexical.state.CastleShortState;
import com.copetti.pgn.lexical.state.DesambiguateFileState;
import com.copetti.pgn.lexical.state.DesambiguateRankState;
import com.copetti.pgn.lexical.state.DestinationSquareState;
import com.copetti.pgn.lexical.state.LexicalState;
import com.copetti.pgn.lexical.state.PromotionState;
import com.copetti.pgn.lexical.state.container.CheckFlagContainer;
import com.copetti.pgn.lexical.state.container.ChessPieceContainer;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

import lombok.Getter;

public class LexicalStateCollection {

	private @Getter List<LexicalState> collection;

	public LexicalStateCollection(List<LexicalState> collection) {
		this.collection = Collections.unmodifiableList(collection);

	}

	public boolean isShortCastleCommand() {
		return contains(CastleShortState.class);
	}

	public boolean isLongCastleCommand() {
		return contains(CastleLongState.class);
	}

	public boolean isCaptureCommand() {
		return contains(CaptureState.class);
	}

	public boolean isPromotion() {
		return contains(PromotionState.class);
	}

	public ChessFile getDesambiguationFile() {

		Optional<DesambiguateFileState> d = getOptionalOf(DesambiguateFileState.class);

		if (!d.isPresent())
			return null;

		return d.get().getFile();
	}

	public ChessRank getDesambiguationRank() {
		Optional<DesambiguateRankState> r = getOptionalOf(DesambiguateRankState.class);

		if (!r.isPresent())
			return null;

		return r.get().getRank();

	}

	public CheckFlag getFlag() {

		return collection //
				.stream() //
				.filter(x -> CheckFlagContainer.class.isAssignableFrom(x.getClass())) //
				.map(x -> (CheckFlagContainer) x) //
				.findFirst() //
				.orElse(() -> CheckFlag.FLAG_NONE) //
				.getFlag();
	}

	public ChessSquare getDestinationSquare() {

		return collection //
				.stream() //
				.filter(x -> x.getClass().isAssignableFrom(DestinationSquareState.class)) //
				.map(x -> (DestinationSquareState) x) //
				.map(x -> new ChessSquare(x.getFile(), x.getRank())) //
				.findFirst() //
				.get();
	}

	public ChessPiece getChessPiece() {

		return collection //
				.stream() //
				.filter(x -> ChessPieceContainer.class.isAssignableFrom(x.getClass())) //
				.map(x -> ((ChessPieceContainer) x).getPiece()) //
				.findFirst() //
				.orElse(ChessPiece.PAWN);
	}

	public ChessPiece getTargetPromotionPiece() {
		return collection //
				.stream() //
				.filter(x -> x.getClass().isAssignableFrom(PromotionState.class)) //
				.map(x -> (PromotionState) x).map(x -> x.getPiece()) //
				.findFirst() //
				.get();
	}

	private boolean contains(Class<? extends LexicalState> clazz) {
		return collection.stream().anyMatch(x -> x.getClass().isAssignableFrom(clazz));
	}

	@SuppressWarnings("unchecked")
	private <T> Optional<T> getOptionalOf(Class<T> clazz) {
		return (Optional<T>) collection //
				.stream(). //
				filter(x -> x.getClass().isAssignableFrom(clazz)) //
				.findFirst(); //
	}

}
