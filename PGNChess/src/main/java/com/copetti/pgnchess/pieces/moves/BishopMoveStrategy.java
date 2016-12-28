package com.copetti.pgnchess.pieces.moves;

import java.util.HashSet;
import java.util.Set;

import com.copetti.pgnchess.pieces.moves.prerequisites.CapturePrerequisite;
import com.copetti.pgnchess.pieces.moves.prerequisites.CompositePrerequisite;
import com.copetti.pgnchess.pieces.moves.prerequisites.EmptySquarePrerequisite;


public class BishopMoveStrategy extends ChessMoveStrategy
{

	@Override
	protected Set<MoveVector> doGetMoves()
	{
		MoveVectorBuilder builder = new MoveVectorBuilder();
		builder.setRepeatable();

		CompositePrerequisite composite = CompositePrerequisite
				.newOrPrerequisite();
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
