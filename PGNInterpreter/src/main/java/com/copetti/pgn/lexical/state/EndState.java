package com.copetti.pgn.lexical.state;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.tokenizer.PGNToken;

public class EndState extends LexicalState {

	public EndState() {
	}

	protected EndState(List<PGNToken> tokens, CommandBuilder command) {
		super(tokens, command);
	}

	@Override
	protected Optional<LexicalState> onExecute() {
		return Optional.empty();
	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Collections.emptyList();
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {
		return true;
	}

	@Override
	public boolean evaluate(Stack<LexicalState> result, List<PGNToken> tokens) throws Exception {

		return tokens.size() == 0;
	}

	@Override
	protected boolean acceptEmptyTokenList() {
		return true;
	}
}
