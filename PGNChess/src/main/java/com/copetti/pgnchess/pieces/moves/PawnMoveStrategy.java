package com.copetti.pgnchess.pieces.moves;

import java.util.Set;

import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.pieces.moves.restriction.CaptureRestriction;
import com.copetti.pgnchess.pieces.moves.restriction.FirstMoveRestriction;

public class PawnMoveStrategy extends ChessMoveStrategy {

	public PawnMoveStrategy() {

		moves.add(new MoveVector(0, 1));
		
		MoveVector moveTwice = new MoveVector(0, 2);
		moveTwice.addRestriction(new FirstMoveRestriction());
		moves.add(moveTwice);
		
		MoveVector capture = new MoveVector(1, 1);
		capture.addRestriction(new CaptureRestriction());
		moves.add(capture);
		
		MoveVector capture2 = new MoveVector(-1, 1);
		capture2.addRestriction(new CaptureRestriction());
		moves.add(capture2);
	}

	@Override
	public Set<ChessSquare> doGetMoves() {
		return null;
	}

}
