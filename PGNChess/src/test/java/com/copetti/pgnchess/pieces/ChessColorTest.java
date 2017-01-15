package com.copetti.pgnchess.pieces;

import static org.junit.Assert.*;

import org.junit.Test;

import com.copetti.pgncommon.chess.board.ChessColor;


public class ChessColorTest
{

	@Test
	public void TestToStringMethod()
	{
		assertEquals(ChessColor.WHITE.toString(), "White");
		assertEquals(ChessColor.BLACK.toString(), "Black");
	}
	
	@Test
	public void TestOppositeMethod()
	{
		assertEquals(ChessColor.WHITE.opposite(), ChessColor.BLACK);
		assertEquals(ChessColor.BLACK.opposite(), ChessColor.WHITE);
	}	

}
