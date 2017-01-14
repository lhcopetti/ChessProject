package com.copetti.pgn.tokenizer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PGNTokenizerTest {

	private PGNTokenizer tok;

	@Before
	public void construct() {
		tok = new PGNTokenizer();
	}

	@Test(expected = NullPointerException.class)
	public void testNull() {
		tok.tokenize(null);
	}

	public void testEmpty() {
		assertTrue(tok.tokenize("").isEmpty());
	}

	@Test
	public void testSimpleMoveKnight() {
		List<PGNToken> tokens = tok.tokenize("Ne4");

		assertArrayEquals(
				new PGNToken[] { //
						PGNToken.of(TokenTypes.CHESS_PIECE, "N"), //
						PGNToken.of(TokenTypes.CHESS_FILE, "e"), //
						PGNToken.of(TokenTypes.CHESS_RANK, "4") }, //
				tokens.toArray());
	}

	@Test
	public void testSimpleMoveRook() {
		List<PGNToken> tokens = tok.tokenize("Ra8");

		assertArrayEquals(
				new PGNToken[] { //
						PGNToken.of(TokenTypes.CHESS_PIECE, "R"), //
						PGNToken.of(TokenTypes.CHESS_FILE, "a"), //
						PGNToken.of(TokenTypes.CHESS_RANK, "8") }, //
				tokens.toArray());
	}

	@Test
	public void testSimpleMoveBishop() {
		List<PGNToken> tokens = tok.tokenize("Bh8");

		assertArrayEquals(
				new PGNToken[] { //
						PGNToken.of(TokenTypes.CHESS_PIECE, "B"), //
						PGNToken.of(TokenTypes.CHESS_FILE, "h"), //
						PGNToken.of(TokenTypes.CHESS_RANK, "8") }, //
				tokens.toArray());
	}

	@Test
	public void testInvalidToken() {
		List<PGNToken> tokens = tok.tokenize("Z");
		assertTrue(tokens.isEmpty());
	}

	@Test
	public void testSimpleMovePawn() {
		List<PGNToken> tokens = tok.tokenize("e4");

		assertArrayEquals(
				new PGNToken[] { //
						PGNToken.of(TokenTypes.CHESS_FILE, "e"), //
						PGNToken.of(TokenTypes.CHESS_RANK, "4") }, //
				tokens.toArray());
	}

	@Test
	public void testSimpleCaptureKnight() {
		List<PGNToken> tokens = tok.tokenize("Nxf6");

		assertArrayEquals(
				new PGNToken[] { //
						PGNToken.of(TokenTypes.CHESS_PIECE, "N"), //
						PGNToken.of(TokenTypes.CHESS_CAPTURE, "x"), PGNToken.of(TokenTypes.CHESS_FILE, "f"),
						PGNToken.of(TokenTypes.CHESS_RANK, "6") }, //
				tokens.toArray());
	}

	@Test
	public void testSimpleCaptureKing() {
		List<PGNToken> tokens = tok.tokenize("Kxh2");

		assertArrayEquals(
				new PGNToken[] { //
						PGNToken.of(TokenTypes.CHESS_PIECE, "K"), //
						PGNToken.of(TokenTypes.CHESS_CAPTURE, "x"), //
						PGNToken.of(TokenTypes.CHESS_FILE, "h"), //
						PGNToken.of(TokenTypes.CHESS_RANK, "2") }, //
				tokens.toArray());
	}

	@Test
	public void testSimplePromotion() {
		List<PGNToken> tokens = tok.tokenize("e8=Q");

		assertArrayEquals(
				new PGNToken[] { //
						PGNToken.of(TokenTypes.CHESS_FILE, "e"), //
						PGNToken.of(TokenTypes.CHESS_RANK, "8"), //
						PGNToken.of(TokenTypes.CHESS_PROMOTION, "="), //
						PGNToken.of(TokenTypes.CHESS_PIECE, "Q") }, //
				tokens.toArray());
	}

	@Test
	public void testSimpleCheck() {
		List<PGNToken> tokens = tok.tokenize("Nc6+");

		assertArrayEquals(
				new PGNToken[] { //
						PGNToken.of(TokenTypes.CHESS_PIECE, "N"), //
						PGNToken.of(TokenTypes.CHESS_FILE, "c"), //
						PGNToken.of(TokenTypes.CHESS_RANK, "6"), //
						PGNToken.of(TokenTypes.CHESS_CHECK, "+") }, //
				tokens.toArray());
	}

	@Test
	public void testSimpleCheckMate() {
		List<PGNToken> tokens = tok.tokenize("e1=Q#");

		assertArrayEquals(new PGNToken[] { //
				PGNToken.of(TokenTypes.CHESS_FILE, "e"), //
				PGNToken.of(TokenTypes.CHESS_RANK, "1"), //
				PGNToken.of(TokenTypes.CHESS_PROMOTION, "="), //
				PGNToken.of(TokenTypes.CHESS_PIECE, "Q"), //
				PGNToken.of(TokenTypes.CHESS_CHECKMATE, "#") //

		}, //
				tokens.toArray());
	}

	@Test
	public void testSimpleShortCastle() {
		List<PGNToken> tokens = tok.tokenize("O-O");

		assertArrayEquals(
				new PGNToken[] { //
						PGNToken.of(TokenTypes.CHESS_BIG_O, "O"), //
						PGNToken.of(TokenTypes.CHESS_DASH, "-"), //
						PGNToken.of(TokenTypes.CHESS_BIG_O, "O"), }, //
				tokens.toArray());
	}

	@Test
	public void testSimpleLongCastle() {
		List<PGNToken> tokens = tok.tokenize("O-O-O");

		assertArrayEquals(
				new PGNToken[] { //
						PGNToken.of(TokenTypes.CHESS_BIG_O, "O"), //
						PGNToken.of(TokenTypes.CHESS_DASH, "-"), //
						PGNToken.of(TokenTypes.CHESS_BIG_O, "O"), //
						PGNToken.of(TokenTypes.CHESS_DASH, "-"), //
						PGNToken.of(TokenTypes.CHESS_BIG_O, "O"), }, //
				tokens.toArray());
	}

	@Test
	public void testQueenMoveWithAmbiguity() {
		List<PGNToken> tokens = tok.tokenize("Qh8g7");

		assertArrayEquals(
				new PGNToken[] { //
						PGNToken.of(TokenTypes.CHESS_PIECE, "Q"), //
						PGNToken.of(TokenTypes.CHESS_FILE, "h"), //
						PGNToken.of(TokenTypes.CHESS_RANK, "8"), //
						PGNToken.of(TokenTypes.CHESS_FILE, "g"), //
						PGNToken.of(TokenTypes.CHESS_RANK, "7"), }, //
				tokens.toArray());
	}

}
