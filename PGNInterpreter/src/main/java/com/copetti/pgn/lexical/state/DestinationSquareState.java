package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;

public class DestinationSquareState extends LexicalState {

	protected DestinationSquareState(List<PGNToken> tokens) {
		super(tokens);
	}

	@Override
	protected Optional<LexicalState> onExecute() {

		if (tokenSize() < 2) {
			System.out.println("Número de tokens insuficientes");
			return Optional.empty();
		}

		PGNToken file = pop();
		PGNToken rank = pop();

		if (file.getTokenType() != TokenTypes.CHESS_FILE) {
			System.out.println("Token do tipo errado recebido. Esperado: " + TokenTypes.CHESS_FILE + " Recebido: "
					+ file.getTokenType());
			return Optional.empty();
		}

		if (rank.getTokenType() != TokenTypes.CHESS_RANK) {
			System.out.println("Token do tipo errado recebido. Esperado: " + TokenTypes.CHESS_RANK + " Recebido: "
					+ rank.getTokenType());
			return Optional.empty();
		}

		System.out.println("Destination Square equals to: " + file.getTokenValue() + rank.getTokenValue());
		return Optional.of(new EndState(tokens));
	}

	@Override
	protected int minimumTokenSize() {
		return 2;
	}

	@Override
	protected List<TokenTypes> enforceTokenOrder() {

		return Collections.unmodifiableList(Arrays.asList(TokenTypes.CHESS_FILE, TokenTypes.CHESS_RANK));
	}

}
