package com.copetti.pgn.lexical;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.copetti.pgn.lexical.state.CaptureState;
import com.copetti.pgn.lexical.state.CheckState;
import com.copetti.pgn.lexical.state.CheckmateState;
import com.copetti.pgn.lexical.state.ChessPieceState;
import com.copetti.pgn.lexical.state.DesambiguateFileState;
import com.copetti.pgn.lexical.state.DesambiguateRankState;
import com.copetti.pgn.lexical.state.DestinationSquareState;
import com.copetti.pgn.lexical.state.LexicalState;
import com.copetti.pgn.lexical.state.PawnFileState;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class PGNLexicalChessCaptureTest {

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
	public void testKnightCaptureWithCheck() {

		List<LexicalState> lex = new PGNLexical().execute("Kxc7+");

		ChessPieceState piece = new ChessPieceState(ChessPiece.KING);
		CaptureState captureState = new CaptureState();
		DestinationSquareState dest = new DestinationSquareState("c7");
		CheckState check = new CheckState();

		assertEquals(Arrays.asList(piece, captureState, dest, check), lex);
	}

	@Test
	public void testQueenCaptureWithMate() {

		List<LexicalState> lex = new PGNLexical().execute("Rxe8#");

		ChessPieceState piece = new ChessPieceState(ChessPiece.ROOK);
		CaptureState captureState = new CaptureState();
		DestinationSquareState dest = new DestinationSquareState("e8");
		CheckmateState mate = new CheckmateState();

		assertEquals(Arrays.asList(piece, captureState, dest, mate), lex);
	}

}
