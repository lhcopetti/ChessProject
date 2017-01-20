package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.lexical.state.container.ChessPieceContainer;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PawnFileState extends LexicalState implements ChessPieceContainer {

	private ChessFile file;

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(CaptureState.class, DestinationSquareState.class);
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof PawnFileState))
			return false;

		PawnFileState other = (PawnFileState) obj;

		if (file == null && other.file != null)
			return false;

		if (file != null && !file.equals(other.file))
			return false;

		return true;
	}

	@Override
	public ChessPiece getPiece() {
		return ChessPiece.PAWN;
	}

}
