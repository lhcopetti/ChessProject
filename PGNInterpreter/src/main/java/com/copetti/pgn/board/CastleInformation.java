package com.copetti.pgn.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CastleInformation {

	private @Getter boolean wCanCastleKingSide;
	private @Getter boolean wCanCastleQueenSide;

	private @Getter boolean bCanCastleKingSide;
	private @Getter boolean bCanCastleQueenSide;

}
