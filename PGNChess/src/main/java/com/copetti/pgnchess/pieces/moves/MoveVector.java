package com.copetti.pgnchess.pieces.moves;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.copetti.pgnchess.board.ChessBoard;
import com.copetti.pgnchess.board.ChessSquare;
import com.copetti.pgnchess.pieces.moves.restriction.MoveRestriction;

import lombok.Getter;

public class MoveVector {

	private @Getter boolean isRepetable;
	private List<MoveRestriction> restrictions;
	private Point offset;

	public MoveVector(int x, int y) {
		this(new Point(x, y), false);
	}

	public MoveVector(Point offset, boolean isRepetable) {

		this.isRepetable = isRepetable;
		this.offset = offset;

		restrictions = new ArrayList<>();
	}

	public boolean hasRestriction() {
		return !restrictions.isEmpty();
	}

	public ChessSquare add(ChessSquare chessSquare) {
		return new ChessSquare(chessSquare.getX() + offset.x, chessSquare.getY() + offset.y);
	}

	public void addRestriction(MoveRestriction firstMove) {

		if (firstMove == null)
			return;

		this.restrictions.add(firstMove);
	}

	public Stream<MoveRestriction> getStream() {
		return restrictions.stream();
	}

	public boolean applyRestrictions(ChessSquare self, ChessBoard board) {
		
		if (!hasRestriction())
			return false;

		ChessSquare target = add(self);
		return restrictions.stream().anyMatch(s -> s.apply(self, board, target));
	}
	
}
