package com.copetti.pgn.board;

import com.copetti.pgn.board.serializer.FEN.FENSerializer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
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

}
