package com.copetti.pgn.lexical;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.copetti.pgn.lexical.state.CaptureState;
import com.copetti.pgn.lexical.state.CheckmateState;
import com.copetti.pgn.lexical.state.ChessPieceState;
import com.copetti.pgn.lexical.state.DesambiguateFileState;
import com.copetti.pgn.lexical.state.DestinationSquareState;
import com.copetti.pgn.lexical.state.LexicalState;
import com.copetti.pgn.lexical.state.PawnFileState;
import com.copetti.pgn.lexical.state.PromotionState;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class PGNLexicalTest {

	@Test
	public void testEmptyTokens() {

		assertTrue(new PGNLexical().execute(new ArrayList<>()).isEmpty());
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
	public void testCommandWithMovementCaptureAndPromotionWithMate() {

		List<LexicalState> lex = new PGNLexical().execute("axb8=Q#");

		PawnFileState pawn = new PawnFileState(ChessFile.A);
		CaptureState capture = new CaptureState();
		DestinationSquareState dest = new DestinationSquareState("b8");
		PromotionState promotion = new PromotionState(ChessPiece.QUEEN);
		CheckmateState mate = new CheckmateState();

		assertEquals(Arrays.asList(pawn, capture, dest, promotion, mate), lex);

	}

}
