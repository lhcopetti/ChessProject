package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgn.tokenizer.tokens.ChessFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DesambiguateFileState extends LexicalState {

	private @Getter ChessFile file;

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(DesambiguateRankState.class, DestinationSquareState.class, CaptureState.class);
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return Arrays.asList(PGNToken.of(TokenTypes.CHESS_FILE, file.getValue()));
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {

		if (tokens.get(0).getTokenType() != TokenTypes.CHESS_FILE)
			return false;

		file = ChessFile.of(tokens.remove(0).getTokenValue()).get();
		return true;
	}

	@Override
	protected String toStringChild() {
		return file.getValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DesambiguateFileState))
			return false;
		DesambiguateFileState other = (DesambiguateFileState) obj;
		if (file != other.file)
			return false;
		return true;
	}

}
