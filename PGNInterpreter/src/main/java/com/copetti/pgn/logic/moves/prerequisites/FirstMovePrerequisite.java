package com.copetti.pgn.logic.moves.prerequisites;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class FirstMovePrerequisite extends MovePrerequisite {

	@Override
	public boolean apply(ChessBoard board, ChessSquare self, ChessSquare target) {

		ColoredChessPiece piece = board.at(self);

		if (piece.getPiece() != ChessPiece.PAWN)
			return false;

		if (piece.getColor() == ChessColor.WHITE && self.getRank().equals(ChessRank.of("2").get()))
			return true;

		if (piece.getColor() == ChessColor.BLACK && self.getRank().equals(ChessRank.of("7").get()))
			return true;

		return false;
	}

}
