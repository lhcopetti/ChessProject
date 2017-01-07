package com.copetti.pgn.lexical.state;

import java.util.List;

import com.copetti.pgn.tokenizer.PGNToken;

public class LexicalState {

	private List<PGNToken> tokens;

	public LexicalState(List<PGNToken> tokens) {
		this.tokens = tokens;
	}

}
