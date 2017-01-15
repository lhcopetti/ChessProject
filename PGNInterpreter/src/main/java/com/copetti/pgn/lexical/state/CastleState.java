package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgncommon.chess.token.ChessBigO;
import com.copetti.pgncommon.chess.token.ChessDash;

public abstract class CastleState extends LexicalState {

	private List<TokenTypes> sequence;

	public CastleState(List<TokenTypes> tokens) {
		this.sequence = tokens;
	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(EndState.class, CheckState.class, CheckmateState.class);
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return sequence //
				.stream() //
				.map((x) -> getToken(x)) //
				.collect(Collectors.toList());
	}

	private PGNToken getToken(TokenTypes type) {
		return PGNToken.of(type,
				type == TokenTypes.CHESS_DASH ? ChessDash.DASH_SIGN.getValue() : ChessBigO.CHESS_BIG_O.getValue());
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {

		if (tokens.size() < sequence.size())
			return false;

		for (int i = 0; i < sequence.size(); i++)
			if (tokens.get(i).getTokenType() != sequence.get(i))
				return false;

		for (int i = 0; i < sequence.size(); i++)
			tokens.remove(0);

		return true;

	}

}
