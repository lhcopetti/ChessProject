package com.copetti.pgn.logic.moves;

import java.util.Set;

public class RookMoveStrategy extends ChessMoveStrategy {

	private HVMoveStrategy move;

	public RookMoveStrategy() {
		move = new HVMoveStrategy(true);
	}

	@Override
	protected Set<MoveVector> doGetMoves() {
		return move.getAvailableMoves();
	}

}
