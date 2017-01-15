package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgncommon.chess.token.ChessBigO;
import com.copetti.pgncommon.chess.token.ChessDash;

import lombok.AllArgsConstructor;

public class CastleLongState extends CastleState {

	public CastleLongState() {
		super(Arrays.asList(TokenTypes.CHESS_BIG_O, TokenTypes.CHESS_DASH, TokenTypes.CHESS_BIG_O,
				TokenTypes.CHESS_DASH, TokenTypes.CHESS_BIG_O));
	}

	@Override
	protected String toStringChild() {
		return "O-O-O";
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof CastleLongState;
	}

}
