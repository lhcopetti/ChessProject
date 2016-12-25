package com.copetti.pgnchess.pieces.moves;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.pieces.moves.prerequisites.MovePrerequisite;

import lombok.Getter;

public class MoveVector {

	private @Getter boolean isRepetable;
	private List<MovePrerequisite> prerequisites;
	private Point offset;

	public MoveVector(int x, int y) {
		this(new Point(x, y), false);
	}

	public MoveVector(Point offset, boolean isRepetable) {

		this.isRepetable = isRepetable;
		this.offset = offset;

		prerequisites = new ArrayList<>();
	}

	public boolean hasPrerequisite() {
		return !prerequisites.isEmpty();
	}

	public ChessSquare add(ChessSquare chessSquare) {
		return new ChessSquare(chessSquare.getX() + offset.x, chessSquare.getY() + offset.y);
	}

	public void addRestriction(MovePrerequisite firstMove) {

		if (firstMove == null)
			return;

		this.prerequisites.add(firstMove);
	}

	public Stream<MovePrerequisite> getStream() {
		return prerequisites.stream();
	}

	public boolean checkPrerequisite(ChessSquare self, ChessBoard board) {
		
		if (!hasPrerequisite())
			return true;

		ChessSquare target = add(self);
		return prerequisites.stream().allMatch(s -> s.apply(self, board, target));
	}
	
}
