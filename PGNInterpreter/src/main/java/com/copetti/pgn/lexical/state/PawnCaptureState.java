package com.copetti.pgn.lexical.state;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgncommon.chess.token.ChessFile;
import com.copetti.pgncommon.chess.token.ChessPiece;

public class PawnCaptureState extends LexicalState {

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return null;
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {
		return false;
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return null;
	}

	@Override
	protected String toStringChild() {
		// TODO Auto-generated method stub
		return null;
	}

}
