package com.copetti.pgncommon.chess.board.builder;

import java.util.Map;

import com.copetti.pgnchess.board.ChessFile;
import com.copetti.pgnchess.board.ChessRank;
import com.copetti.pgnchess.pieces.ChessPiece;
import com.copetti.pgncommon.chess.board.ChessSquare;

public class ChessBoardBuilderFillFile implements ChessBoardBuilderFillWith {

	private Map<ChessSquare, ChessPiece> pieces;
	private ChessFile chessFile;

	public ChessBoardBuilderFillFile(Map<ChessSquare, ChessPiece> pieces, ChessFile chessFile) {
		this.pieces = pieces;
		this.chessFile = chessFile;
	}

	@Override
	public ChessBoardBuilder with(ChessPiece piece) {
		
		for (ChessRank c : ChessRank.values())
			pieces.put(new ChessSquare(chessFile, c), piece);

		return new ChessBoardBuilder(pieces);
	}

}
