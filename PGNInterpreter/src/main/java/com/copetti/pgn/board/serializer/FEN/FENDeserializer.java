package com.copetti.pgn.board.serializer.FEN;

import java.util.HashMap;
import java.util.Map;

import com.copetti.pgn.board.CastleInformation;
import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.board.ChessBoardContext;
import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.board.FullMoveCounter;
import com.copetti.pgn.board.HalfMoveCounter;
import com.copetti.pgn.list.ReverseIterator;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class FENDeserializer {

	public ChessBoard deserialize(String s) {

		String[] partition = s.split(" ");

		Map<ChessSquare, ColoredChessPiece> board = deserializeBoard(partition[0]);
		ChessColor next = deserializeNextToMove(partition[1]);
		CastleInformation castle = deserializeCastleInformation(partition[2]);
		ChessSquare cs = deserializeEnPassantTarget(partition[3]);
		HalfMoveCounter hMove = deserializeHalfMove(partition[4]);
		FullMoveCounter fMove = deserializeFullMove(partition[5]);

		ChessBoardContext ctx = new ChessBoardContext(next, castle, cs, hMove, fMove);
		return new ChessBoard(board, ctx);
	}

	public Map<ChessSquare, ColoredChessPiece> deserializeBoard(String s) {

		Map<ChessSquare, ColoredChessPiece> map = new HashMap<>();

		String[] lines = s.split("/");

		int counter = 0;

		for (ChessRank r : new ReverseIterator<>(ChessRank.values()))
			map.putAll(deserializeRank(lines[counter++], r));

		return map;
	}

	private Map<? extends ChessSquare, ? extends ColoredChessPiece> deserializeRank(String rank, ChessRank r) {

		Map<ChessSquare, ColoredChessPiece> map = new HashMap<>();

		ChessFile current = ChessFile.first();
		Integer skip = 0;

		for (char c : rank.toCharArray()) //
		{
			ColoredChessPiece cp;
			if ((skip = getInteger(c)) != null) //
			{
				while (skip-- > 0)
					current = current.next();
			} //
			else if ((cp = getColoredChessPiece(c)) != null) //
			{
				map.put(new ChessSquare(current, r), cp);
				current = current.next();
			} //
		}

		return map;

	}

	private ColoredChessPiece getColoredChessPiece(char c) {
		ChessColor cc = getColor(c);
		ChessPiece cp = getPiece(c);

		return new ColoredChessPiece(cp, cc);
	}

	private ChessPiece getPiece(char c) {
		return ChessPiece.of(String.valueOf(c).toUpperCase()).get();
	}

	private ChessColor getColor(char c) {

		if (String.valueOf(c).toUpperCase().equals(String.valueOf(c)))
			return ChessColor.WHITE;

		return ChessColor.BLACK;
	}

	private Integer getInteger(char c) {
		try {
			return Integer.parseInt(String.valueOf(c));
		} catch (Exception e) {
			return null;
		}
	}

	public ChessColor deserializeNextToMove(String s) {
		return s.equals("w") ? ChessColor.WHITE : ChessColor.BLACK;
	}

	public CastleInformation deserializeCastleInformation(String s) {

		if (s.equals("-"))
			return CastleInformation.none();

		if (s.contains("[^KQkq]"))
			return null;

		boolean whiteKingCastle = false;
		boolean whiteQueenCastle = false;
		boolean blackKingCastle = false;
		boolean blackQueenCastle = false;

		if (s.contains("K"))
			whiteKingCastle = true;

		if (s.contains("Q"))
			whiteQueenCastle = true;

		if (s.contains("k"))
			blackKingCastle = true;

		if (s.contains("q"))
			blackQueenCastle = true;

		return new CastleInformation(whiteKingCastle, whiteQueenCastle, blackKingCastle, blackQueenCastle);
	}

	public ChessSquare deserializeEnPassantTarget(String s) {

		if (s.equals("-"))
			return null;

		return new ChessSquare(s);
	}

	public HalfMoveCounter deserializeHalfMove(String s) {
		return new HalfMoveCounter(Integer.parseInt(s));
	}

	public FullMoveCounter deserializeFullMove(String s) {
		return new FullMoveCounter(Integer.parseInt(s));
	}
}
