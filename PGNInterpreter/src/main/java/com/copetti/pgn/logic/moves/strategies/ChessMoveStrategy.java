package com.copetti.pgn.logic.moves.strategies;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.logic.math.UnitVectorInterpoler;
import com.copetti.pgn.logic.math.Vector;
import com.copetti.pgn.logic.moves.MoveVector;
import com.copetti.pgn.logic.moves.prerequisites.CapturePrerequisite;
import com.copetti.pgn.logic.moves.prerequisites.EmptySquarePrerequisite;

public abstract class ChessMoveStrategy {

	EmptySquarePrerequisite emptyPrerequisite;
	CapturePrerequisite capturePrerequisite;

	public ChessMoveStrategy() {
		emptyPrerequisite = new EmptySquarePrerequisite();
		capturePrerequisite = new CapturePrerequisite();
	}

	public Set<ChessSquare> getMoves(ChessSquare self, ChessBoard board) {

		Set<ChessSquare> availableMoves = new HashSet<>();

		for (MoveVector m : doGetMoves()) {
			int repetition = 1;

			while (true) {

				if (!targetSquareIsWithinBounds(m, self, repetition))
					break;

				ChessSquare target = m.add(self, repetition++);

				if (!computeMoveValidity(self, board, target))
					break;

				if (!m.checkPrerequisite(self, board, target))
					break;

				availableMoves.add(target);

				if (!m.isRepetable())
					break;
			}
		}

		return availableMoves;
	}

	private boolean targetSquareIsWithinBounds(MoveVector m, ChessSquare self, int repetition) {
		try {
			m.add(self, repetition);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	private boolean computeMoveValidity(ChessSquare self, ChessBoard board, ChessSquare target) {
		if (!computeIntermediateSquares(self, board, target))
			return false;

		if (!emptyPrerequisite.apply(board, self, target) && !capturePrerequisite.apply(board, self, target))
			return false;

		return true;
	}

	private boolean computeIntermediateSquares(ChessSquare self, ChessBoard board, ChessSquare target) {
		Vector vOrigin = new Vector(self.getX(), self.getY());
		Vector vTarget = new Vector(target.getX(), target.getY());

		UnitVectorInterpoler interpoler = new UnitVectorInterpoler();
		List<Vector> intermediatePoints = interpoler.getIntermediatePoints(vOrigin, vTarget);

		if (null == intermediatePoints)
			return true;

		if (intermediatePoints.isEmpty())
			return true;

		return intermediatePoints.stream().map(cs -> new ChessSquare((int) cs.getX(), (int) cs.getY()))
				.allMatch(x -> emptyPrerequisite.apply(board, self, x));
	}

	protected abstract Set<MoveVector> doGetMoves();

}
