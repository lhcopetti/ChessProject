package com.copetti.pgn.logic.moves.strategies;

import java.util.Set;

import com.copetti.pgn.logic.moves.HVMovement;
import com.copetti.pgn.logic.moves.MoveVector;

public class RookMoveStrategy extends ChessMoveStrategy {

	private HVMovement move;

	public RookMoveStrategy() {
		move = new HVMovement(true);
	}

	@Override
	protected Set<MoveVector> doGetMoves() {
		return move.getAvailableMoves();
	}

}
