package com.copetti.pgn.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CastleInformation {

	private @Getter boolean kingCastleAvaiableForWhite;
	private @Getter boolean queenCastleAvaiableForWhite;

	private @Getter boolean kingCastleAvailableForBlack;
	private @Getter boolean queenCastleAvailableForBlack;

}
