package com.copetti.pgn.logic.moves;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.copetti.pgn.logic.moves.prerequisites.MovePrerequisite;

public class MoveVectorBuilder {

	private List<MovePrerequisite> prerequisites;
	private boolean repeatable;
	private DisplacementType type;

	public MoveVectorBuilder() {
		prerequisites = new ArrayList<>();
		repeatable = false;
		type = DisplacementType.BOTH;
	}

	public void setRepeatable() {
		this.repeatable = true;
	}

	public void setDisplacementType(DisplacementType type) {
		this.type = type;
	}

	public void addPrerequisite(MovePrerequisite composite) {
		prerequisites.add(composite);
	}

	public MoveVector build(int x, int y) {
		MoveVector moveVector = new MoveVector(new Point(x, y), repeatable, type);

		prerequisites //
				.stream() //
				.forEach(prerequisite -> moveVector.addPrerequisite(prerequisite)); //

		return moveVector;
	}
}
