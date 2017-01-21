package com.copetti.pgn.interpreter;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.command.CaptureCommand;
import com.copetti.pgn.command.CastleLongCommand;
import com.copetti.pgn.command.CastleShortCommand;
import com.copetti.pgn.command.ChessCommand;
import com.copetti.pgn.command.ChessCommand.CheckFlag;
import com.copetti.pgn.command.MoveCommand;
import com.copetti.pgn.command.decorator.PromotionDecorator;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class PGNInterpreterTest {

	private PGNInterpreter inter;

	@Before
	public void setUp() {
		inter = new PGNInterpreter();
	}

	@Test
	public void interpretNullValue() {
		assertEquals(null, inter.parse(null));
	}

	@Test
	public void interpretZeroTokens() {

		assertEquals(null, inter.parse(""));
	}

	@Test
	public void interpretInvalidState() {

		assertEquals(null, inter.parse("Nxe4=K+R"));
	}

	@Test
	public void interpretInvalidToken() {

		assertEquals(null, inter.parse("Nxe4T=K+R"));
	}

	@Test
	public void interpretKnightMove() {

		ChessCommand c = inter.parse("Nc6");
		assertTrue(c instanceof MoveCommand);

		MoveCommand move = (MoveCommand) c;

		assertEquals(ChessPiece.KNIGHT, move.getPiece());
		assertEquals(new ChessSquare("c6"), move.getDestinationSquare());
		assertEquals(null, move.getPromotion());
		assertEquals(CheckFlag.FLAG_NONE, move.getFlag());
	}

	@Test
	public void interpretRookCapture() {

		ChessCommand c = inter.parse("Rxh7");

		assertTrue(c instanceof CaptureCommand);
		CaptureCommand move = (CaptureCommand) c;

		assertEquals(ChessPiece.ROOK, move.getPiece());
		assertEquals(new ChessSquare("h7"), move.getDestinationSquare());
		assertEquals(null, move.getPromotion());
		assertEquals(CheckFlag.FLAG_NONE, move.getFlag());
	}

	@Test
	public void interpretShortCastle() {

		ChessCommand c = inter.parse("O-O");
		assertThat(c, instanceOf(CastleShortCommand.class));

		CastleShortCommand move = (CastleShortCommand) c;

		assertEquals(ChessPiece.KING, move.getPiece());
		assertEquals(CheckFlag.FLAG_NONE, move.getFlag());
	}

	@Test
	public void interpretLongCastle() {

		ChessCommand c = inter.parse("O-O-O");
		assertThat(c, instanceOf(CastleLongCommand.class));

		CastleLongCommand move = (CastleLongCommand) c;

		assertEquals(ChessPiece.KING, move.getPiece());
		assertEquals(CheckFlag.FLAG_NONE, move.getFlag());
	}

	@Test
	public void interpretPawnPromotion() {

		ChessCommand c = inter.parse("h8=Q+");
		assertTrue(c instanceof MoveCommand);

		MoveCommand move = (MoveCommand) c;

		assertEquals(ChessPiece.PAWN, move.getPiece());
		assertEquals(new ChessSquare("h8"), move.getDestinationSquare());
		assertEquals(new PromotionDecorator(ChessPiece.QUEEN), move.getPromotion());
		assertEquals(CheckFlag.FLAG_CHECK, move.getFlag());
	}

	@Test
	public void interpretInvalidPiecePromotion() {

		ChessCommand c = inter.parse("h8=K+");
		assertTrue(c instanceof MoveCommand);

		MoveCommand move = (MoveCommand) c;

		assertEquals(ChessPiece.PAWN, move.getPiece());
		assertEquals(new ChessSquare("h8"), move.getDestinationSquare());
		assertEquals(new PromotionDecorator(ChessPiece.KING), move.getPromotion());
		assertEquals(CheckFlag.FLAG_CHECK, move.getFlag());
	}

	@Test
	public void interpretKingMove() {

		ChessCommand c = inter.parse("Kb1");
		assertTrue(c instanceof MoveCommand);

		MoveCommand move = (MoveCommand) c;

		assertEquals(ChessPiece.KING, move.getPiece());
		assertEquals(new ChessSquare("b1"), move.getDestinationSquare());
		assertEquals(null, move.getPromotion());
		assertEquals(CheckFlag.FLAG_NONE, move.getFlag());
	}

	@Test
	public void interpretQueenMoveAndCheck() {

		ChessCommand c = inter.parse("Qc6+");
		assertTrue(c instanceof MoveCommand);

		MoveCommand move = (MoveCommand) c;

		assertEquals(ChessPiece.QUEEN, move.getPiece());
		assertEquals(new ChessSquare("c6"), move.getDestinationSquare());
		assertEquals(null, move.getPromotion());
		assertEquals(CheckFlag.FLAG_CHECK, move.getFlag());
	}

	@Test
	public void interpretQueenCaptureAndMate() {

		ChessCommand c = inter.parse("Qxh7#");

		assertTrue(c instanceof CaptureCommand);
		CaptureCommand move = (CaptureCommand) c;

		assertEquals(ChessPiece.QUEEN, move.getPiece());
		assertEquals(new ChessSquare("h7"), move.getDestinationSquare());
		assertEquals(null, move.getPromotion());
		assertEquals(CheckFlag.FLAG_MATE, move.getFlag());
	}

}
