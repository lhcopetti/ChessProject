package com.copetti.pgn.logic.moves;

import java.util.ArrayList;
import java.util.List;

import com.copetti.pgn.logic.moves.prerequisites.CompositePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.MovePrerequisite;

public class MoveVectorBuilder {

	private List<MovePrerequisite> prerequisites;
	private boolean repeatable;

	public MoveVectorBuilder() {
		prerequisites = new ArrayList<>();
		repeatable = false;
	}

	public void setRepeatable() {
		this.repeatable = true;
	}

	public void addPrerequisite(CompositePrerequisite composite) {
		prerequisites.add(composite);
	}

	public MoveVector build(int x, int y) {
		MoveVector moveVector = new MoveVector(x, y);

		if (repeatable)
			moveVector.setRepeatable();

		prerequisites //
				.stream() //
				.forEach(prerequisite -> moveVector.addPrerequisite(prerequisite)); //

		return moveVector;
	}
}
