package com.copetti.pgn.lexical.state;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;

public class EndState extends LexicalState {

	public EndState() {
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

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return Collections.emptyList();
	}

	@Override
	protected String toStringChild() {
		return "";
	}
}
