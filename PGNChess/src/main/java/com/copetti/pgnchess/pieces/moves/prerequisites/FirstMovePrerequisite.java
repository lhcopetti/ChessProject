package com.copetti.pgnchess.pieces.moves.prerequisites;

import com.copetti.pgncommon.chess.board.ChessBoard;
import com.copetti.pgncommon.chess.board.ChessSquare;

public class FirstMovePrerequisite extends MovePrerequisite {

	@Override
	public boolean apply(ChessSquare self, ChessBoard board, ChessSquare target) {
		return true;
	}

}
