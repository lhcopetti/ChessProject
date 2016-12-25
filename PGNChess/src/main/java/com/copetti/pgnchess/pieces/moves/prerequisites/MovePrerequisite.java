package com.copetti.pgnchess.pieces.moves.prerequisites;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;

public abstract class MovePrerequisite {

	public abstract boolean apply(ChessSquare self, ChessBoard board, ChessSquare target);

}
