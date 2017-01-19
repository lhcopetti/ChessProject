package com.copetti.pgn.board.serializer.FEN;

import java.util.Map;

import com.copetti.pgn.board.CastleInformation;
import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.FullMoveCounter;
import com.copetti.pgn.board.HalfMoveCounter;
import com.copetti.pgn.board.serializer.ChessBoardSerializer;

public class FENSerializer implements ChessBoardSerializer {

	@Override
	public String serialize(ChessBoard b) {
		return null;
	}

	protected String serialize(CastleInformation castleInformation) {

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

	protected String serialize(HalfMoveCounter halfMoveCounter) {
		return String.valueOf(halfMoveCounter.getCounter());
	}

	protected String serialize(FullMoveCounter fullMoveCounter) {
		return String.valueOf(fullMoveCounter.getCounter());
	}

	protected String serialize(ChessSquare enPassant) {
		return enPassant.toString();
	}

	protected String serialize(ChessColor color) {
		return color == ChessColor.BLACK ? "b" : "w";
	}

	protected String serialize(Map<ChessSquare, ColoredChessPiece> map) {
		return "";
	}

}
