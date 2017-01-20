package com.copetti.pgn.board;

import java.util.Map;

import com.copetti.pgn.board.serializer.FEN.FENSerializer;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

import lombok.Getter;

public class ChessBoard {

	private Map<ChessSquare, ColoredChessPiece> pieces;

	private @Getter ChessColor nextToPlay;
	private @Getter CastleInformation castleInfo;
	private @Getter ChessSquare enPassantTarget;

	private @Getter HalfMoveCounter halfMoveCounter;
	private @Getter FullMoveCounter fullMoveNumber;

	public ChessBoard(Map<ChessSquare, ColoredChessPiece> pieces, ChessBoardContext ctx) {
		this.pieces = pieces;

		this.nextToPlay = ctx.getNextToPlay();
		this.castleInfo = ctx.getCastleInfo();
		this.halfMoveCounter = ctx.getHalfMoveCounter();
		this.enPassantTarget = ctx.getEnPassantTarget();
		this.fullMoveNumber = ctx.getFullMoveNumber();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((castleInfo == null) ? 0 : castleInfo.hashCode());
		result = prime * result + ((enPassantTarget == null) ? 0 : enPassantTarget.hashCode());
		result = prime * result + ((fullMoveNumber == null) ? 0 : fullMoveNumber.hashCode());
		result = prime * result + ((halfMoveCounter == null) ? 0 : halfMoveCounter.hashCode());
		result = prime * result + ((nextToPlay == null) ? 0 : nextToPlay.hashCode());
		result = prime * result + ((pieces == null) ? 0 : pieces.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ChessBoard))
			return false;
		ChessBoard other = (ChessBoard) obj;
		if (castleInfo == null) {
			if (other.castleInfo != null)
				return false;
		} else if (!castleInfo.equals(other.castleInfo))
			return false;
		if (enPassantTarget == null) {
			if (other.enPassantTarget != null)
				return false;
		} else if (!enPassantTarget.equals(other.enPassantTarget))
			return false;
		if (fullMoveNumber == null) {
			if (other.fullMoveNumber != null)
				return false;
		} else if (!fullMoveNumber.equals(other.fullMoveNumber))
			return false;
		if (halfMoveCounter == null) {
			if (other.halfMoveCounter != null)
				return false;
		} else if (!halfMoveCounter.equals(other.halfMoveCounter))
			return false;
		if (nextToPlay != other.nextToPlay)
			return false;
		if (pieces == null) {
			if (other.pieces != null)
				return false;
		} else if (!pieces.equals(other.pieces))
			return false;
		return true;
	}
}
