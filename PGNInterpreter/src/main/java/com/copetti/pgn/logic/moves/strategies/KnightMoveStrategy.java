package com.copetti.pgn.logic.moves.strategies;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.copetti.pgn.logic.moves.MoveContainer;
import com.copetti.pgn.logic.moves.MoveVector;
import com.copetti.pgn.logic.moves.MoveVectorBuilder;
import com.copetti.pgn.logic.moves.prerequisites.CapturePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.CompositePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.EmptySquarePrerequisite;

public class KnightMoveStrategy implements MoveContainer {

	private Set<MoveVector> moves;

	public KnightMoveStrategy() {
		MoveVectorBuilder builder = new MoveVectorBuilder();

		CompositePrerequisite composite = CompositePrerequisite.newOrPrerequisite();
		composite.add(new EmptySquarePrerequisite());
		composite.add(new CapturePrerequisite());
		builder.addPrerequisite(composite);

		Set<MoveVector> moves = new HashSet<>();
		moves.add(builder.build(-1, 2));
		moves.add(builder.build(1, 2));

		moves.add(builder.build(-1, -2));
		moves.add(builder.build(1, -2));

		moves.add(builder.build(-2, 1));
		moves.add(builder.build(-2, -1));

		moves.add(builder.build(2, 1));
		moves.add(builder.build(2, -1));

		this.moves = Collections.unmodifiableSet(moves);
	}

	@Override
	public Set<MoveVector> getMoveCollection() {
		return moves;
	}

}
