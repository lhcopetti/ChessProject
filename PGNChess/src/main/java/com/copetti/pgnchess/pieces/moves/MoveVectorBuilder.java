package com.copetti.pgnchess.pieces.moves;

import java.util.ArrayList;
import java.util.List;

import com.copetti.pgnchess.pieces.moves.prerequisites.CompositePrerequisite;
import com.copetti.pgnchess.pieces.moves.prerequisites.MovePrerequisite;


public class MoveVectorBuilder
{

	private List<MovePrerequisite> prerequisites;

	public MoveVectorBuilder()
	{
		prerequisites = new ArrayList<>();
	}

	public void setRepeatable()
	{
	}

	public void addPrerequisite(CompositePrerequisite composite)
	{
		prerequisites.add(composite);
	}

	public MoveVector build(int x, int y)
	{
		MoveVector moveVector = new MoveVector(x, y);
		moveVector.setRepeatable();
		prerequisites //
				.stream() //
				.forEach(prerequisite -> moveVector
						.addPrerequisite(prerequisite)); //

		return moveVector;
	}
}
