package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DesambiguateRankState extends LexicalState {

	private @Getter ChessRank rank;

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(DestinationSquareState.class, CaptureState.class);
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return Arrays.asList(PGNToken.of(TokenTypes.CHESS_RANK, rank.getValue()));
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {

		if (tokens.get(0).getTokenType() != TokenTypes.CHESS_RANK)
			return false;

		rank = ChessRank.of(tokens.remove(0).getTokenValue()).get();
		return true;
	}

	@Override
	protected String toStringChild() {
		return rank.getValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DesambiguateRankState))
			return false;
		DesambiguateRankState other = (DesambiguateRankState) obj;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		return true;
	}

}
