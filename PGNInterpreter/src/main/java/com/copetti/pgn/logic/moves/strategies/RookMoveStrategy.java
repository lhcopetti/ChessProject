package com.copetti.pgn.logic.moves.strategies;

import java.util.Set;

import com.copetti.pgn.logic.moves.HVMovement;
import com.copetti.pgn.logic.moves.MoveContainer;
import com.copetti.pgn.logic.moves.MoveVector;

public class RookMoveStrategy implements MoveContainer {

	private HVMovement move;

	public RookMoveStrategy() {
		move = new HVMovement(true);
	}

	@Override
	public Set<MoveVector> getMoveCollection() {
		return move.getMoveCollection();
	}

}
