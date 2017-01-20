package com.copetti.pgn.board.serializer.FEN;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.copetti.pgn.board.CastleInformation;
import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessBoardContext;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.FullMoveCounter;
import com.copetti.pgn.board.HalfMoveCounter;
import com.copetti.pgn.board.factory.VisualChessBoardFactory;

public class FENDeserializerTest {

	private FENDeserializer deser = new FENDeserializer();

	private Map<ChessSquare, ColoredChessPiece> getPieces(String visualBoard) {
		return new VisualChessBoardFactory().newBoard(visualBoard);
	}

	@Test
	public void initialSetup() {
		/* Initial chess board setup */
		// rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1

		String initialSetup = "rnbqkbnr" + "pppppppp" + "--------" + "--------" + "--------" + "--------" + "PPPPPPPP"
				+ "RNBQKBNR";

		ChessColor next = ChessColor.WHITE;
		CastleInformation castle = CastleInformation.all();
		ChessSquare enPassant = null;

		HalfMoveCounter halfMove = HalfMoveCounter.first();
		FullMoveCounter fullMove = FullMoveCounter.first();

		ChessBoardContext ctx = new ChessBoardContext(next, castle, enPassant, halfMove, fullMove);
		ChessBoard cb = new ChessBoard(getPieces(initialSetup), ctx);

		String fenBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		assertEquals(cb, deser.deserialize(fenBoard));
	}

	@Test
	public void initialSetupAfterE4() {

		/* After e4 */
		// rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1

		String initialSetup = //
				"" + //
						"rnbqkbnr" + //
						"pppppppp" + //
						"--------" + //
						"--------" + //
						"----P---" + //
						"--------" + //
						"PPPP-PPP" + //
						"RNBQKBNR";

		ChessColor next = ChessColor.BLACK;
		CastleInformation castle = CastleInformation.all();
		ChessSquare enPassant = new ChessSquare("e3");

		HalfMoveCounter halfMove = HalfMoveCounter.first();
		FullMoveCounter fullMove = FullMoveCounter.first();
		ChessBoardContext ctx = new ChessBoardContext(next, castle, enPassant, halfMove, fullMove);

		ChessBoard cb = new ChessBoard(getPieces(initialSetup), ctx);

		String fenBoard = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
		assertEquals(cb, deser.deserialize(fenBoard));
	}

	@Test
	public void imCrushingHimResignation() {
		/* https://en.lichess.org/study/QuFstlOs */
		// 1Rr5/3b4/Q1NPpk2/3p1p1p/3P1Pp1/P5P1/1P6/2K5 b - - 2 51

		String initialSetup = //
				"" + //
						"-Rr-----" + //
						"---b----" + //
						"Q-NPpk--" + //
						"---p-p-p" + //
						"---P-Pp-" + //
						"P-----P-" + //
						"-P------" + //
						"--K-----";

		ChessColor next = ChessColor.BLACK;
		CastleInformation castle = CastleInformation.none();
		ChessSquare enPassant = null;

		HalfMoveCounter halfMove = new HalfMoveCounter(2);
		FullMoveCounter fullMove = new FullMoveCounter(51);
		ChessBoardContext ctx = new ChessBoardContext(next, castle, enPassant, halfMove, fullMove);

		ChessBoard cb = new ChessBoard(getPieces(initialSetup), ctx);

		String fenBoard = "1Rr5/3b4/Q1NPpk2/3p1p1p/3P1Pp1/P5P1/1P6/2K5 b - - 2 51";
		assertEquals(cb, deser.deserialize(fenBoard));
	}

	@Test
	public void supriseMateTest() {
		/* Surprise Mate */
		/* https://en.lichess.org/y6pUC1CK/white#47 */
		// 1k1r2BB/ppp1Q3/5p2/6p1/8/3P2P1/P1P2q1P/1N2R2K b - - 0 24

		String initialSetup = //
				"" + //
						"-k-r--BB" + //
						"ppp-Q---" + //
						"-----p--" + //
						"------p-" + //
						"--------" + //
						"---P--P-" + //
						"P-P--q-P" + //
						"-N--R--K";

		ChessColor next = ChessColor.BLACK;
		CastleInformation castle = CastleInformation.none();
		ChessSquare enPassant = null;

		HalfMoveCounter halfMove = new HalfMoveCounter(0);
		FullMoveCounter fullMove = new FullMoveCounter(24);
		ChessBoardContext ctx = new ChessBoardContext(next, castle, enPassant, halfMove, fullMove);

		ChessBoard cb = new ChessBoard(getPieces(initialSetup), ctx);

		String fenBoard = "1k1r2BB/ppp1Q3/5p2/6p1/8/3P2P1/P1P2q1P/1N2R2K b - - 0 24";
		assertEquals(cb, deser.deserialize(fenBoard));
	}

	@Test
	public void RookStalemate() {
		/* Incredible stalemate https://en.lichess.org/study/QuFstlOs */
		// k7/P6R/1P6/8/7P/8/2P1K3/8 w - - 1 41

		String initialSetup = //
				"" + //
						"k-------" + //
						"P------R" + //
						"-P------" + //
						"--------" + //
						"-------P" + //
						"--------" + //
						"--P-K---" + //
						"--------";

		ChessColor next = ChessColor.WHITE;
		CastleInformation castle = CastleInformation.none();
		ChessSquare enPassant = null;

		HalfMoveCounter halfMove = new HalfMoveCounter(1);
		FullMoveCounter fullMove = new FullMoveCounter(41);
		ChessBoardContext ctx = new ChessBoardContext(next, castle, enPassant, halfMove, fullMove);

		ChessBoard cb = new ChessBoard(getPieces(initialSetup), ctx);

		String fenBoard = "k7/P6R/1P6/8/7P/8/2P1K3/8 w - - 1 41";
		assertEquals(cb, deser.deserialize(fenBoard));
	}

}
