package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgn.tokenizer.tokens.ChessCheck;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckState extends LexicalState {

	public CheckState() {
		this(ChessCheck.CHESS_CHECK);
	}

	private @Getter ChessCheck check;

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(EndState.class);
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return Arrays.asList(PGNToken.of(TokenTypes.CHESS_CHECK, check.getValue()));
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {
		if (tokens.get(0).getTokenType() != TokenTypes.CHESS_CHECK)
			return false;

		check = ChessCheck.of(tokens.remove(0).getTokenValue()).get();
		return true;
	}

	@Override
	protected String toStringChild() {
		return check.getValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((check == null) ? 0 : check.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CheckState))
			return false;
		CheckState other = (CheckState) obj;
		if (check != other.check)
			return false;
		return true;
	}

}
