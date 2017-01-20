package com.copetti.pgn.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ChessBoardContext {

	private @Getter ChessColor nextToPlay;
	private @Getter CastleInformation castleInfo;
	private @Getter ChessSquare enPassantTarget;
	private @Getter HalfMoveCounter halfMoveCounter;
	private @Getter FullMoveCounter fullMoveNumber;

}
