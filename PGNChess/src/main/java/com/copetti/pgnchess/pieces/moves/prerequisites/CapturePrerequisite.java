
package com.copetti.pgnchess.pieces.moves.prerequisites;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.pieces.ChessColor;
import com.copetti.pgnchess.pieces.ChessPiece;

public class CapturePrerequisite extends MovePrerequisite {

	@Override
	public boolean apply(ChessSquare self, ChessBoard board, ChessSquare target) {
		
		ChessPiece targetPiece = board.at(target);
		
		if (null == targetPiece)
			return false;
		
		ChessColor opponent = board.at(self).getColor().opposite();
		
		return targetPiece.getColor() == opponent;
	}

}
