package com.copetti.pgn.lexical.state;

import java.util.Arrays;

import com.copetti.pgn.tokenizer.TokenTypes;

public class CastleShortState extends CastleState {

	public CastleShortState() {
		super(Arrays.asList(TokenTypes.CHESS_BIG_O, TokenTypes.CHESS_DASH, TokenTypes.CHESS_BIG_O));
	}

	@Override
	protected String toStringChild() {
		return "O-O";
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof CastleShortState;
	}

}
