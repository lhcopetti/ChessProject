package com.copetti.pgn.lexical.state;

import java.util.List;
import java.util.Optional;

import com.copetti.pgn.tokenizer.PGNToken;

public class ChessPieceState extends LexicalState {

	protected ChessPieceState(List<PGNToken> tokens) {
		super(tokens);
	}

	@Override
	public Optional<LexicalState> onExecute() {

		PGNToken token = pop();
		System.out.println("Pop token: " + token);

		PGNToken head = peek().get();

		switch (head.getTokenType()) {
		case CHESS_FILE:
			return Optional.of(new DestinationSquareState(tokens));
		default:
			return Optional.empty();
		}
	}

}
