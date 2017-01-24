package com.copetti.pgn.logic.moves.strategies;

import java.util.HashSet;
import java.util.Set;

import com.copetti.pgn.logic.moves.DiagonalMovement;
import com.copetti.pgn.logic.moves.HVMovement;
import com.copetti.pgn.logic.moves.MoveVector;

public class QueenMoveStrategy extends ChessMoveStrategy {

	private DiagonalMovement diagonal;
	private HVMovement hvMove;

	public QueenMoveStrategy() {
		diagonal = new DiagonalMovement(true);
		hvMove = new HVMovement(true);
	}

	@Override
	protected Set<MoveVector> doGetMoves() {

		Set<MoveVector> moves = new HashSet<>();
		moves.addAll(diagonal.getAvailableMoves());
		moves.addAll(hvMove.getAvailableMoves());

		return moves;
	}

}
