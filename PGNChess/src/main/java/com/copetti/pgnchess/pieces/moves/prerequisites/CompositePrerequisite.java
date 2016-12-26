package com.copetti.pgnchess.pieces.moves.prerequisites;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;


public class CompositePrerequisite extends MovePrerequisite
{

	private static final BinaryOperator<Boolean> andReduce = new BinaryOperator<Boolean>()
	{

		@Override
		public Boolean apply(Boolean t, Boolean u)
		{
			return t && u;
		}
	};

	private static final BinaryOperator<Boolean> orReduce = new BinaryOperator<Boolean>()
	{

		@Override
		public Boolean apply(Boolean t, Boolean u)
		{
			return t || u;
		}
	};

	private List<MovePrerequisite> prerequisites;

	private BinaryOperator<Boolean> reduceOperator;
	private Boolean identity;

	private CompositePrerequisite(BinaryOperator<Boolean> reduceOperator,
			boolean identity)
	{
		this.identity = identity;
		this.reduceOperator = reduceOperator;
		prerequisites = new ArrayList<>();
	}

	public static CompositePrerequisite newAndPrerequisite()
	{
		return new CompositePrerequisite(andReduce, true);
	}

	public static CompositePrerequisite newOrPrerequisite()
	{
		return new CompositePrerequisite(orReduce, false);
	}

	public void add(MovePrerequisite prerequisite)
	{
		prerequisites.add(prerequisite);
	}

	@Override
	public boolean apply(ChessSquare self, ChessBoard board, ChessSquare target)
	{
		return prerequisites //
				.stream() //
				.map(x -> x.apply(self, board, target)) //
				.reduce(identity, reduceOperator); //
	}

}
