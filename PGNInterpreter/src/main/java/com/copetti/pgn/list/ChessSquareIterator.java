package com.copetti.pgn.list;

import java.util.Iterator;

import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.tokenizer.tokens.ChessFile;

public class ChessSquareIterator implements Iterable<ChessSquare>, Iterator<ChessSquare> {

	private ChessSquare current;
	private static ChessSquare start = new ChessSquare("a8");
	private static ChessSquare end = new ChessSquare("h1");

	@Override
	public boolean hasNext() {
		return !end.equals(current);
	}

	@Override
	public ChessSquare next() {

		ChessSquare cs = doNext();

		current = cs;
		return cs;
	}

	private ChessSquare doNext() {
		// TODO Auto-generated method stub
		if (null == current)
			return start;

		if (current.getFile() != ChessFile.last())
			return new ChessSquare(current.getFile().next(), current.getRank());

		return new ChessSquare(ChessFile.first(), current.getRank().previous());
	}

	@Override
	public Iterator<ChessSquare> iterator() {
		return this;
	}

}
