package com.copetti.pgn.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.copetti.pgn.tokenizer.tokens.ChessBigO;
import com.copetti.pgn.tokenizer.tokens.ChessCapture;
import com.copetti.pgn.tokenizer.tokens.ChessCheck;
import com.copetti.pgn.tokenizer.tokens.ChessCheckMate;
import com.copetti.pgn.tokenizer.tokens.ChessDash;
import com.copetti.pgn.tokenizer.tokens.ChessFile;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;
import com.copetti.pgn.tokenizer.tokens.ChessPromotion;
import com.copetti.pgn.tokenizer.tokens.ChessRank;

public class PGNTokenizer {

	List<PGNToken> tokens = new ArrayList<>();
	private TokenizerExecutor executor;

	public PGNTokenizer() {

		executor = new TokenizerExecutor();

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
			return new ArrayList<>();

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
