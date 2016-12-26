package com.copetti.pgnchess.pieces.moves.prerequisites;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;

public class EmptySquarePrerequisite extends MovePrerequisite {

	@Override
	public boolean apply(ChessSquare self, ChessBoard board, ChessSquare target) {

		return board.at(target) == null;
	}

}