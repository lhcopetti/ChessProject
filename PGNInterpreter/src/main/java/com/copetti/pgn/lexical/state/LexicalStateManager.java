package com.copetti.pgn.lexical.state;

import java.util.Optional;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.command.PGNCommand;

import lombok.Getter;

public class LexicalStateManager {

	private @Getter PGNCommand command;

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

		if (!currentState.getClass().equals(EndState.class)) {
			System.out.println("Término abrupto de leitura de máquina de estados");
			return false;
		}

		CommandBuilder builder = currentState.getCommand();

		if ((command = builder.build()) == null) {
			System.out.println("Falha ao construir comando!");
			return false;
		}

		return true;
	}

}
