package com.copetti.pgn.board.factory;

import java.util.HashMap;
import java.util.Map;

import com.copetti.pgn.board.ChessColor;
import com.copetti.pgn.board.ChessSquare;
import com.copetti.pgn.board.ColoredChessPiece;
import com.copetti.pgn.list.ChessSquareIterator;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class VisualChessBoardFactory {

	public Map<ChessSquare, ColoredChessPiece> newBoard(String board) {

		board = board.replace("[^RrNnBbKkQqPp-]", "");

		if (board.length() != 64)
			return null;

		return doNewBoard(board);
	}

	private Map<ChessSquare, ColoredChessPiece> doNewBoard(String board) {

		Map<ChessSquare, ColoredChessPiece> map = new HashMap<>();
		char[] array = board.toCharArray();
		int counter = 0;

		for (ChessSquare cs : new ChessSquareIterator()) {
			ColoredChessPiece c = getValue(array[counter]);
			if (null != c)
				map.put(cs, c);
			counter++;
		}

		return map;
	}

	private ColoredChessPiece getValue(char c) {

		if (c == '-')
			return null;

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
}
