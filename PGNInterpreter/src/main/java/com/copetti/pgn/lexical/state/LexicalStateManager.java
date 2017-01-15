package com.copetti.pgn.lexical.state;

import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LexicalStateManager {

	public List<LexicalState> execute(Class<? extends LexicalState> start, List<PGNToken> tokens) throws Exception {

		Stack<LexicalState> result = new Stack<>();

		LexicalState state = start.newInstance();

		if (state.evaluate(result, tokens)) {
			result.add(0, state);
			return result;
		}

		return null;
	}
}
