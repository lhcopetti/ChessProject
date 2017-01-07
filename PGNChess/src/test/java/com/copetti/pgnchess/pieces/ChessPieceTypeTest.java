package com.copetti.pgnchess.pieces;

import static org.junit.Assert.*;

import org.junit.Test;


public class ChessPieceTypeTest
{

	@Test
	public void testGetPgnFormat()
	{
		assertEquals("p", ChessPieceType.PAWN.getPgnFormat());
		assertEquals("b", ChessPieceType.BISHOP.getPgnFormat());
		assertEquals("n", ChessPieceType.KNIGHT.getPgnFormat());
		assertEquals("r", ChessPieceType.ROOK.getPgnFormat());
		assertEquals("q", ChessPieceType.QUEEN.getPgnFormat());
		assertEquals("k", ChessPieceType.KING.getPgnFormat());
	}

	@Test
	public void testGetScore()
	{
		assertEquals(1, ChessPieceType.PAWN.getScore());
		assertEquals(3, ChessPieceType.BISHOP.getScore());
		assertEquals(3, ChessPieceType.KNIGHT.getScore());
		assertEquals(5, ChessPieceType.ROOK.getScore());
		assertEquals(9, ChessPieceType.QUEEN.getScore());
		assertEquals(-1, ChessPieceType.KING.getScore());
	}

}
