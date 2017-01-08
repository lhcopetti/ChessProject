package com.copetti.pgn.lexical.state;

import java.util.List;
import java.util.Optional;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;

import lombok.AccessLevel;
import lombok.Getter;

public abstract class LexicalState {

	protected List<PGNToken> tokens;

	@Getter(value = AccessLevel.PUBLIC)
	protected CommandBuilder command;

	protected LexicalState(List<PGNToken> tokens, CommandBuilder command) {
		this.tokens = tokens;
		this.command = command;
	}

	public Optional<LexicalState> execute() {
		System.out.println("Executing " + getClass().getSimpleName() + ". Tokens: " + tokens);

		if (!checkMinimumSize())
			return Optional.empty();

		if (!checkEnforceTokenOrder())
			return Optional.empty();

		return onExecute();
	}

	private boolean checkEnforceTokenOrder() {

		List<TokenTypes> list = enforceTokenOrder();

		if (list == null)
			return true;

		if (list.size() > tokens.size()) {
			System.out.println("Tamanho insuficiente de tokens");
			return false;
		}

		for (int i = 0; i < list.size(); ++i)
			if (list.get(i) != tokens.get(i).getTokenType()) {
				System.out.println("Tokens não possuem o tipo correto. Esperado: " + list + " Recebido: " + tokens);
				return false;
			}

		return true;
	}

	private boolean checkMinimumSize() {

		if (tokenSize() < minimumTokenSize()) {
			System.out.println("Quantidade insuficiente de tokens");
			return false;
		}

		return true;
	}

	protected abstract Optional<LexicalState> onExecute();

	public Optional<PGNToken> peek() {

		return tokens.isEmpty() ? Optional.empty() : Optional.of(tokens.get(0));
	}

	public PGNToken pop() {
		return tokens.remove(0);
	}

	protected int tokenSize() {
		return tokens.size();
	}

	protected int minimumTokenSize() {
		return 0;
	}

	protected List<TokenTypes> enforceTokenOrder() {
		return null;
	}

}
