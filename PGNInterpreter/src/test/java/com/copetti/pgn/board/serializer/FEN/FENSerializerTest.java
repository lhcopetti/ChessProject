package com.copetti.pgn.board.serializer.FEN;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.copetti.pgn.board.CastleInformation;
import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.factory.BoardTestFactory;

public class FENSerializerTest {

	@Test
	public void initialPositionSerializerTest() {

		String initialSetup = "rnbqkbnr" + "pppppppp" + "--------" + "--------" + "--------" + "--------" + "PPPPPPPP"
				+ "RNBQKBNR";
		ChessBoard b = BoardTestFactory.createNew(initialSetup, ChessColor.WHITE, CastleInformation.all());

		String output = new FENSerializer().serialize(b);

		assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", output);
	}

}
