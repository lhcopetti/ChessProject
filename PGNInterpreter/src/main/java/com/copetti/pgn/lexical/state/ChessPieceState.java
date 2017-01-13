package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;

public class ChessPieceState extends LexicalState {

	private PGNToken chessPiece;

	public ChessPieceState() {

	}

	public ChessPieceState(PGNToken token) {
		this.chessPiece = token;
	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(DestinationSquareState.class, CaptureState.class);
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {

		PGNToken head = tokens.get(0);

		if (head.getTokenType() != TokenTypes.CHESS_PIECE)
			return false;

		tokens.remove(0);
		chessPiece = head;
		return true;
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return Arrays.asList(chessPiece);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chessPiece == null) ? 0 : chessPiece.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ChessPieceState))
			return false;
		ChessPieceState other = (ChessPieceState) obj;
		if (chessPiece == null) {
			if (other.chessPiece != null)
				return false;
		} else if (!chessPiece.equals(other.chessPiece))
			return false;
		return true;
	}

	@Override
	public String toStringChild() {
		return chessPiece.getTokenValue();
	}
}
