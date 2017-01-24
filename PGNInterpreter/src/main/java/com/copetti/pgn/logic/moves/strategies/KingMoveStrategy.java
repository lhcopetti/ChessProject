package com.copetti.pgn.logic.moves.strategies;

import java.util.HashSet;
import java.util.Set;

import com.copetti.pgn.logic.moves.DiagonalMovement;
import com.copetti.pgn.logic.moves.HVMovement;
import com.copetti.pgn.logic.moves.MoveVector;

public class KingMoveStrategy extends ChessMoveStrategy {

	private DiagonalMovement diagonal;
	private HVMovement hvMove;

	public KingMoveStrategy() {
		diagonal = new DiagonalMovement(false);
		hvMove = new HVMovement(false);
	}

	@Override
	protected Set<MoveVector> doGetMoves() {

		Set<MoveVector> moves = new HashSet<>();
		moves.addAll(diagonal.getAvailableMoves());
		moves.addAll(hvMove.getAvailableMoves());
		return moves;
	}

}
