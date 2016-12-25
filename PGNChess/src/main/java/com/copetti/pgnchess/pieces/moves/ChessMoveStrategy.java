package com.copetti.pgnchess.pieces.moves;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.math.UnitVectorInterpoler;
import com.copetti.pgnchess.math.Vector;
import com.copetti.pgnchess.pieces.moves.prerequisites.CapturePrerequisite;
import com.copetti.pgnchess.pieces.moves.prerequisites.EmptySquarePrerequisite;
import com.copetti.pgnchess.pieces.moves.prerequisites.MovePrerequisite;


public abstract class ChessMoveStrategy
{

	protected List<MoveVector> moves;

	EmptySquarePrerequisite emptyPrerequisite;
	CapturePrerequisite capturePrerequisite;

	public ChessMoveStrategy()
	{
		moves = new ArrayList<>();
		emptyPrerequisite = new EmptySquarePrerequisite();
		capturePrerequisite = new CapturePrerequisite();
	}

	public Set<ChessSquare> getMoves(ChessSquare self, ChessBoard board)
	{

		Set<ChessSquare> availableMoves = new HashSet<>();

		for( MoveVector m : moves )
		{

			if (computeMoveValidity(m, self, board))
				availableMoves.add(m.add(self));

		}

		return availableMoves;
	}

	private boolean computeMoveValidity(MoveVector moveVector, ChessSquare self,
			ChessBoard board)
	{
		ChessSquare target = moveVector.add(self);

		if (!computeIntermediateSquares(self, board, target)) return false;

		if (!emptyPrerequisite.apply(self, board, target)
				&& !capturePrerequisite.apply(self, board, target))
			return false;

		if (!moveVector.checkPrerequisite(self, board)) return false;

		return true;
	}

	private boolean computeIntermediateSquares(ChessSquare self,
			ChessBoard board, ChessSquare target)
	{
		Vector vOrigin = new Vector(self.getX(), self.getY());
		Vector vTarget = new Vector(target.getX(), target.getY());

		UnitVectorInterpoler interpoler = new UnitVectorInterpoler();
		List<Vector> intermediatePoints = interpoler
				.getIntermediatePoints(vOrigin, vTarget);

		if (null == intermediatePoints) return true;

		if (intermediatePoints.isEmpty()) return true;

		return intermediatePoints.stream()
				.map(cs -> new ChessSquare((int) cs.getX(), (int) cs.getY()))
				.allMatch(x -> emptyPrerequisite.apply(self, board, x));
	}

	protected abstract Set<ChessSquare> doGetMoves();

}
