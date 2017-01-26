
package com.copetti.pgn.logic.moves.prerequisites;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;

public class CapturePrerequisite extends MovePrerequisite {

	@Override
	public boolean apply(ChessBoard board, ChessSquare self, ChessSquare target) {

		ColoredChessPiece targetPiece = board.at(target);

		if (null == targetPiece)
			return false;

		ChessColor opponent = board.at(self).getColor().opposite();

		return targetPiece.getColor() == opponent;
	}
}
