package com.copetti.pgn.lexical.state;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tools.ant.filters.ReplaceTokens.Token;
import org.junit.Test;

import com.copetti.pgn.lexical.PGNLexical;
import com.copetti.pgn.lexical.state.CaptureState;
import com.copetti.pgn.lexical.state.ChessPieceState;
import com.copetti.pgn.lexical.state.DestinationSquareState;
import com.copetti.pgn.lexical.state.LexicalState;
import com.copetti.pgn.lexical.state.PawnFileState;
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

		List<LexicalState> lex = new PGNLexical().execute("Nc6");

		ChessPieceState pieceState = new ChessPieceState(ChessPiece.KNIGHT);
		DestinationSquareState dest = new DestinationSquareState("c6");
		assertEquals(Arrays.asList(pieceState, dest), lex);
	}

	@Test
	public void testKnightMoveInvalidSemantically() {

		List<LexicalState> lex = new PGNLexical().execute("Ncc6");

		ChessPieceState pieceState = new ChessPieceState(ChessPiece.KNIGHT);
		DesambiguateFileState file = new DesambiguateFileState(ChessFile.C);
		DestinationSquareState dest = new DestinationSquareState("c6");
		assertEquals(Arrays.asList(pieceState, file, dest), lex);

	}

	@Test
	public void testBishopMove() {

		List<LexicalState> lex = new PGNLexical().execute("Ba1");

		ChessPieceState pieceState = new ChessPieceState(ChessPiece.BISHOP);
		DestinationSquareState dest = new DestinationSquareState("a1");
		assertEquals(Arrays.asList(pieceState, dest), lex);
	}

	@Test
	public void testPawnMove() {

		List<LexicalState> lex = new PGNLexical().execute("e4");
		assertEquals(new DestinationSquareState("e4"), lex.get(0));
	}

	@Test
	public void testKnightCapture() {

		List<LexicalState> lex = new PGNLexical().execute("Nxf6");

		ChessPieceState pieceState = new ChessPieceState(ChessPiece.KNIGHT);
		assertEquals(pieceState, lex.get(0));
		assertEquals(new CaptureState(), lex.get(1));
		assertEquals(new DestinationSquareState("f6"), lex.get(2));
	}

	@Test
	public void testPawnCapture() {

		List<LexicalState> lex = new PGNLexical().execute("exf6");

		PawnFileState fileState = new PawnFileState(ChessFile.of("e").get());
		CaptureState captureState = new CaptureState();
		DestinationSquareState dest = new DestinationSquareState("f6");

		assertEquals(Arrays.asList(fileState, captureState, dest), lex);
	}

	@Test
	public void testRookMoveWithAmbiguityInRank() {

		List<LexicalState> lex = new PGNLexical().execute("Rah7");

		ChessPieceState piece = new ChessPieceState(ChessPiece.ROOK);
		DesambiguateFileState state = new DesambiguateFileState(ChessFile.A);
		DestinationSquareState dest = new DestinationSquareState("h7");

		assertEquals(Arrays.asList(piece, state, dest), lex);
	}

	@Test
	public void testRookMoveWithAmbiguityInFile() {

		List<LexicalState> lex = new PGNLexical().execute("R8a5");

		ChessPieceState piece = new ChessPieceState(ChessPiece.ROOK);
		DesambiguateRankState state = new DesambiguateRankState(ChessRank.of("8").get());
		DestinationSquareState dest = new DestinationSquareState("a5");

		assertEquals(Arrays.asList(piece, state, dest), lex);
	}

	@Test
	public void testQueenMoveWithAmbiguityInFileAndRank() {

		/*
		 * http://chess.stackexchange.com/questions/1817/how-are-pgn-ambiguities
		 * -handled
		 */
		List<LexicalState> lex = new PGNLexical().execute("Qh8g7");

		ChessPieceState piece = new ChessPieceState(ChessPiece.QUEEN);
		DesambiguateFileState file = new DesambiguateFileState(ChessFile.H);
		DesambiguateRankState rank = new DesambiguateRankState(ChessRank.of("8").get());
		DestinationSquareState dest = new DestinationSquareState("g7");

		assertEquals(Arrays.asList(piece, file, rank, dest), lex);
	}

	@Test
	public void testRookCaptureWithAmbiguityInRank() {

		List<LexicalState> lex = new PGNLexical().execute("Raxh7");

		ChessPieceState piece = new ChessPieceState(ChessPiece.ROOK);
		DesambiguateFileState state = new DesambiguateFileState(ChessFile.A);
		CaptureState capture = new CaptureState();
		DestinationSquareState dest = new DestinationSquareState("h7");

		assertEquals(Arrays.asList(piece, state, capture, dest), lex);
	}

	@Test
	public void testRookCaptureWithAmbiguityInFile() {

		List<LexicalState> lex = new PGNLexical().execute("R8xa5");

		ChessPieceState piece = new ChessPieceState(ChessPiece.ROOK);
		DesambiguateRankState rank = new DesambiguateRankState(ChessRank.of("8").get());
		CaptureState capture = new CaptureState();
		DestinationSquareState dest = new DestinationSquareState("a5");

		assertEquals(Arrays.asList(piece, rank, capture, dest), lex);
	}

	@Test
	public void testQueenCaptureWithAmbiguityInFileAndRank() {

		/*
		 * http://chess.stackexchange.com/questions/1817/how-are-pgn-ambiguities
		 * -handled
		 */
		List<LexicalState> lex = new PGNLexical().execute("Qh8xg7");

		ChessPieceState piece = new ChessPieceState(ChessPiece.QUEEN);
		DesambiguateFileState file = new DesambiguateFileState(ChessFile.H);
		DesambiguateRankState rank = new DesambiguateRankState(ChessRank.of("8").get());
		CaptureState capture = new CaptureState();
		DestinationSquareState dest = new DestinationSquareState("g7");

		assertEquals(Arrays.asList(piece, file, rank, capture, dest), lex);
	}

}
