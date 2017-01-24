package com.copetti.pgn.logic.moves;

import java.util.Set;

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
