package com.copetti.pgnchess.pieces.moves.prerequisites;

import com.copetti.pgncommon.chess.board.ChessBoard;
import com.copetti.pgncommon.chess.board.ChessSquare;

public abstract class MovePrerequisite {

	public abstract boolean apply(ChessSquare self, ChessBoard board, ChessSquare target);

}
