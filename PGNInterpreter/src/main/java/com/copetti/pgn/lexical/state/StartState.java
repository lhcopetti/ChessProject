package com.copetti.pgn.lexical.state;

import java.util.List;
import java.util.Optional;

import com.copetti.pgn.tokenizer.PGNToken;

public class StartState extends LexicalState {

	public StartState(List<PGNToken> tokens) {
		super(tokens);
	}

	public Optional<LexicalState> onExecute() {

		switch (peek().get().getTokenType()) {
		case CHESS_PIECE:
			return Optional.of(new ChessPieceState(tokens));

		default:
			return Optional.empty();

		}
	}
}