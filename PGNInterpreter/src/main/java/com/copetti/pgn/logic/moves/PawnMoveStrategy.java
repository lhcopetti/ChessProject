package com.copetti.pgn.logic.moves;

import java.util.HashSet;
import java.util.Set;

import com.copetti.pgn.logic.moves.prerequisites.CapturePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.FirstMovePrerequisite;

public class PawnMoveStrategy extends ChessMoveStrategy {

	public PawnMoveStrategy() {

	}

	@Override
	public Set<MoveVector> doGetMoves() {

		Set<MoveVector> moves = new HashSet<>();

		moves.add(new MoveVector(0, 1));

		MoveVector moveTwice = new MoveVector(0, 2);
		moveTwice.addPrerequisite(new FirstMovePrerequisite());
		moves.add(moveTwice);

		MoveVector capture = new MoveVector(1, 1);
		capture.addPrerequisite(new CapturePrerequisite());
		moves.add(capture);

		MoveVector capture2 = new MoveVector(-1, 1);
		capture2.addPrerequisite(new CapturePrerequisite());
		moves.add(capture2);

		return moves;
	}

}
