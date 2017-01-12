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
	public void testKnightMoveExecuter() throws Exception {

		PGNTokenizer tokenizer = new PGNTokenizer();
		List<PGNToken> knightMove = tokenizer.tokenize("Nc6");
		new PGNLexical().executer(knightMove);
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

	@Test
	public void testKnightCapture() {

		PGNTokenizer tokenizer = new PGNTokenizer();
		List<PGNToken> bishopMove = tokenizer.tokenize("Nxf6");
		assertTrue(new PGNLexical().execute(bishopMove));
	}

	@Test
	public void testPawnCapture() {

		PGNTokenizer tokenizer = new PGNTokenizer();
		assertTrue(new PGNLexical().execute(tokenizer.tokenize("exf6")));
	}

	@Test
	public void testRookMoveWithAmbiguityInRank() {

		PGNTokenizer tokenizer = new PGNTokenizer();
		assertTrue(new PGNLexical().execute(tokenizer.tokenize("Rah7")));
	}

	@Test
	public void testRookMoveWithAmbiguityInFile() {

		PGNTokenizer tokenizer = new PGNTokenizer();
		assertTrue(new PGNLexical().execute(tokenizer.tokenize("R8a5")));
	}

	@Test
	public void testQueenMoveWithAmbiguityInFileAndRank() {

		/*
		 * http://chess.stackexchange.com/questions/1817/how-are-pgn-ambiguities
		 * -handled
		 */
		PGNTokenizer tokenizer = new PGNTokenizer();
		assertTrue(new PGNLexical().execute(tokenizer.tokenize("Qh8g7")));
	}

}
