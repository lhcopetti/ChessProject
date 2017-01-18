package com.copetti.pgn.board.serializer.FEN;

import static org.junit.Assert.*;

import org.junit.Test;

public class FENSerializerBoardTest {

	@Test
	public void initialSetup() {
		/* Initial chess board setup */
		// rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
		fail("Not yet implemented");
	}
	
	@Test
	public void initialSetupAfterE4() {
		/* After e4 */
		// rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1
	}
	
	@Test
	public void imCrushingHimResignation() {
		fail("Not yet implemented");
		/* https://en.lichess.org/study/QuFstlOs */
		// 1Rr5/3b4/Q1NPpk2/3p1p1p/3P1Pp1/P5P1/1P6/2K5 b - - 2 51
	}
	
	@Test
	public void supriseMateTest() {
		/* Surprise Mate */
		/* https://en.lichess.org/y6pUC1CK/white#47 */
		// 1k1r2BB/ppp1Q3/5p2/6p1/8/3P2P1/P1P2q1P/1N2R2K b - - 0 24
	}
	
	@Test
	public void RookStalemate() {
		/* Incredible stalemate https://en.lichess.org/study/QuFstlOs */
		// k7/P6R/1P6/8/7P/8/2P1K3/8 w - - 1 41
		fail("Not yet implemented");
	}

}
