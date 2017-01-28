package com.copetti.pgn.logic.moves.prerequisites;

import java.util.Map;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.logic.ChessMovementResolver;

public class SquareNotAttackedPrerequisite extends MovePrerequisite {

	@Override
	public boolean apply(ChessBoard board, ChessSquare self, ChessSquare target) {

		ChessMovementResolver cmr = new ChessMovementResolver();

		ChessColor myColor = board.at(self).getColor();

		Map<ChessSquare, ColoredChessPiece> opponentPieces = board.getAllPieces(myColor.opposite());

		return opponentPieces //
				.entrySet() //
				.stream() //
				.noneMatch(x -> cmr.getAttackingMoves(x.getKey(), board).contains(target));
	}

}
