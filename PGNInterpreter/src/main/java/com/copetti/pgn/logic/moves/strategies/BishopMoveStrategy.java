package com.copetti.pgn.logic.moves.strategies;

import java.util.Set;

import com.copetti.pgn.logic.moves.DiagonalMovement;
import com.copetti.pgn.logic.moves.MoveContainer;
import com.copetti.pgn.logic.moves.MoveVector;

public class BishopMoveStrategy implements MoveContainer {

	private DiagonalMovement move;

	public BishopMoveStrategy() {
		move = new DiagonalMovement(true);
	}

	@Override
	public Set<MoveVector> getMoveCollection() {
		return move.getMoveCollection();
	}

}
