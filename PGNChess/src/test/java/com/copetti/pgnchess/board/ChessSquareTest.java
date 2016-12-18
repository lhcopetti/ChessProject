package com.copetti.pgnchess.board;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ChessSquareTest {

	private ChessSquare _a1;
	private ChessSquare _b2;
	private ChessSquare _c7;
	private ChessSquare _h8;
	private ChessSquare _d4;

	@Before
	public void setUp() {
		_a1 = new ChessSquare(0, 0);
		_b2 = new ChessSquare(1, 1);
		_c7 = new ChessSquare(2, 6);
		_h8 = new ChessSquare(7, 7);
		_d4 = new ChessSquare(3, 3);
	}

	@Test
	public void testCoordinateToString() {
		assertEquals(_a1.toString(), "a1");
		assertEquals(_b2.toString(), "b2");
		assertEquals(_c7.toString(), "c7");
		assertEquals(_h8.toString(), "h8");
		assertEquals(_d4.toString(), "d4");
	}

	@Test
	public void testFile() {
		assertEquals(_a1.getFile(), ChessFile.A);
		assertEquals(_b2.getFile(), ChessFile.B);
		assertEquals(_c7.getFile(), ChessFile.C);
		assertEquals(_h8.getFile(), ChessFile.H);
		assertEquals(_d4.getFile(), ChessFile.D);
	}

	@Test
	public void testRank() {
		assertEquals(_a1.getRank().getValue(), 1);
		assertEquals(_b2.getRank().getValue(), 2);
		assertEquals(_c7.getRank().getValue(), 7);
		assertEquals(_h8.getRank().getValue(), 8);
		assertEquals(_d4.getRank().getValue(), 4);
	}

	@Test
	public void testEquals() {
		assertEquals(new ChessSquare(0, 0), new ChessSquare(0, 0));
		assertEquals(new ChessSquare(7, 7), new ChessSquare(7, 7));

		assertEquals(new ChessSquare(3, 7).hashCode(), new ChessSquare(3, 7).hashCode());
		assertEquals(new ChessSquare(ChessFile.B, new ChessRank(8)), new ChessSquare(1, 7));
		assertEquals(new ChessSquare(ChessFile.F, new ChessRank(4)), new ChessSquare(5, 3));
	}

	@Test
	public void testStringArgument() {

		assertEquals(new ChessSquare("e4"), new ChessSquare(ChessFile.E, new ChessRank(4)));
		assertEquals(new ChessSquare("h8"), new ChessSquare(ChessFile.H, new ChessRank(8)));

		assertEquals(new ChessSquare("b3"), new ChessSquare(1, 2));
		assertEquals(new ChessSquare("f6"), new ChessSquare(5, 5));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStringArgumentInvalid() {

		assertEquals(new ChessSquare("f6x"), new ChessSquare(5, 5));
	}

}
