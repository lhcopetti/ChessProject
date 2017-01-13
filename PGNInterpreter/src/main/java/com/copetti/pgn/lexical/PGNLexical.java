package com.copetti.pgn.lexical;

import java.util.Collections;
import java.util.List;

import com.copetti.pgn.command.PGNCommand;
import com.copetti.pgn.lexical.state.LexicalState;
import com.copetti.pgn.lexical.state.LexicalStateManager;
import com.copetti.pgn.lexical.state.StartState;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.PGNTokenizer;

import lombok.Getter;

public class PGNLexical {

	private @Getter PGNCommand command;

	public List<LexicalState> execute(String rawInput) {

		PGNTokenizer tokenizer = new PGNTokenizer();
		List<PGNToken> tokens = tokenizer.tokenize(rawInput);

		return execute(tokens);
	}

	public List<LexicalState> execute(List<PGNToken> tokens) {

		if (tokens.isEmpty())
			return Collections.emptyList();

		List<LexicalState> states = null;

		try {
			LexicalStateManager manager = new LexicalStateManager();
			states = manager.execute(StartState.class, tokens);

		} catch (Exception e) {
			System.err.println("Exceção não tratada: " + e.getMessage());
			return Collections.emptyList();
		}

		if (null == states)
			return null;

		if (states.size() < 2) {
			System.out.println("Tamanho dos estados léxicos é menor que o mínimo para conter [Start, End]");
			return states;
		}

		states.remove(0);
		states.remove(states.size() - 1);

		System.out.println("Return is: " + states);
		return states;
	}

}
