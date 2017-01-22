package com.copetti.pgn.board.factory;

import java.util.Map;

import com.copetti.pgn.board.CastleInformation;
import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessBoardContext;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.FullMoveCounter;
import com.copetti.pgn.board.HalfMoveCounter;

public class BoardTestFactory {

	public static ChessBoard createNew(String board, ChessColor nextToPlay) {

		Map<ChessSquare, ColoredChessPiece> map = new VisualChessBoardFactory().newBoard(board);

		return createNew(map, nextToPlay);

	}

	public static ChessBoard createNew(Map<ChessSquare, ColoredChessPiece> board, ChessColor nextToPlay) {

		ChessBoardContext ctx = new ChessBoardContext(nextToPlay, CastleInformation.none(), null,
				HalfMoveCounter.first(), FullMoveCounter.first());

		return new ChessBoard(board, ctx);

	}

}
