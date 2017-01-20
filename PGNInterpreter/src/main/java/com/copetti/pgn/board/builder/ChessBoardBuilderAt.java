package com.copetti.pgn.board.builder;

import java.util.Map;

import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class ChessBoardBuilderAt {

	private Map<ChessSquare, ColoredChessPiece> pieces;
	private String[] square;
	private ChessColor color;

	public ChessBoardBuilderAt(Map<ChessSquare, ColoredChessPiece> map, ChessColor color, String[] square) {
		this.pieces = map;
		this.square = square;
		this.color = color;
	}

	public ChessBoardBuilder put(ChessPiece piece) {

		if (square != null) {
			for (String s : square)
				pieces.put(new ChessSquare(s), new ColoredChessPiece(piece, color));
		}

		return new ChessBoardBuilder(pieces, color);
	}

}
