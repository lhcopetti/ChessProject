package com.copetti.pgnchess.pieces.moves;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.pieces.moves.restriction.EmptySquareRestriction;
import com.copetti.pgnchess.pieces.moves.restriction.MoveRestriction;

public abstract class ChessMoveStrategy {

	protected List<MoveVector> moves;

	MoveRestriction moveRestriction;

	public ChessMoveStrategy() {
		moves = new ArrayList<>();
		moveRestriction = new EmptySquareRestriction();
	}

	public Set<ChessSquare> getMoves(ChessSquare self, ChessBoard board) {

		Set<ChessSquare> availableMoves = new HashSet<>();

		for (MoveVector m : moves) {

			ChessSquare target = m.add(self);

			if (moveRestriction.apply(self, board, target))
				continue;

			if (m.applyRestrictions(self, board))
				continue;

			availableMoves.add(m.add(self));
		}

		return availableMoves;
	}

	protected abstract Set<ChessSquare> doGetMoves();

}
