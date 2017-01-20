package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgn.tokenizer.tokens.ChessCheckMate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CheckmateState extends LexicalState {

	private @Getter ChessCheckMate mate;

	public CheckmateState() {
		this(ChessCheckMate.CHESS_MATE);
	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(EndState.class);
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return Arrays.asList(PGNToken.of(TokenTypes.CHESS_CHECKMATE, ChessCheckMate.CHESS_MATE.getValue()));
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {

		if (tokens.get(0).getTokenType() != TokenTypes.CHESS_CHECKMATE)
			return false;

		mate = ChessCheckMate.of(tokens.remove(0).getTokenValue()).get();
		return true;
	}

	@Override
	protected String toStringChild() {
		return mate.getValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mate == null) ? 0 : mate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CheckmateState))
			return false;
		CheckmateState other = (CheckmateState) obj;
		if (mate != other.mate)
			return false;
		return true;
	}

}
