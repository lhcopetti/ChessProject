package com.copetti.pgnchess.pieces.moves;

import java.util.Set;

import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.pieces.moves.prerequisites.CapturePrerequisite;
import com.copetti.pgnchess.pieces.moves.prerequisites.FirstMovePrerequisite;

public class PawnMoveStrategy extends ChessMoveStrategy {

	public PawnMoveStrategy() {

		moves.add(new MoveVector(0, 1));
		
		MoveVector moveTwice = new MoveVector(0, 2);
		moveTwice.addRestriction(new FirstMovePrerequisite());
		moves.add(moveTwice);
		
		MoveVector capture = new MoveVector(1, 1);
		capture.addRestriction(new CapturePrerequisite());
		moves.add(capture);
		
		MoveVector capture2 = new MoveVector(-1, 1);
		capture2.addRestriction(new CapturePrerequisite());
		moves.add(capture2);
	}

	@Override
	public Set<ChessSquare> doGetMoves() {
		return null;
	}

}
