package com.copetti.pgn.logic.moves;

import static com.copetti.pgn.logic.moves.ChessAssertiveLibrary.assertContains;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.serializer.FEN.FENDeserializer;
import com.copetti.pgn.logic.ChessMovementResolver;

public class CastlingMovesTest {

	@Test
	public void getMovesWithCastlingAvailableWhite() {

		ChessBoard board = new FENDeserializer().deserialize("rnbqkbnr/pppp1ppp/8/8/8/8/PPPPPPPP/R3K2R w KQ - 0 1");

		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(board.getKing(ChessColor.WHITE), board);
		assertEquals(4, moves.size());
		assertContains(moves, "c1", "d1", "f1", "g1");
	}

	@Test
	public void getMovesWithCastlingAvailableBlack() {

		ChessBoard board = new FENDeserializer().deserialize("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/R1N1K1NR b kq - 0 1");

		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(board.getKing(ChessColor.BLACK), board);
		assertEquals(4, moves.size());
		assertContains(moves, "c8", "d8", "f8", "g8");
	}

	@Test
	public void getMovesWithNoCastlingAvailableForWhite() {

		ChessBoard board = new FENDeserializer().deserialize("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/R3K2R w kq - 0 1");

		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(board.getKing(ChessColor.WHITE), board);
		assertEquals(2, moves.size());
		assertContains(moves, "d1", "f1");
	}

	@Test
	public void getMovesWithNoCastlingAvailableForBlack() {

		ChessBoard board = new FENDeserializer().deserialize("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/R3K2R b KQ - 0 1");

		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(board.getKing(ChessColor.BLACK), board);
		assertEquals(2, moves.size());
		assertContains(moves, "d8", "f8");
	}

	@Test
	public void getMovesWithCastlingAvailableButBlockedForWhite() {

		ChessBoard board = new FENDeserializer().deserialize("rn2k1nr/pppppppp/8/8/8/8/PPPPPPPP/RN2K1NR w KQkq - 0 1");

		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(board.getKing(ChessColor.WHITE), board);
		assertEquals(2, moves.size());
		assertContains(moves, "d1", "f1");
	}

	@Test
	public void getMovesWithCastlingAvailableButBlockedForBlack() {

		ChessBoard board = new FENDeserializer().deserialize("rn2k1nr/pppppppp/8/8/8/8/PPPPPPPP/RN2K1NR b KQkq - 0 1");

		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(board.getKing(ChessColor.BLACK), board);
		assertEquals(2, moves.size());
		assertContains(moves, "d8", "f8");
	}

	@Test
	public void getMovesWithCastlingAvailableButDestinationSquareIsUnderAttackForWhite() {

		ChessBoard board = new FENDeserializer()
				.deserialize("r3k2r/ppp1p1pp/1b1p1p1b/8/8/1B1P1P1B/PPP1P1PP/R3K2R w KQkq - 0 1");

		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(board.getKing(ChessColor.WHITE), board);
		assertEquals(4, moves.size());
		assertContains(moves, "d1", "f1", "d2", "f2");
	}

	@Test
	public void getMovesWithCastlingAvailableButDestinationSquareIsUnderAttackForBlack() {

		ChessBoard board = new FENDeserializer()
				.deserialize("r3k2r/ppp1p1pp/1b1p1p1b/8/8/1B1P1P1B/PPP1P1PP/R3K2R b KQkq - 0 1");

		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(board.getKing(ChessColor.BLACK), board);
		assertEquals(4, moves.size());
		assertContains(moves, "d8", "f8", "d7", "f7");
	}

	@Test
	public void getMovesWithCastlingAvailableButKingIsUnderAttackForWhite() {

		ChessBoard board = new FENDeserializer()
				.deserialize("r3k2r/pppp2pp/1b3p1b/8/8/1B1n1P1B/PPPP2PP/R3K2R w KQkq - 0 1");

		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(board.getKing(ChessColor.WHITE), board);
		assertEquals(4, moves.size());
		assertContains(moves, "d1", "f1", "e2", "f2");
	}

	@Test
	public void getMovesWithCastlingAvailableButKingIsUnderAttackForBlack() {

		ChessBoard board = new FENDeserializer()
				.deserialize("r3k2r/pppp2pp/1b1N1p1b/8/8/1B3P1B/PPPP2PP/R3K2R b KQkq - 0 1");

		ChessMovementResolver cmr = new ChessMovementResolver();

		Set<ChessSquare> moves = cmr.getMoves(board.getKing(ChessColor.BLACK), board);
		assertEquals(4, moves.size());
		assertContains(moves, "d8", "f8", "e7", "f7");
	}

}
