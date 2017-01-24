package com.copetti.pgn.logic.moves;

import java.util.HashSet;
import java.util.Set;

import com.copetti.pgn.logic.moves.prerequisites.CapturePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.CompositePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.EmptySquarePrerequisite;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DiagonalMovement implements MoveContainer {

	private @Getter boolean repeatable;

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
		moves.add(builder.build(1, 1));
		moves.add(builder.build(-1, 1));
		moves.add(builder.build(1, -1));
		moves.add(builder.build(-1, -1));

		return moves;
	}

}
