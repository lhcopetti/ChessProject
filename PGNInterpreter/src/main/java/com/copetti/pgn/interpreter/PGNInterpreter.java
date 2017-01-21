package com.copetti.pgn.interpreter;

import java.util.List;

import com.copetti.pgn.command.ChessCommand;
import com.copetti.pgn.command.factory.ChessCommandFactory;
import com.copetti.pgn.lexical.PGNLexical;
import com.copetti.pgn.lexical.state.LexicalState;
import com.copetti.pgn.tokenizer.PGNToken;
import com.copetti.pgn.tokenizer.PGNTokenizer;

public class PGNInterpreter {

	public ChessCommand parse(String chessCommand) {

		PGNTokenizer tokenizer = new PGNTokenizer();
		List<PGNToken> tokens = tokenizer.tokenize(chessCommand);

		if (null == tokens || tokens.isEmpty()) {
			System.out.println("Nenhuma token recebida!");
			return null;
		}

		PGNLexical lexical = new PGNLexical();
		List<LexicalState> list = lexical.execute(tokens);

		if (null == list || list.isEmpty())
			return null;

		ChessCommandFactory factory = new ChessCommandFactory();
		return factory.create(list);
	}

}
