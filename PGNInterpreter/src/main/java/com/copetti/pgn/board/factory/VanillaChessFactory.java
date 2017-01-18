package com.copetti.pgn.board.factory;

import java.util.Map;

import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.builder.ChessBoardBuilder;

public class VanillaChessFactory {

	public Map<ChessSquare, ColoredChessPiece> newBoard() {
		ChessBoardBuilder b = ChessBoardBuilder.newBuilder();

		return b.build();
	}

}
