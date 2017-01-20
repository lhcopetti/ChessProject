package com.copetti.pgn.interpreter;

import java.util.List;

import com.copetti.pgn.command.PGNCommand;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.PGNTokenizer;

public class PGNInterpreter {

	public PGNCommand parse(String chessCommand) {

		PGNTokenizer tokenizer = new PGNTokenizer();
		List<PGNToken> tokens = tokenizer.tokenize(chessCommand);

		if (tokens.isEmpty()) {
			System.out.println("Nenhuma token recebida!");
			return null;
		}

		// PGNLexical lexical = new PGNLexical();

		// if (!lexical.execute(tokens)) {
		// System.out.println("Falha na análise léxica para os tokens: " +
		// tokens);
		// return null;
		// }

		// return lexical.getCommand();
		return null;

	}

}
