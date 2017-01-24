package com.copetti.pgn.logic.moves.prerequisites;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;

public class EnPassantPrerequisite extends MovePrerequisite {

	@Override
	public boolean apply(ChessBoard board, ChessSquare self, ChessSquare target) {

		ChessSquare enPassant = board.getEnPassantTarget();

		if (null == enPassant)
			return false;

		if (!enPassant.equals(target))
			return false;

		return true;
	}
}
