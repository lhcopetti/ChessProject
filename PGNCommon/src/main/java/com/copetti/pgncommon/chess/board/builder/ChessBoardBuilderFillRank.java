package com.copetti.pgncommon.chess.board.builder;

import java.util.Map;

import com.copetti.pgnchess.board.ChessFile;
import com.copetti.pgnchess.board.ChessRank;
import com.copetti.pgnchess.pieces.ChessPiece;
import com.copetti.pgncommon.chess.board.ChessSquare;

public class ChessBoardBuilderFillRank implements ChessBoardBuilderFillWith {

	private ChessRank chessRank;
	private Map<ChessSquare, ChessPiece> pieces;

	public ChessBoardBuilderFillRank(Map<ChessSquare, ChessPiece> pieces, ChessRank chessRank) {
		this.pieces = pieces;
		this.chessRank = chessRank;
	}

	public ChessBoardBuilder with(ChessPiece piece) {

		for (ChessFile f : ChessFile.values())
			pieces.put(new ChessSquare(f, chessRank), piece);

		return new ChessBoardBuilder(pieces);
	}

}
