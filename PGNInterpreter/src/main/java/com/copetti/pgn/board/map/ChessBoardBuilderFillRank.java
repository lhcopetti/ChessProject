package com.copetti.pgn.board.map;

import java.util.Map;

import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class ChessBoardBuilderFillRank implements ChessBoardBuilderFillWith {

	private ChessRank chessRank;
	private Map<ChessSquare, ColoredChessPiece> pieces;

	public ChessBoardBuilderFillRank(Map<ChessSquare, ColoredChessPiece> pieces, ChessRank chessRank) {
		this.pieces = pieces;
		this.chessRank = chessRank;
	}

	public ChessBoardBuilder with(ColoredChessPiece piece) {

		for (ChessFile f : ChessFile.values())
			pieces.put(new ChessSquare(f, chessRank), piece);

		return new ChessBoardBuilder(pieces, null);
	}

}
