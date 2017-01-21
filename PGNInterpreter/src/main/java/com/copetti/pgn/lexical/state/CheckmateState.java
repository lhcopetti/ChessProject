package com.copetti.pgn.lexical.state;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.copetti.pgn.command.ChessCommand.CheckFlag;
import com.copetti.pgn.lexical.state.container.CheckFlagContainer;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.TokenTypes;
import com.copetti.pgn.tokenizer.tokens.ChessCheckMate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CheckmateState extends LexicalState implements CheckFlagContainer {

	private @Getter ChessCheckMate mate;

	public CheckmateState() {
		this(ChessCheckMate.CHESS_MATE);
	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		return Arrays.asList(EndState.class);
	}

	@Override
	public Collection<? extends PGNToken> getConsumedTokens() {
		return Arrays.asList(PGNToken.of(TokenTypes.CHESS_CHECKMATE, ChessCheckMate.CHESS_MATE.getValue()));
	}

	@Override
	protected boolean doConsume(Stack<LexicalState> result, List<PGNToken> tokens) {

		if (tokens.get(0).getTokenType() != TokenTypes.CHESS_CHECKMATE)
			return false;

		mate = ChessCheckMate.of(tokens.remove(0).getTokenValue()).get();
		return true;
	}

	@Override
	protected String toStringChild() {
		return mate.getValue();
	}

	@Override
	public CheckFlag getFlag() {
		return CheckFlag.FLAG_MATE;
	}

}
