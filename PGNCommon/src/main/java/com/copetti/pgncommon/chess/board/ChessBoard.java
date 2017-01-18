package com.copetti.pgncommon.chess.board;

import java.util.Map;

import com.copetti.pgncommon.chess.token.ChessFile;
import com.copetti.pgncommon.chess.token.ChessPiece;
import com.copetti.pgncommon.chess.token.ChessRank;

public class ChessBoard {

	private Map<ChessSquare, ChessPiece> pieces;

	private ChessColor nextToPlay;
	private CastleInformation castleInfo;
	private HalfMoveCounter halfMoveCounter;
	private ChessSquare enPassantTarget;
	private FullMoveCounter fullMoveNumber;

	public ChessBoard(Map<ChessSquare, ChessPiece> map) {
		pieces = map;
	}

	public ChessPiece at(int x, int y) {
		return pieces.get(new ChessSquare(x, y));
	}

	public boolean isEmpty() {
		return pieces.isEmpty();
	}

	public ChessPiece at(ChessFile c, ChessRank chessRank) {
		return at(new ChessSquare(c, chessRank));
	}

	public ChessPiece at(ChessSquare chessSquare) {
		return pieces.get(chessSquare);
	}

}
