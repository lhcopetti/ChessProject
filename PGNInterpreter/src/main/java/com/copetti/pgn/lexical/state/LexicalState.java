package com.copetti.pgn.lexical.state;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;

public abstract class LexicalState {

	public abstract List<Class<? extends LexicalState>> getSuccessors();

	public boolean evaluate(Stack<LexicalState> result, List<PGNToken> tokens) throws Exception {

		for (Class<? extends LexicalState> clazz : getSuccessors()) {

			LexicalState ls = clazz.newInstance();

			if (!ls.consume(result, tokens))
				continue;

			result.push(ls);

			if (ls.evaluate(result, tokens))
				return true;

			// Restore
			result.pop();
			tokens.addAll(ls.getConsumedTokens());

		}

		return false;
	}

	public abstract Collection<? extends PGNToken> getConsumedTokens();

	protected boolean acceptEmptyTokenList() {
		return false;
	}

	protected abstract boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens);

	protected final boolean consume(Stack<LexicalState> result, List<PGNToken> tokens) {

		if (tokens.isEmpty() && !acceptEmptyTokenList())
			return false;

		return doConsume(result, tokens);
	}

	@Override
	public final String toString() {
		return getClass().getSimpleName() + " " + toStringChild();
	}

	protected abstract String toStringChild();

}
