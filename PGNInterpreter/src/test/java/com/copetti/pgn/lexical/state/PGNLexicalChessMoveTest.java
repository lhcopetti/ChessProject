package com.copetti.pgn.lexical.state;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.copetti.pgn.lexical.PGNLexical;
import com.copetti.pgncommon.chess.token.ChessFile;
import com.copetti.pgncommon.chess.token.ChessPiece;
import com.copetti.pgncommon.chess.token.ChessRank;

public class PGNLexicalChessMoveTest {

	@Test
	public void testKnightMove() {

		List<LexicalState> lex = new PGNLexical().execute("Nc6");

		ChessPieceState pieceState = new ChessPieceState(ChessPiece.KNIGHT);
		DestinationSquareState dest = new DestinationSquareState("c6");
		assertEquals(Arrays.asList(pieceState, dest), lex);
	}

	@Test
	public void testKingMove() {

		List<LexicalState> lex = new PGNLexical().execute("Kh8");

		ChessPieceState piece = new ChessPieceState(ChessPiece.KING);
		DestinationSquareState dest = new DestinationSquareState("h8");
		assertEquals(Arrays.asList(piece, dest), lex);
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
	public void testMoveWithCheck() {

		/*
		 * http://chess.stackexchange.com/questions/1817/how-are-pgn-ambiguities
		 * -handled
		 */
		List<LexicalState> lex = new PGNLexical().execute("Qh8g7+");

		ChessPieceState piece = new ChessPieceState(ChessPiece.QUEEN);
		DesambiguateFileState file = new DesambiguateFileState(ChessFile.H);
		DesambiguateRankState rank = new DesambiguateRankState(ChessRank.of("8").get());
		DestinationSquareState dest = new DestinationSquareState("g7");
		CheckState check = new CheckState();

		assertEquals(Arrays.asList(piece, file, rank, dest, check), lex);
	}

	@Test
	public void testMoveWithCheckMate() {

		/*
		 * http://chess.stackexchange.com/questions/1817/how-are-pgn-ambiguities
		 * -handled
		 */
		List<LexicalState> lex = new PGNLexical().execute("Nf7#");

		ChessPieceState piece = new ChessPieceState(ChessPiece.KNIGHT);
		DestinationSquareState dest = new DestinationSquareState("f7");
		CheckmateState mate = new CheckmateState();

		assertEquals(Arrays.asList(piece, dest, mate), lex);
	}

}
