package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;

public class StartState extends LexicalState {

	public StartState(List<PGNToken> tokens) {
		super(tokens, new CommandBuilder());
	}

	public StartState() {

	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.copetti.pgn.lexical.state.LexicalState#onExecute()
	 * 
	 * 
	 * CHESS_PIECE_STATE
	 * 
	 * DESTINATION_SQUARE_STATE
	 * 
	 * PAWN_CAPTURE_STATE
	 * 
	 */

	public Optional<LexicalState> onExecute() {

		TokenTypes tokenType = peek().get().getTokenType();

		if (tokenType == TokenTypes.CHESS_PIECE)
			return Optional.of(new ChessPieceState(tokens, command));

		if (!secondPeek().isPresent())
			return Optional.empty();

		switch (secondPeek().get().getTokenType()) {
		case CHESS_RANK:
			return Optional.of(new DestinationSquareState(tokens, command));
		case CHESS_CAPTURE:
			return Optional.of(new PawnCaptureState(tokens, command));
		default:
			return Optional.empty();
		}

	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(ChessPieceState.class, DestinationSquareState.class, PawnCaptureState.class);
	}

	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {
		return true;
	}
}