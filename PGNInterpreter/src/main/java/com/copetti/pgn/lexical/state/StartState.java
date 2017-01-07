package com.copetti.pgn.lexical.state;

import java.util.List;
import java.util.Optional;

import com.copetti.pgn.tokenizer.PGNToken;

public class StartState extends LexicalState {

	
	Optional<LexicalState> onExecute(List<PGNToken> tokens)
	{
		PGNToken head = tokens.get(0);
		
		switch(head.getTokenType())
		{
		case CHESS_FILE:
			return new ChessPieceState(tokens);
		}
		
	}
	
}
