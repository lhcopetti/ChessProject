package com.copetti.pgnchess.pieces.moves.restriction;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;

public abstract class MoveRestriction {

	public abstract boolean apply(ChessSquare self, ChessBoard board, ChessSquare target);

}
