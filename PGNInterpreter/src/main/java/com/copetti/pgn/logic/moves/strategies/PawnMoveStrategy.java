package com.copetti.pgn.logic.moves.strategies;

import java.awt.Point;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.copetti.pgn.logic.moves.DisplacementType;
import com.copetti.pgn.logic.moves.MoveContainer;
import com.copetti.pgn.logic.moves.MoveVector;
import com.copetti.pgn.logic.moves.MoveVectorBuilder;
import com.copetti.pgn.logic.moves.prerequisites.CapturePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.CompositePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.EnPassantPrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.FirstMovePrerequisite;

public class PawnMoveStrategy implements MoveContainer {

	Set<MoveVector> allMoves;

	public PawnMoveStrategy() {

		Set<MoveVector> moves = new HashSet<>();

		moves.add(new MoveVector(new Point(0, 1), false, DisplacementType.MOVEMENT));

		MoveVector moveTwice = new MoveVector(new Point(0, 2), false, DisplacementType.MOVEMENT);
		moveTwice.addPrerequisite(new FirstMovePrerequisite());
		moves.add(moveTwice);

		CompositePrerequisite composite = CompositePrerequisite.newOrPrerequisite();
		composite.add(new CapturePrerequisite());
		composite.add(new EnPassantPrerequisite());

		MoveVectorBuilder builder = new MoveVectorBuilder();

		builder.addPrerequisite(composite);
		builder.setDisplacementType(DisplacementType.ATTACKING);

		moves.add(builder.build(1, 1));
		moves.add(builder.build(-1, 1));
		allMoves = Collections.unmodifiableSet(moves);
	}

	@Override
	public Set<MoveVector> getMoveCollection() {
		return allMoves;
	}

}
