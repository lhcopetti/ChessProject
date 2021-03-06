package com.copetti.pgn.logic.moves.strategies;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import com.copetti.pgn.logic.moves.DiagonalMovement;
import com.copetti.pgn.logic.moves.DisplacementType;
import com.copetti.pgn.logic.moves.HVMovement;
import com.copetti.pgn.logic.moves.MoveContainer;
import com.copetti.pgn.logic.moves.MoveVector;
import com.copetti.pgn.logic.moves.prerequisites.castling.KingSideCastlePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.castling.QueenSideCastlePrerequisite;

public class KingMoveStrategy implements MoveContainer {

	private DiagonalMovement diagonal;
	private HVMovement hvMove;

	public KingMoveStrategy() {
		diagonal = new DiagonalMovement(false);
		hvMove = new HVMovement(false);
	}

	@Override
	public Set<MoveVector> getMoveCollection() {
		Set<MoveVector> moves = new HashSet<>();
		moves.addAll(diagonal.getMoveCollection());
		moves.addAll(hvMove.getMoveCollection());

		MoveVector queenCastle = new MoveVector(new Point(-2, 0), false, DisplacementType.MOVEMENT);
		queenCastle.addPrerequisite(new QueenSideCastlePrerequisite());

		MoveVector kingCastle = new MoveVector(new Point(+2, 0), false, DisplacementType.MOVEMENT);
		kingCastle.addPrerequisite(new KingSideCastlePrerequisite());

		moves.add(queenCastle);
		moves.add(kingCastle);

		return moves;
	}

}
