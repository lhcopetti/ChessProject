package com.copetti.pgn.logic.moves.prerequisites;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;

public class EmptySquarePrerequisite extends MovePrerequisite {

	@Override
	public boolean apply(ChessBoard board, ChessSquare self, ChessSquare target) {
		return board.isEmptyAt(target);
	}

}
