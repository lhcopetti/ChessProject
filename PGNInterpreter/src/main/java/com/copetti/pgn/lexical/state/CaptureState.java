package com.copetti.pgn.lexical.state;

import java.util.List;
import java.util.Optional;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.tokenizer.PGNToken;

public class CaptureState extends LexicalState {

	protected CaptureState(List<PGNToken> tokens, CommandBuilder command) {
		super(tokens, command);
	}

	@Override
	protected Optional<LexicalState> onExecute() {

		PGNToken token = pop();
		System.out.println("Pop token: " + token);

		PGNToken head = peek().get();

		switch (head.getTokenType()) {
		case CHESS_FILE:
			return Optional.of(new DestinationSquareState(tokens, command));
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		// TODO Auto-generated method stub
		return null;
	}

}
