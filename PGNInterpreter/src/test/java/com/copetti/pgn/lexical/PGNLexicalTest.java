package com.copetti.pgn.lexical;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.PGNTokenizer;

public class PGNLexicalTest {

	@Test
	public void testEmptyTokens() {

		assertFalse(new PGNLexical().execute(new ArrayList<>()));
	}

	@Test
	public void testKnightMoveInvalid() {

		PGNTokenizer tokenizer = new PGNTokenizer();
		List<PGNToken> knightMove = tokenizer.tokenize("Ncc6");
		assertFalse(new PGNLexical().execute(knightMove));
	}

	@Test
	public void testKnightMove() {

		PGNTokenizer tokenizer = new PGNTokenizer();
		List<PGNToken> knightMove = tokenizer.tokenize("Nc6");
		assertTrue(new PGNLexical().execute(knightMove));
	}

	@Test
	public void testBishopMove() {

		PGNTokenizer tokenizer = new PGNTokenizer();
		List<PGNToken> bishopMove = tokenizer.tokenize("Ba1");
		assertTrue(new PGNLexical().execute(bishopMove));
	}

	@Test
	public void testPawnMove() {

		PGNTokenizer tokenizer = new PGNTokenizer();
		List<PGNToken> bishopMove = tokenizer.tokenize("e4");
		assertTrue(new PGNLexical().execute(bishopMove));
	}

}
