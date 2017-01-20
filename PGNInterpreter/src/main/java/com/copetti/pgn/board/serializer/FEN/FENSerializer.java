package com.copetti.pgn.board.serializer.FEN;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.copetti.pgn.board.CastleInformation;
import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.FullMoveCounter;
import com.copetti.pgn.board.HalfMoveCounter;
import com.copetti.pgn.board.serializer.ChessBoardSerializer;
import com.copetti.pgn.list.ReverseIterator;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class FENSerializer implements ChessBoardSerializer {

	@Override
	public String serialize(ChessBoard b) {
		return null;
	}

	public String serialize(CastleInformation castleInformation) {

		String ret = "";

		if (castleInformation.isWhiteKingCastle())
			ret += "K";
		if (castleInformation.isWhiteQueenCastle())
			ret += "Q";

		if (castleInformation.isBlackKingCastle())
			ret += "k";
		if (castleInformation.isBlackQueenCastle())
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

		List<String> result = new ArrayList<>();

		for (ChessRank r : new ReverseIterator<>(ChessRank.values()))
			result.add(serializeRank(map, r));

		return String.join("/", result);
	}

	private String serializeRank(Map<ChessSquare, ColoredChessPiece> map, ChessRank r) {

		String line = "";
		int counter = 0;

		for (ChessFile f : ChessFile.values()) {
			ColoredChessPiece cp = map.get(new ChessSquare(f, r));

			if (cp == null) {
				counter++;
				continue;
			}

			line += counter > 0 ? counter : "";
			line += getFENPieceRepresentation(cp);
			counter = 0;
		}

		if (counter > 0)
			line += counter;

		return line;
	}

	private String getFENPieceRepresentation(ColoredChessPiece cp) {

		Function<String, String> f = cp.getColor() == ChessColor.WHITE ? String::toUpperCase : String::toLowerCase;
		return f.apply(cp.getPiece().getPgnNotation());
	}

	// private ChessPiece getPiece(char c) {
	// return ChessPiece.of(String.valueOf(c).toUpperCase()).get();
	// }
	//
	// private ChessColor getColor(char c) {
	//
	// if (String.valueOf(c).toUpperCase().equals(String.valueOf(c)))
	// return ChessColor.WHITE;
	//
	// return ChessColor.BLACK;
	// }

}
