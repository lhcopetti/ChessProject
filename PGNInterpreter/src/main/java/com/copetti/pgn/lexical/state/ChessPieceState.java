package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.lexical.state.container.ChessPieceContainer;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ChessPieceState extends LexicalState implements ChessPieceContainer {

	private @Getter ChessPiece piece;

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList( //
				DestinationSquareState.class, //
				CaptureState.class, //
				DesambiguateRankState.class, //
				DesambiguateFileState.class);
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {

		PGNToken head = tokens.get(0);

		if (head.getTokenType() != TokenTypes.CHESS_PIECE)
			return false;

		tokens.remove(0);
		piece = ChessPiece.of(head.getTokenValue()).get();
		return true;
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return Arrays.asList(PGNToken.of(TokenTypes.CHESS_PIECE, piece.getValue()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((piece == null) ? 0 : piece.hashCode());
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
		if (piece == null) {
			if (other.piece != null)
				return false;
		} else if (!piece.equals(other.piece))
			return false;
		return true;
	}

	@Override
	public String toStringChild() {
		return piece.getValue();
	}
}
