package com.copetti.pgn.lexical.state;

import java.util.List;
import java.util.Optional;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;

public abstract class LexicalState {

	protected List<PGNToken> tokens;

	protected LexicalState(List<PGNToken> tokens) {
		this.tokens = tokens;
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
			if (list.get(0) != tokens.get(0).getTokenType()) {
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
