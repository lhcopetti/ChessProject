package com.copetti.pgn.board;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.copetti.pgn.board.serializer.FEN.FENSerializer;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class ChessBoard {

	private Map<ChessSquare, ColoredChessPiece> pieces;

	private @Getter ChessColor nextToPlay;
	private @Getter CastleInformation castleInfo;
	private @Getter ChessSquare enPassantTarget;

	private @Getter HalfMoveCounter halfMoveCounter;
	private @Getter FullMoveCounter fullMoveNumber;

	public ChessBoard(Map<ChessSquare, ColoredChessPiece> pieces, ChessBoardContext ctx) {

		/* Defensive copy */
		this.pieces = Collections.unmodifiableMap(pieces);

		this.nextToPlay = ctx.getNextToPlay();
		this.castleInfo = ctx.getCastleInfo();
		this.halfMoveCounter = ctx.getHalfMoveCounter();
		this.enPassantTarget = ctx.getEnPassantTarget();
		this.fullMoveNumber = ctx.getFullMoveNumber();
	}

	public Map<ChessSquare, ColoredChessPiece> getPieces() {
		return new HashMap<>(pieces);
	}

	public ColoredChessPiece at(int x, int y) {
		return pieces.get(new ChessSquare(x, y));
	}

	public boolean isEmpty() {
		return pieces.isEmpty();
	}

	public ColoredChessPiece at(ChessFile c, ChessRank chessRank) {
		return at(new ChessSquare(c, chessRank));
	}

	public ColoredChessPiece at(ChessSquare chessSquare) {
		return pieces.get(chessSquare);
	}

	@Override
	public String toString() {
		return new FENSerializer().serialize(this);
	}

	public boolean isEmptyAt(ChessSquare cs) {
		return at(cs) == null;
	}

	public List<ChessSquare> getAllSquaresThatContains(ColoredChessPiece coloredChessPiece) {

		return pieces.entrySet() //
				.stream() //
				.filter(entry -> entry.getValue().equals(coloredChessPiece)) //
				.map(entry -> entry.getKey()) //
				.collect(Collectors.toList());

	}

	public Map<ChessSquare, ColoredChessPiece> getAllPieces(ChessColor ofColor) {

		return pieces //
				.entrySet() //
				.stream() //
				.filter(x -> x.getValue().getColor() == ofColor) //
				.collect(Collectors.toMap(x -> x.getKey(), y -> y.getValue())); //
	}

	public ChessSquare getKing(ChessColor kingColor) {
		return pieces.entrySet() //
				.stream() //
				.filter(x -> x.getValue().getPiece() == ChessPiece.KING && x.getValue().getColor() == kingColor) //
				.findFirst() //
				.get() //
				.getKey(); //
	}
}
