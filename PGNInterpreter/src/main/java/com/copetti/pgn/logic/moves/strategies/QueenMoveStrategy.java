package com.copetti.pgn.logic.moves.strategies;

import java.util.HashSet;
import java.util.Set;

import com.copetti.pgn.logic.moves.DiagonalMovement;
import com.copetti.pgn.logic.moves.HVMovement;
import com.copetti.pgn.logic.moves.MoveContainer;
import com.copetti.pgn.logic.moves.MoveVector;

public class QueenMoveStrategy implements MoveContainer {

	private DiagonalMovement diagonal;
	private HVMovement hvMove;

	public QueenMoveStrategy() {
		diagonal = new DiagonalMovement(true);
		hvMove = new HVMovement(true);
	}

	@Override
	public Set<MoveVector> getMoveCollection() {

		Set<MoveVector> moves = new HashSet<>();
		moves.addAll(diagonal.getMoveCollection());
		moves.addAll(hvMove.getMoveCollection());

		return moves;
	}

}
