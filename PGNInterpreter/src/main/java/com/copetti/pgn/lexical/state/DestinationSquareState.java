package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgncommon.chess.token.ChessFile;
import com.copetti.pgncommon.chess.token.ChessRank;

public class DestinationSquareState extends LexicalState {

	private ChessFile file;
	private ChessRank rank;

	public DestinationSquareState() {

	}

	public DestinationSquareState(String destSquare) {

		this.file = ChessFile.of(destSquare.substring(0, 1)).get();
		this.rank = ChessRank.of(destSquare.substring(1)).get();
	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(EndState.class);
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {
		if (tokens.size() < 2)
			return false;

		if (tokens.get(0).getTokenType() != TokenTypes.CHESS_FILE)
			return false;

		if (tokens.get(1).getTokenType() != TokenTypes.CHESS_RANK)
			return false;

		file = ChessFile.of(tokens.remove(0).getTokenValue()).get();
		rank = ChessRank.of(tokens.remove(0).getTokenValue()).get();
		return true;
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {

		return Arrays.asList( //
				PGNToken.of(TokenTypes.CHESS_FILE, file.getValue()), //
				PGNToken.of(TokenTypes.CHESS_FILE, rank.getValue()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DestinationSquareState))
			return false;
		DestinationSquareState other = (DestinationSquareState) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		return true;
	}

	@Override
	public String toStringChild() {
		return file.toString() + rank.toString();
	}
}
