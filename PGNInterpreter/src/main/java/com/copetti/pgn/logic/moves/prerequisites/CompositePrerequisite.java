package com.copetti.pgn.logic.moves.prerequisites;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;

public class CompositePrerequisite extends MovePrerequisite {

	private List<MovePrerequisite> prerequisites;
	private BinaryOperator<Boolean> reduceOperator;
	private Boolean identity;

	private CompositePrerequisite(BinaryOperator<Boolean> reduceOperator, boolean identity) {
		this.identity = identity;
		this.reduceOperator = reduceOperator;
		prerequisites = new ArrayList<>();
	}

	public static CompositePrerequisite newAndPrerequisite() {
		return new CompositePrerequisite((x, y) -> x && y, true);
	}

	public static CompositePrerequisite newOrPrerequisite() {
		return new CompositePrerequisite((x, y) -> x || y, false);
	}

	public void add(MovePrerequisite prerequisite) {
		prerequisites.add(prerequisite);
	}

	@Override
	public boolean apply(ChessBoard board, ChessSquare self, ChessSquare target) {

		return prerequisites //
				.stream() //
				.map(x -> x.apply(board, self, target)) //
				.reduce(identity, reduceOperator); //
	}

}
