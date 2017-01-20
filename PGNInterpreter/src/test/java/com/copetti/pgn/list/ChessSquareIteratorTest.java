package com.copetti.pgn.list;

import static org.junit.Assert.*;

import org.junit.Test;

import com.copetti.pgn.board.ChessSquare;

public class ChessSquareIteratorTest {

	@Test
	public void testSizeIterator() {

		ChessSquareIterator c = new ChessSquareIterator();

		int counter = 0;

		while (c.hasNext()) {
			counter++;
			c.next();
		}

		assertEquals(64, counter);
	}

	@Test
	public void testRandom() {

		ChessSquareIterator c = new ChessSquareIterator();

		assertEquals(new ChessSquare("a8"), c.next());
		skip(c, 4);
		assertEquals(new ChessSquare("f8"), c.next());
		skip(c, 4);
		assertEquals(new ChessSquare("c7"), c.next());

	}

	private void skip(ChessSquareIterator c, int i) {

		while (i-- > 0)
			c.next();
	}

}
