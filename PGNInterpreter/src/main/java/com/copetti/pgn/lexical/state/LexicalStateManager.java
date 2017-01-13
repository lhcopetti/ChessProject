package com.copetti.pgn.lexical.state;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.command.PGNCommand;
import com.copetti.pgn.tokenizer.PGNToken;

import lombok.Getter;

public class LexicalStateManager {

	private @Getter PGNCommand command;

	private LexicalState currentState;

	public LexicalStateManager() {
		currentState = null;
	}

	public List<LexicalState> execute(Class<? extends LexicalState> start, List<PGNToken> tokens) throws Exception {

		Stack<LexicalState> result = new Stack<>();

		LexicalState state = start.newInstance();

		if (state.evaluate(result, tokens)) {
			result.add(0, state);
			return result;
		}

		return null;
	}

//	public boolean execute(LexicalState startState) {
//
//		currentState = startState;
//
//		while (true) {
//			Optional<LexicalState> nextState = currentState.execute();
//
//			if (!nextState.isPresent())
//				break;
//
//			currentState = nextState.get();
//		}
//
//		if (!currentState.getClass().equals(EndState.class)) {
//			System.out.println("Término abrupto de leitura de máquina de estados");
//			return false;
//		}
//
//		CommandBuilder builder = currentState.getCommand();
//
//		if ((command = builder.build()) == null) {
//			System.out.println("Falha ao construir comando!");
//			return false;
//		}
//
//		return true;
//	}

}
