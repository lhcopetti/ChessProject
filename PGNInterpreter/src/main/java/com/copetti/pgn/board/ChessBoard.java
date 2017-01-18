package com.copetti.pgn.board;

import java.util.Map;

import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ChessBoard {

	private Map<ChessSquare, ColoredChessPiece> pieces;

	private @Getter ChessColor nextToPlay;
	private @Getter CastleInformation castleInfo;
	private @Getter HalfMoveCounter halfMoveCounter;
	private @Getter ChessSquare enPassantTarget;
	private @Getter FullMoveCounter fullMoveNumber;

	public ColoredChessPiece at(int x, int y) {
		return pieces.get(new ChessSquare(x, y));
	}

	public boolean isEmpty() {
		return pieces.isEmpty();
	}

	public ColoredChessPiece at(ChessFile c, ChessRank chessRank) {
		return at(new ChessSquare(c, chessRank));
	}

	public ColoredChessPiece at(ChessSquare chessSquare) {
		return pieces.get(chessSquare);
	}

	public ChessBoard(Map<ChessSquare, ColoredChessPiece> piece) {
		throw new UnsupportedOperationException();
	}

}
