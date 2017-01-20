package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessPromotion;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class PromotionState extends LexicalState {

	private @Getter ChessPiece piece;

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(EndState.class, CheckState.class, CheckmateState.class);
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return Arrays.asList(PGNToken.of(TokenTypes.CHESS_PROMOTION, ChessPromotion.PROMOTION_SIGN.getValue()),
				PGNToken.of(TokenTypes.CHESS_PIECE, piece.getValue()));
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {

		if (tokens.size() < 2)
			return false;

		PGNToken head = tokens.get(0);
		PGNToken sHead = tokens.get(1);

		if (head.getTokenType() != TokenTypes.CHESS_PROMOTION || sHead.getTokenType() != TokenTypes.CHESS_PIECE)
			return false;

		tokens.remove(0);
		piece = ChessPiece.of(tokens.remove(0).getTokenValue()).get();
		return true;
	}

	@Override
	protected String toStringChild() {
		return ChessPromotion.PROMOTION_SIGN + " " + piece.getValue();
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
		if (!(obj instanceof PromotionState))
			return false;
		PromotionState other = (PromotionState) obj;
		if (piece != other.piece)
			return false;
		return true;
	}

}
