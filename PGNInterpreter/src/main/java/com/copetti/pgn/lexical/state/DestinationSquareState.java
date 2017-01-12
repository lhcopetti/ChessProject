package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgncommon.chess.board.ChessSquare;
import com.copetti.pgncommon.chess.token.ChessFile;
import com.copetti.pgncommon.chess.token.ChessRank;

public class DestinationSquareState extends LexicalState {

	public DestinationSquareState() {

	}

	protected DestinationSquareState(List<PGNToken> tokens, CommandBuilder command) {
		super(tokens, command);
	}

	@Override
	protected Optional<LexicalState> onExecute() {

		PGNToken file = pop();
		PGNToken rank = pop();

		ChessFile f = ChessFile.of(file.getTokenValue()).get();
		ChessRank r = ChessRank.of(rank.getTokenValue()).get();
		command.setDestinationSquare(new ChessSquare(f, r));

		System.out.println("Destination Square equals to: " + file.getTokenValue() + rank.getTokenValue());
		return Optional.of(new EndState(tokens, command));
	}

	@Override
	protected int minimumTokenSize() {
		return 2;
	}

	@Override
	protected List<TokenTypes> enforceTokenOrder() {

		return Collections.unmodifiableList(Arrays.asList(TokenTypes.CHESS_FILE, TokenTypes.CHESS_RANK));
	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(EndState.class);
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {
		if (tokens.size() < 2)
			return false;

		PGNToken file = tokens.remove(0);
		PGNToken rank = tokens.remove(0);

		if (file.getTokenType() != TokenTypes.CHESS_FILE)
			return false;

		if (rank.getTokenType() != TokenTypes.CHESS_RANK)
			return false;

		consumedTokens.add(file);
		consumedTokens.add(rank);
		return true;
	}

}
