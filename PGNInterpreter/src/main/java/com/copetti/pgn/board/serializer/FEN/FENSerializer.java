package com.copetti.pgn.board.serializer.FEN;

import com.copetti.pgn.board.CastleInformation;
import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.FullMoveCounter;
import com.copetti.pgn.board.HalfMoveCounter;
import com.copetti.pgn.board.serializer.ChessBoardSerializer;

public class FENSerializer implements ChessBoardSerializer {

	@Override
	public String serialize(ChessBoard b) {
		return null;
	}

	public String serialize(CastleInformation castleInformation) {

		String ret = "";

		if (castleInformation.isKingCastleAvaiableForWhite())
			ret += "K";
		if (castleInformation.isQueenCastleAvaiableForWhite())
			ret += "Q";

		if (castleInformation.isKingCastleAvailableForBlack())
			ret += "k";
		if (castleInformation.isQueenCastleAvailableForBlack())
			ret += "q";

		return ret.isEmpty() ? "-" : ret;
	}

	public String serialize(HalfMoveCounter halfMoveCounter) {
		return String.valueOf(halfMoveCounter.getCounter());
	}

	public String serialize(FullMoveCounter fullMoveCounter) {
		return String.valueOf(fullMoveCounter.getCounter());
	}

	public String serialize(ChessSquare enPassant) {
		return enPassant.toString();
	}

	public String serialize(ChessColor color) {
		return color == ChessColor.BLACK ? "b" : "w";
	}
}
