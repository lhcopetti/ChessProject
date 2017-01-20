package com.copetti.pgn.board;

import com.copetti.pgn.board.serializer.FEN.FENSerializer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
public class CastleInformation {

	private @Getter boolean whiteKingCastle;
	private @Getter boolean whiteQueenCastle;

	private @Getter boolean blackKingCastle;
	private @Getter boolean blackQueenCastle;

	public static CastleInformation all() {
		return new CastleInformation(true, true, true, true);
	}

	public static CastleInformation none() {
		return new CastleInformation(false, false, false, false);
	}

	@Override
	public String toString() {
		return new FENSerializer().serialize(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (blackKingCastle ? 1231 : 1237);
		result = prime * result + (blackQueenCastle ? 1231 : 1237);
		result = prime * result + (whiteKingCastle ? 1231 : 1237);
		result = prime * result + (whiteQueenCastle ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CastleInformation))
			return false;
		CastleInformation other = (CastleInformation) obj;
		if (blackKingCastle != other.blackKingCastle)
			return false;
		if (blackQueenCastle != other.blackQueenCastle)
			return false;
		if (whiteKingCastle != other.whiteKingCastle)
			return false;
		if (whiteQueenCastle != other.whiteQueenCastle)
			return false;
		return true;
	}
}
