package com.copetti.pgn.lexical.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;

import lombok.AccessLevel;
import lombok.Getter;

public abstract class LexicalState {

	protected List<PGNToken> tokens;

	protected List<PGNToken> consumedTokens;

	@Getter(value = AccessLevel.PUBLIC)
	protected CommandBuilder command;

	protected LexicalState(List<PGNToken> tokens, CommandBuilder command) {
		this.tokens = tokens;
		this.command = command;

	}

	public LexicalState() {
		consumedTokens = new ArrayList<>();
	}

	public Optional<LexicalState> execute() {
		System.out.println("Executing " + getClass().getSimpleName() + ". Tokens: " + tokens);

		if (!checkMinimumSize())
			return Optional.empty();

		if (!checkEnforceTokenOrder())
			return Optional.empty();

		return onExecute();
	}

	public abstract List<Class<? extends LexicalState>> getSuccessors();

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

	public Optional<PGNToken> secondPeek() {

		return tokens.size() < 2 ? Optional.empty() : Optional.of(tokens.get(1));
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

	public boolean evaluate(Stack<LexicalState> result, List<PGNToken> tokens) throws Exception {

		for (Class<? extends LexicalState> clazz : getSuccessors()) {

			LexicalState ls = clazz.newInstance();

			// if (clazz == EndState.class && tokens.isEmpty()) {
			// result.push(ls);
			// return true;
			// }

			if (!ls.consume(result, tokens))
				continue;

			result.push(ls);

			// if (tokens.isEmpty() && ls.getSuccessors().isEmpty())
			// return true;
			//
			// if (tokens.isEmpty() || ls.getSuccessors().isEmpty())
			// return false;

			if (ls.evaluate(result, tokens))
				return true;

			// Restore
			result.pop();
			tokens.addAll(ls.getConsumedTokens());

		}

		return false;
	}

	private Collection<? extends PGNToken> getConsumedTokens() {
		return consumedTokens;
	}

	protected boolean acceptEmptyTokenList() {
		return false;
	}

	protected abstract boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens);

	protected final boolean consume(Stack<LexicalState> result, List<PGNToken> tokens) {

		if (tokens.isEmpty() && !acceptEmptyTokenList())
			return false;

		return doConsume(result, tokens);
	}

}
