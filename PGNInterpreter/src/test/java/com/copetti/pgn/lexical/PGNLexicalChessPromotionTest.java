package com.copetti.pgn.lexical;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.copetti.pgn.lexical.state.CaptureState;
import com.copetti.pgn.lexical.state.CheckState;
import com.copetti.pgn.lexical.state.CheckmateState;
import com.copetti.pgn.lexical.state.DestinationSquareState;
import com.copetti.pgn.lexical.state.LexicalState;
import com.copetti.pgn.lexical.state.PawnFileState;
import com.copetti.pgn.lexical.state.PromotionState;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class PGNLexicalChessPromotionTest {

	@Test
	public void testInvalidPromotion() {

		/*
		 * http://chess.stackexchange.com/questions/1817/how-are-pgn-ambiguities
		 * -handled
		 */
		List<LexicalState> lex = new PGNLexical().execute("e8=K");

		DestinationSquareState dest = new DestinationSquareState("e8");
		PromotionState promotion = new PromotionState(ChessPiece.KING);

		assertEquals(Arrays.asList(dest, promotion), lex);
	}

	@Test
	public void testMoveWithPromotion() {

		/*
		 * http://chess.stackexchange.com/questions/1817/how-are-pgn-ambiguities
		 * -handled
		 */
		List<LexicalState> lex = new PGNLexical().execute("a8=Q");

		DestinationSquareState dest = new DestinationSquareState("a8");
		PromotionState promotion = new PromotionState(ChessPiece.QUEEN);

		assertEquals(Arrays.asList(dest, promotion), lex);
	}

	@Test
	public void testCaptureWithPromotion() {

		/*
		 * http://chess.stackexchange.com/questions/1817/how-are-pgn-ambiguities
		 * -handled
		 */
		List<LexicalState> lex = new PGNLexical().execute("axb8=N");

		PawnFileState file = new PawnFileState(ChessFile.A);
		CaptureState capture = new CaptureState();
		DestinationSquareState dest = new DestinationSquareState("b8");
		PromotionState promotion = new PromotionState(ChessPiece.KNIGHT);

		assertEquals(Arrays.asList(file, capture, dest, promotion), lex);
	}

	@Test
	public void testMoveWithPromotionAndCheck() {

		/*
		 * http://chess.stackexchange.com/questions/1817/how-are-pgn-ambiguities
		 * -handled
		 */
		List<LexicalState> lex = new PGNLexical().execute("a8=R+");

		DestinationSquareState dest = new DestinationSquareState("a8");
		PromotionState promotion = new PromotionState(ChessPiece.ROOK);
		CheckState check = new CheckState();

		assertEquals(Arrays.asList(dest, promotion, check), lex);
	}

	@Test
	public void testCaptureWithPromotionAndMate() {
		/*
		 * http://chess.stackexchange.com/questions/1817/how-are-pgn-ambiguities
		 * -handled
		 */
		List<LexicalState> lex = new PGNLexical().execute("a8=Q#");

		DestinationSquareState dest = new DestinationSquareState("a8");
		PromotionState promotion = new PromotionState(ChessPiece.QUEEN);
		CheckmateState check = new CheckmateState();

		assertEquals(Arrays.asList(dest, promotion, check), lex);
	}

}
