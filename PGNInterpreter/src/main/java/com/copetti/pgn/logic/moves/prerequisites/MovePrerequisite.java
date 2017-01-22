package com.copetti.pgn.logic.moves.prerequisites;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;

public abstract class MovePrerequisite {

	public abstract boolean apply(ChessBoard board, ChessSquare self, ChessSquare target);

}
