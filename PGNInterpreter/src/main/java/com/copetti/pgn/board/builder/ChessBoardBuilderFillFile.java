package com.copetti.pgn.board.builder;

import java.util.Map;

import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class ChessBoardBuilderFillFile implements ChessBoardBuilderFillWith {

	private Map<ChessSquare, ColoredChessPiece> pieces;
	private ChessFile chessFile;

	public ChessBoardBuilderFillFile(Map<ChessSquare, ColoredChessPiece> pieces, ChessFile chessFile) {
		this.pieces = pieces;
		this.chessFile = chessFile;
	}

	@Override
	public ChessBoardBuilder with(ColoredChessPiece piece) {

		for (ChessRank c : ChessRank.values())
			pieces.put(new ChessSquare(chessFile, c), piece);

		return new ChessBoardBuilder(pieces, null);
	}

}
