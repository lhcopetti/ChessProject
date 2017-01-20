package com.copetti.pgn.board.factory;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;

public class VanillaChessFactoryTest {

	@Test
	public void testNewBoardFactory() {
		Map<ChessSquare, ColoredChessPiece> map = new VanillaChessFactory().newBoard();

		String initialSetup = "rnbqkbnr" + "pppppppp" + "--------" + "--------" + "--------" + "--------" + "PPPPPPPP" + "RNBQKBNR";
		Map<ChessSquare, ColoredChessPiece> mapVisaul = new VisualChessBoardFactory().newBoard(initialSetup);

		assertEquals(map, mapVisaul);
	}
}
