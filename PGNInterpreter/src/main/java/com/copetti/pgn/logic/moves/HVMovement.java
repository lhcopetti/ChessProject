package com.copetti.pgn.logic.moves;

import java.util.HashSet;
import java.util.Set;

import com.copetti.pgn.logic.moves.prerequisites.CapturePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.CompositePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.EmptySquarePrerequisite;

import lombok.Getter;

public class HVMovement implements MoveContainer {

	private @Getter boolean repeatable;

	public HVMovement(boolean repeatable) {
		this.repeatable = repeatable;
	}

	@Override
	public Set<MoveVector> getMoveCollection() {

		MoveVectorBuilder builder = new MoveVectorBuilder();

		if (repeatable)
			builder.setRepeatable();

		CompositePrerequisite composite = CompositePrerequisite.newOrPrerequisite();
		composite.add(new EmptySquarePrerequisite());
		composite.add(new CapturePrerequisite());

		builder.addPrerequisite(composite);

		Set<MoveVector> moves = new HashSet<>();
		moves.add(builder.build(0, 1));
		moves.add(builder.build(1, 0));
		moves.add(builder.build(0, -1));
		moves.add(builder.build(-1, 0));

		return moves;
	}

}
