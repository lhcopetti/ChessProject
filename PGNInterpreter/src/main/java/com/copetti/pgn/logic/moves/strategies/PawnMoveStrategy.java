package com.copetti.pgn.logic.moves.strategies;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.copetti.pgn.logic.moves.MoveContainer;
import com.copetti.pgn.logic.moves.MoveVector;
import com.copetti.pgn.logic.moves.prerequisites.CapturePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.CompositePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.EnPassantPrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.FirstMovePrerequisite;

public class PawnMoveStrategy implements MoveContainer {

	Set<MoveVector> allMoves;

	public PawnMoveStrategy() {

		Set<MoveVector> moves = new HashSet<>();

		moves.add(new MoveVector(0, 1));

		MoveVector moveTwice = new MoveVector(0, 2);
		moveTwice.addPrerequisite(new FirstMovePrerequisite());
		moves.add(moveTwice);

		CompositePrerequisite composite = CompositePrerequisite.newOrPrerequisite();
		composite.add(new CapturePrerequisite());
		composite.add(new EnPassantPrerequisite());

		MoveVector capture = new MoveVector(1, 1);
		capture.addPrerequisite(composite);
		moves.add(capture);

		MoveVector capture2 = new MoveVector(-1, 1);
		capture2.addPrerequisite(composite);
		moves.add(capture2);

		allMoves = Collections.unmodifiableSet(moves);

	}

	@Override
	public Set<MoveVector> getMoveCollection() {
		return allMoves;
	}

}
