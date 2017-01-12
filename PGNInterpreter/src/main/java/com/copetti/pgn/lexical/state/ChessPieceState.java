package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgncommon.chess.token.ChessPiece;

public class ChessPieceState extends LexicalState {

	protected ChessPieceState(List<PGNToken> tokens, CommandBuilder command) {
		super(tokens, command);
	}

	public ChessPieceState() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.copetti.pgn.lexical.state.LexicalState#onExecute() Nf3 Ndf3 N1f2
	 * Nd1f2
	 */

	@Override
	public Optional<LexicalState> onExecute() {

		PGNToken token = pop();
		System.out.println("Pop token: " + token);

		if (!secondPeek().isPresent()) {
			System.out.println("Número de tokens não é suficiente. " + tokens);
			return Optional.empty();
		}

		PGNToken head = peek().get();

		switch (head.getTokenType()) {
		case CHESS_FILE:
			command.setPieceType(ChessPiece.of(token.getTokenValue()).get());
			return Optional.of(new DestinationSquareState(tokens, command));
		case CHESS_CAPTURE:
			command.setCapture();
			return Optional.of(new CaptureState(tokens, command));
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(DestinationSquareState.class);
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {

		PGNToken head = tokens.get(0);

		if (head.getTokenType() != TokenTypes.CHESS_PIECE)
			return false;

		consumedTokens.add(tokens.remove(0));
		return true;
	}
}
