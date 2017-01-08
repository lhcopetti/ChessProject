package com.copetti.pgn.lexical.state;

import java.util.List;
import java.util.Optional;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.tokenizer.PGNToken;

public class EndState extends LexicalState {

	protected EndState(List<PGNToken> tokens, CommandBuilder command) {
		super(tokens, command);
	}

	@Override
	protected Optional<LexicalState> onExecute() {
		return Optional.empty();
	}

}
