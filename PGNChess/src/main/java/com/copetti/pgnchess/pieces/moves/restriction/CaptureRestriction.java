package com.copetti.pgnchess.pieces.moves.restriction;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;

public class CaptureRestriction extends MoveRestriction {

	@Override
	public boolean apply(ChessSquare self, ChessBoard board, ChessSquare target) {
		return false;
	}

}
