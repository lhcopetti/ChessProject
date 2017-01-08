package com.copetti.pgn.lexical.state;

import java.util.List;
import java.util.Optional;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgncommon.chess.token.ChessPiece;

public class ChessPieceState extends LexicalState {

	protected ChessPieceState(List<PGNToken> tokens, CommandBuilder command) {
		super(tokens, command);
	}

	@Override
	public Optional<LexicalState> onExecute() {

		PGNToken token = pop();
		System.out.println("Pop token: " + token);

		PGNToken head = peek().get();

		switch (head.getTokenType()) {
		case CHESS_FILE:
			command.setPieceType(ChessPiece.of(token.getTokenValue()).get());
			return Optional.of(new DestinationSquareState(tokens, command));
		default:
			return Optional.empty();
		}
	}

}
