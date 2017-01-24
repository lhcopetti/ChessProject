package com.copetti.pgn.logic.moves.strategies;

import java.util.Set;

import com.copetti.pgn.logic.moves.DiagonalMovement;
import com.copetti.pgn.logic.moves.MoveVector;

public class BishopMoveStrategy extends ChessMoveStrategy {

	private DiagonalMovement move;

	public BishopMoveStrategy() {
		move = new DiagonalMovement(true);
	}

	@Override
	protected Set<MoveVector> doGetMoves() {
		return move.getAvailableMoves();

	}

}
