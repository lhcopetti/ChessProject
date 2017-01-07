package com.copetti.pgn.lexical;

import java.util.List;

import com.copetti.pgn.lexical.state.LexicalStateManager;
import com.copetti.pgn.lexical.state.StartState;
import com.copetti.pgn.tokenizer.PGNToken;

public class PGNLexical {

	public boolean execute(List<PGNToken> tokens) {

		if (tokens.isEmpty())
			return false;
		
		LexicalStateManager manager = new LexicalStateManager();
		
		if (!manager.execute(new StartState(tokens)))
			return false;

		return true;

	}

}
