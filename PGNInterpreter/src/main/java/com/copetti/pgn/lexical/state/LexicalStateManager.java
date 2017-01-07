package com.copetti.pgn.lexical.state;

import java.util.Optional;

public class LexicalStateManager {

	private LexicalState currentState;

	public LexicalStateManager() {
		currentState = null;
	}

	public boolean execute(StartState startState) {

		currentState = startState;

		while (true) {
			Optional<LexicalState> nextState = currentState.execute();

			if (!nextState.isPresent())
				break;

			currentState = nextState.get();
		}

		if (!currentState.getClass().equals(EndState.class))
		{
			System.out.println("T�rmino abrupto de leitura de m�quina de estados");
			return false;
		}
		
		return true;
	}

}
