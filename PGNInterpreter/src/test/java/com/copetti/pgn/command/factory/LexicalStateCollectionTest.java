package com.copetti.pgn.command.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.lexical.PGNLexical;
import com.copetti.pgn.lexical.state.CaptureState;
import com.copetti.pgn.lexical.state.CastleLongState;
import com.copetti.pgn.lexical.state.CastleShortState;
import com.copetti.pgn.lexical.state.DestinationSquareState;
import com.copetti.pgn.lexical.state.LexicalState;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class LexicalStateCollectionTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testModifyList() {

		List<LexicalState> l = Arrays.asList(new CaptureState(), new DestinationSquareState("e4"));
		LexicalStateCollection collection = new LexicalStateCollection(l);
		collection.getCollection().remove(0);
	}

	@Test
	public void isShortCastleCommandTest() {
		LexicalStateCollection l = new LexicalStateCollection(Arrays.asList(new CastleShortState()));
		assertTrue(l.isShortCastleCommand());
	}

	@Test
	public void isLongCastleCommandTest() {
		LexicalStateCollection l = new LexicalStateCollection(Arrays.asList(new CastleLongState()));
		assertTrue(l.isLongCastleCommand());
	}

	@Test
	public void isCaptureStateTest() {
		LexicalStateCollection l = new LexicalStateCollection(Arrays.asList(new CaptureState()));
		assertTrue(l.isCaptureCommand());
	}

	@Test
	public void isPromotionStateTest() {
		List<LexicalState> l = new PGNLexical().execute("e8=Q+");
		LexicalStateCollection coll = new LexicalStateCollection(l);
		assertTrue(coll.isPromotionCommand());
	}

	@Test
	public void getDestinationSquareStateTest() {
		List<LexicalState> l = new PGNLexical().execute("e4");
		LexicalStateCollection coll = new LexicalStateCollection(l);
		assertEquals(new ChessSquare("e4"), coll.getDestinationSquare());
	}

	@Test
	public void getChessPieceStateKnightTest() {
		List<LexicalState> l = new PGNLexical().execute("Nxc6+");
		LexicalStateCollection coll = new LexicalStateCollection(l);
		assertEquals(ChessPiece.KNIGHT, coll.getChessPiece());
	}

	@Test
	public void getChessPieceStatePawnTest() {
		List<LexicalState> l = new PGNLexical().execute("exf8=Q#");
		LexicalStateCollection coll = new LexicalStateCollection(l);
		assertEquals(ChessPiece.PAWN, coll.getChessPiece());
	}

	@Test
	public void getChessPieceStateCastleTest() {
		List<LexicalState> l = new PGNLexical().execute("O-O");
		LexicalStateCollection coll = new LexicalStateCollection(l);
		assertEquals(ChessPiece.KING, coll.getChessPiece());

	}

	@Test
	public void getChessPieceStateCastleLongTest() {
		List<LexicalState> l = new PGNLexical().execute("O-O-O");
		LexicalStateCollection coll = new LexicalStateCollection(l);
		assertEquals(ChessPiece.KING, coll.getChessPiece());
	}

}
