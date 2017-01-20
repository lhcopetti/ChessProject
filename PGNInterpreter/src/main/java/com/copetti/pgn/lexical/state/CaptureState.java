package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgn.tokenizer.tokens.ChessCapture;

public class CaptureState extends LexicalState {

	public CaptureState() {

	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(DestinationSquareState.class);
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {
		if (tokens.get(0).getTokenType() != TokenTypes.CHESS_CAPTURE)
			return false;

		tokens.remove(0);
		return true;
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return Arrays.asList(PGNToken.of(TokenTypes.CHESS_CAPTURE, ChessCapture.CAPTURE_SIGN.getValue()));
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof CaptureState;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public String toStringChild() {
		return ChessCapture.CAPTURE_SIGN.getValue();
	}

}
