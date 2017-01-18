package com.copetti.pgn.board.serializer.FEN;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.copetti.pgn.board.CastleInformation;
import com.copetti.pgn.board.serializer.ChessBoardSerializer;

public class CastleInformationSerializerTest {

	private FENSerializer ser;

	@Before
	public void before() {
		ser = new FENSerializer();
	}

	@Test
	public void testAllCanCastle() {
		assertEquals("KQkq", ser.serialize(new CastleInformation(true, true, true, true)));
	}

	@Test
	public void onlyWhiteCanCastle() {
		assertEquals("KQ", ser.serialize(new CastleInformation(true, true, false, false)));
	}

	@Test
	public void onlyBlackCanCastle() {
		assertEquals("kq", ser.serialize(new CastleInformation(false, false, true, true)));
	}

	@Test
	public void noOneCanCastle() {
		assertEquals("-", ser.serialize(new CastleInformation(false, false, false, false)));
	}
}
