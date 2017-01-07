package com.copetti.pgn.tokenizer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import com.copetti.pgncommon.chess.token.ChessBigO;
import com.copetti.pgncommon.chess.token.ChessCapture;
import com.copetti.pgncommon.chess.token.ChessCheck;
import com.copetti.pgncommon.chess.token.ChessCheckMate;
import com.copetti.pgncommon.chess.token.ChessDash;
import com.copetti.pgncommon.chess.token.ChessFile;
import com.copetti.pgncommon.chess.token.ChessPiece;
import com.copetti.pgncommon.chess.token.ChessPromotion;
import com.copetti.pgncommon.chess.token.ChessRank;

public class PGNTokenizer {

	List<PGNToken> tokens = new ArrayList<>();
	private TokenizerExecutor executor;

	public PGNTokenizer() {

		executor = new TokenizerExecutor();
		// Set<Class<? extends TokenInterface>> subTypesOf = new
		// Reflections("com.copetti.pgncommon.chess")
		// .get
		//
		// subTypesOf.forEach(x -> executor.addTokenizer(x.getme));

		executor.addTokenizer(ChessPiece::of);
		executor.addTokenizer(ChessFile::of);
		executor.addTokenizer(ChessRank::of);
		executor.addTokenizer(ChessCapture::of);
		executor.addTokenizer(ChessCheck::of);
		executor.addTokenizer(ChessCheckMate::of);
		executor.addTokenizer(ChessPromotion::of);
		executor.addTokenizer(ChessBigO::of);
		executor.addTokenizer(ChessDash::of);
	}

	public List<PGNToken> tokenize(String input) {

		if (input.isEmpty())
			throw new InputMismatchException("The empty string is not a valid input for the PGNTokenizer");

		String currentInput = input;
		Optional<PGNToken> tok;

		while ((tok = nextToken(currentInput)).isPresent()) {
			PGNToken token = tok.get();

			tokens.add(token);
			currentInput = currentInput.substring(token.getTokenValue().length());
		}

		return tokens;
	}

	private Optional<PGNToken> nextToken(String input) {

		return executor.execute(input);
	}

}
