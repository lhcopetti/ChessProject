package com.copetti.pgn.logic.moves;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.logic.moves.prerequisites.MovePrerequisite;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoveVector {

	private Point offset;
	private @Getter boolean isRepetable;
	private List<MovePrerequisite> prerequisites;

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
		return add(chessSquare, 1);
	}

	public ChessSquare add(ChessSquare chessSquare, int i) {
		return new ChessSquare(chessSquare.getX() + offset.x * i, chessSquare.getY() + offset.y * i);
	}

	public void addPrerequisite(MovePrerequisite firstMove) {

		if (firstMove == null)
			return;

		this.prerequisites.add(firstMove);
	}

	public Stream<MovePrerequisite> getStream() {
		return prerequisites.stream();
	}

	public boolean checkPrerequisite(ChessSquare self, ChessBoard board, ChessSquare target) {

		if (!hasPrerequisite())
			return true;

		return prerequisites.stream().allMatch(s -> s.apply(board, self, target));
	}

	public void setRepeatable() {
		this.isRepetable = true;
	}

	@Override
	public String toString() {
		return "(" + offset.getX() + ", " + offset.getY() + ")";
	}

	public MoveVector flip() {
		return new MoveVector(new Point(-offset.x, -offset.y), isRepetable, prerequisites);
	}

}
