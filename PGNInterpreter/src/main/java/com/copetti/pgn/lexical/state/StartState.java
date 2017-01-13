package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;

public class StartState extends LexicalState {

	public StartState() {
	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(ChessPieceState.class, DestinationSquareState.class, PawnCaptureState.class);
	}

	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {
		return true;
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return Collections.emptyList();
	}

	@Override
	protected String toStringChild() {
		return "";
	}
}