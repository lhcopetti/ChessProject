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
	public void testSimpleMoves() {

		PGNTokenizer tokenizer = new PGNTokenizer();

		List<PGNToken> knightMove = tokenizer.tokenize("Nc6");
		assertTrue(new PGNLexical().execute(knightMove));

		// List<PGNToken> knightMove = tokenizer.tokenize("");
		//
		// List<PGNToken> kingMove = tokenizer.tokenize("");

//		List<PGNToken> pawnMove = tokenizer.tokenize("");
//		assertTrue(new PGNLexical().execute(pawnMove));
	}

}
