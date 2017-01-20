package com.copetti.pgn.lexical.state;

import java.util.Arrays;

import com.copetti.pgn.tokenizer.TokenTypes;

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
