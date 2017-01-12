package com.copetti.pgn.lexical.state;

import java.util.List;
import java.util.Optional;

import com.copetti.pgn.command.CommandBuilder;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgncommon.chess.token.ChessFile;
import com.copetti.pgncommon.chess.token.ChessPiece;

public class PawnCaptureState extends LexicalState {

	protected PawnCaptureState(List<PGNToken> tokens, CommandBuilder command) {
		super(tokens, command);
	}

	@Override
	protected Optional<LexicalState> onExecute() {

		PGNToken fileToken = pop();
		PGNToken captureToken = pop();

		ChessFile originFile = ChessFile.of(fileToken.getTokenValue()).get();
		command.setPieceType(ChessPiece.PAWN);

		System.out.println(
				"Pawn capture ( " + captureToken.getTokenValue() + " ) from file: " + originFile.getPgnNotation());
		return Optional.of(new DestinationSquareState(tokens, command));
	}

	@Override
	public List<Class<? extends LexicalState>> getSuccessors() {
		// TODO Auto-generated method stub
		return null;
	}

}
