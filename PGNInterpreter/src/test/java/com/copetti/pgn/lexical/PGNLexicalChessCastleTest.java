package com.copetti.pgn.lexical;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.copetti.pgn.lexical.PGNLexical;
import com.copetti.pgn.lexical.state.CastleLongState;
import com.copetti.pgn.lexical.state.CastleShortState;
import com.copetti.pgn.lexical.state.LexicalState;

public class PGNLexicalChessCastleTest {

	@Test
	public void castleTestInvalidEnd() {
		List<LexicalState> lex = new PGNLexical().execute("O-O-O-");
		assertTrue(lex == null);
	}

	@Test
	public void castleTestInvalidBeginning() {
		List<LexicalState> lex = new PGNLexical().execute("-O-O");
		assertTrue(null == lex);
	}

	@Test
	public void shortCastleTest() {
		List<LexicalState> lex = new PGNLexical().execute("O-O");
		assertEquals(new CastleShortState(), lex.get(0));
	}

	@Test
	public void longCastleTest() {
		List<LexicalState> lex = new PGNLexical().execute("O-O-O");
		assertEquals(new CastleLongState(), lex.get(0));
	}

	@Test
	public void longCastleTestWithCheck() {
		List<LexicalState> lex = new PGNLexical().execute("O-O-O+");
		assertEquals(new CastleLongState(), lex.get(0));
	}

	@Test
	public void longCastleTestWithMate() {
		List<LexicalState> lex = new PGNLexical().execute("O-O-O#");
		assertEquals(new CastleLongState(), lex.get(0));
	}
}
