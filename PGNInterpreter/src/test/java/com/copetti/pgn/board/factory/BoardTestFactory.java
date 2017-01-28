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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardTestFactory {

	public static ChessBoard createNew(String board, ChessColor nextToPlay) {
		return createNew(board, nextToPlay, CastleInformation.none());
	}

	public static ChessBoard createNew(String board, ChessColor nextToPlay, CastleInformation info) {

		Map<ChessSquare, ColoredChessPiece> map = new VisualChessBoardFactory().newBoard(board);
		return createNew(map, nextToPlay, null, info);
	}

	public static ChessBoard createNew(Map<ChessSquare, ColoredChessPiece> board, ChessColor nextToPlay,
			ChessSquare enPassant, CastleInformation info) {

		ChessBoardContext ctx = new ChessBoardContext(nextToPlay, info, enPassant, HalfMoveCounter.first(),
				FullMoveCounter.first());

		return new ChessBoard(board, ctx);
	}

	public static ChessBoard createNew(Map<ChessSquare, ColoredChessPiece> board, ChessColor nextToPlay) {
		return createNew(board, nextToPlay, null, CastleInformation.none());
	}

	public static ChessBoard enPassantBoard(Map<ChessSquare, ColoredChessPiece> board, ChessColor nextToPlay,
			ChessSquare enPassant) {
		return createNew(board, nextToPlay, enPassant, CastleInformation.none());
	}

}
