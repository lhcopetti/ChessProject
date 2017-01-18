package com.copetti.pgn.command;

import com.copetti.pgn.board.ChessBoard;
import com.copetti.pgn.tokenizer.tokens.ChessPiece;

public class CastleLongCommand extends CastleCommand {

	public CastleLongCommand() {
		super(ChessPiece.KING);
	}

	@Override
	protected boolean canExecute(ChessBoard input) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected ChessBoard doExecute(ChessBoard input) {
		// TODO Auto-generated method stub
		return null;
	}

}
