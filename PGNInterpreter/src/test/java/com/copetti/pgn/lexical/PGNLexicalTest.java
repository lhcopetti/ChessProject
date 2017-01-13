package com.copetti.pgn.lexical;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.filters.ReplaceTokens.Token;
import org.junit.Test;

import com.copetti.pgn.lexical.state.CaptureState;
import com.copetti.pgn.lexical.state.ChessPieceState;
import com.copetti.pgn.lexical.state.DestinationSquareState;
import com.copetti.pgn.lexical.state.LexicalState;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.PGNTokenizer;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgncommon.chess.token.ChessFile;
import com.copetti.pgncommon.chess.token.ChessPiece;
import com.copetti.pgncommon.chess.token.ChessRank;

public class PGNLexicalTest {

	@Test
	public void testEmptyTokens() {

		assertTrue(new PGNLexical().execute(new ArrayList<>()).isEmpty());
	}

	@Test
	public void testKnightMove() {

		PGNTokenizer tokenizer = new PGNTokenizer();
		List<PGNToken> tokens = tokenizer.tokenize("Nc6");
		List<LexicalState> lex = new PGNLexical().execute(tokens);

		ChessPieceState pieceState = new ChessPieceState(PGNToken.of(TokenTypes.CHESS_PIECE, "N"));
		assertEquals(pieceState, lex.get(0));

		assertEquals(new DestinationSquareState("c6"), lex.get(1));
	}

	@Test
	public void testKnightMoveInvalid() {

		PGNTokenizer tokenizer = new PGNTokenizer();
		List<PGNToken> knightMove = tokenizer.tokenize("Ncc6");
		assertFalse(new PGNLexical().execute(knightMove));
	}

	@Test
	public void testBishopMove() {

		PGNTokenizer tokenizer = new PGNTokenizer();
		List<PGNToken> tokens = tokenizer.tokenize("Ba1");
		List<LexicalState> lex = new PGNLexical().execute(tokens);

		ChessPieceState pieceState = new ChessPieceState(PGNToken.of(TokenTypes.CHESS_PIECE, "B"));
		assertEquals(pieceState, lex.get(0));
		assertEquals(new DestinationSquareState("a1"), lex.get(1));
	}

	@Test
	public void testPawnMove() {

		List<LexicalState> lex = new PGNLexical().execute("e4");
		assertEquals(new DestinationSquareState("e4"), lex.get(0));
	}

	@Test
	public void testKnightCapture() {

		List<LexicalState> lex = new PGNLexical().execute("Nxf6");

		ChessPieceState pieceState = new ChessPieceState(PGNToken.of(TokenTypes.CHESS_PIECE, "N"));
		assertEquals(pieceState, lex.get(0));
		assertEquals(new CaptureState(), lex.get(1));
		assertEquals(new DestinationSquareState("f6"), lex.get(2));
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
